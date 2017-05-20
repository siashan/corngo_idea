package com.corngo.base.support.tag;

import com.corngo.admin.service.ISysUserService;
import com.corngo.base.support.spring.SpringContextHolder;
import com.corngo.base.support.utils.Shiro;
import jodd.util.StringUtil;
import org.beetl.core.Tag;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 后台细粒度控制按钮
 * <p/>
 * Usage:   <#button id="viewBtn" class="am-btn am-btn-primary" icon="am-icon-plus" s="sys:menu:list"(必选) func="viewDetail"(必选)  value="查看详情"(必选) req=${request} (必须)/>
 * Created by kfc on 2016/6/30.
 */
public class ButtonTag extends Tag {
    @Override
    public void render() {
        try {
            Map attrs = (Map) this.args[1];
            // 点击按钮调用的方法
            String func = (String) attrs.get("func");
            // HttpServletRequest 请求
            HttpServletRequest requestt = (HttpServletRequest) attrs.get("req");
            // 按钮值
            String value = (String) attrs.get("value");
            String s = (String) attrs.get("s");

            // 权限拦截
            if(StringUtils.isEmpty(s) || !checkPermission(s)){
                this.ctx.byteWriter.writeString("");
                return;
            }

            StringBuffer sb = new StringBuffer("<button ");
            // ID 判断
            if (attrs.containsKey("id")) {
                sb.append(" id= '" + attrs.get("id").toString() + "' ");
            }
            // class判断
            if (attrs.containsKey("class")) {
                sb.append("class = '" + attrs.get("class") + "' ");
            }
            sb.append("onclick = '" + func + "'");
            sb.append(">");
            sb.append(value);
            // 图标判断
            if (attrs.containsKey("icon")) {
                sb.append("<i class = '" + attrs.get("icon") + "'></i>");
            }
            sb.append("</button>");
            this.ctx.byteWriter.writeString(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO 可优化，用户登陆后将权限缓存起来，不再查询数据库
    private boolean checkPermission(String s) {
        Long userId = Shiro.getUserId();
        ISysUserService iSysUserService = SpringContextHolder.getBean(ISysUserService.class);
        List<String> permissionsList = iSysUserService.queryAllPerms(userId);
        //用户权限列表
        for (String permission : permissionsList) {
            if (StringUtil.isNotBlank(permission)&&permission.contains(s.trim())) {
                return true;
            }
        }
        return false;
    }
}
