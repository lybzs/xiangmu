package com.itheima.controller;

import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/19 21:17
 * @description :
 * @version: 1.0
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @RequestMapping("/getUsername")
    public Result getUsername(HttpServletRequest request){
        //1 获取上下文对象
        SecurityContext context = SecurityContextHolder.getContext();
        //2 获取认证对象
        Authentication authentication = context.getAuthentication();
        //3获取重要信息(也就是User对象),需要强转
        Object principal = authentication.getPrincipal();
        User user= (User) principal;
        //4 得到用户名
        String username = user.getUsername();

        return  new Result(true,MessageConstant.GET_USERNAME_SUCCESS,username);
    }


}

    
    
    
    
    
    
    
    
    
    
        
/*
				   _ooOoo_
				  o8888888o
				 88"  .  "88
				(|  -   -  |)
				 O\   =   /O
			   ____/`---'\____
			.'  \\|       |//  `.
		   /  \\|||   :   |||//  \
		  /  _|||||  -:-  |||||-  \
		  |   |  \\\  -  ///  |   |
		  | \_|   ''\---/''   |   |
		  \  .-\__   `-`   ___/-. /
		 ___`. .'  /--.--\  `. . __
	  ."" '<  `.___\_<|>_/___.'  >'"".
	 | | :  `- \`.;`\ _ /`;.`/ - ` : | |
	 \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
				   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			佛祖保佑       永无BUG
*/   
    