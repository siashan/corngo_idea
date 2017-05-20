package com.corngo.admin.controller;

import com.corngo.base.support.controller.BaseController;
import com.corngo.base.support.utils.Shiro;
import net.sf.ehcache.Cache;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kfc on 2017/2/9.
 */
@Controller
@RequestMapping("admin/passport")
public class PassportController extends BaseController{

    @Resource(name = "graphCache")
    private Cache graphCache;
    /**
     * 登录页
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String toLogin() {
        return "admin/login";
    }

    /**
     * 登陆
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(String username,String password,String captcha,RedirectAttributes ra,HttpServletRequest request) {
        try{
            // 验证码校验   开发室注释掉了
//            if(StringUtil.isBlank(captcha)){
//                ra.addFlashAttribute("msg", "验证码不能为空！");
//                return "redirect:/admin/passport/login";
//            }else{
//                Element element = graphCache.get(request.getSession().getId());
//                if (element == null || element.getObjectValue() == null || element.isExpired()) {
//                    ra.addFlashAttribute("msg", "验证码过期或无效");
//                    return "redirect:/admin/passport/login";
//                }
//                String checkCode = element.getObjectValue().toString();
//                if (!checkCode.equalsIgnoreCase(captcha)) {
//                    ra.addFlashAttribute("msg", "验证码错误");
//                    return "redirect:/admin/passport/login";
//                }
//
//                graphCache.removeElement(element);  //验证成功后删除
//        }
            // 用户名密码验证
            Subject subject = Shiro.getSubject();
            password = new Sha256Hash(password).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        }catch (IncorrectCredentialsException | LockedAccountException e){
            ra.addAttribute("msg","用户名或密码错误");
            return "redirect:/admin/passport/login";
        }
        return "redirect:/admin/passport/index";
    }

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value = "index")
    public String index(Model model){
        model.addAttribute("user", user());
//        model.addAttribute("menus", menuService.getUserMenuList(userid()));
        return "admin/index";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("logout")
    public String logout(){
        Shiro.logout();
        return "redirect:/admin/passport/login";
    }

    /**
     * 404页面
     * @return
     */
    @RequestMapping("404")
    public String page404(){
        return "admin/404";
    }

    /**
     * 500页面
     * @return
     */
    @RequestMapping("500")
    public String page500(){
        return "admin/500";
    }
}
