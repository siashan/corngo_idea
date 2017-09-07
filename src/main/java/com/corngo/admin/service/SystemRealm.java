package com.corngo.admin.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.corngo.admin.model.SysMenu;
import com.corngo.admin.model.SysUser;
import com.corngo.base.support.utils.Shiro;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 自定义shiro 认证域
 *
 * @author kfc
 * @version 1.0 - 2016-11-30
 */
public class SystemRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        List<String> permissionsList = null;
       /* if (user.getId() == 1) {
            // 管理员最高权限
            List<SysMenu> menus = sysMenuService.selectList(null);
            permissionsList = new ArrayList<>(menus.size());
            for (SysMenu menu : menus) {
                permissionsList.add(menu.getPermissions());
            }
        } else {
            permissionsList = sysUserService.queryAllPerms(user.getId());
        }*/
        permissionsList = sysUserService.queryAllPerms(user.getId());
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String permission : permissionsList) {
            if (StringUtils.isBlank(permission)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(permission.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);

        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        EntityWrapper<SysUser> query = new EntityWrapper<>();
        query.where("user_name = {0}",username).and("password = {0}",password);

        SysUser user = sysUserService.selectOne(query);
        if (user == null) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        List<SysMenu> menus = sysMenuService.getUserFatherMenuList(user.getId());
        ArrayList<SysMenu> menuLift = new ArrayList<>();
        for (SysMenu menu : menus){
            if (menu.getType().equals("1")){
                ArrayList<SysMenu> child = new ArrayList<>();
                for (SysMenu m:menus){
//                    System.out.println("parentId:"+m.getParentId() + "---mId:"+menu.getId() +"---"+(m.getParentId() == menu.getId()) + "---"+((long)m.getParentId() == (long)menu.getId()));
                    if ((long)m.getParentId() == (long)menu.getId()){
                        child.add(m);
                    }
                }
                menu.setChildren(child);
                menuLift.add(menu);
            }
        }
        Shiro.setSessionAttribute("leftMenu",menuLift);
        Shiro.setSessionAttribute("user",user);
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
