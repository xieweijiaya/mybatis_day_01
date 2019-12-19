package com.zking.ssm.controller;

import com.zking.ssm.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/sysUser")
public class ShiroController {

    @RequestMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }


    @RequestMapping(value = "/login")
    public String login(SysUser sysUser, Model model, HttpServletRequest request) {
        String username = sysUser.getUsername();
        String password = sysUser.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        String message = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            message = "账号有误";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            message = "密码错误";
        } catch (Exception e) {
            e.printStackTrace();
            message = "账号或密码错误";
        }
        model.addAttribute("message", message);
        if (null == message) {
            return "redirect:/book/listbook";
        } else {
            return "login";
        }
    }

}
