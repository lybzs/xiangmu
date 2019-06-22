package com.itheima.controller;

import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/18 17:51
 * @description :
 * @version: 1.0
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    JedisPool jedisPool;
    /**
     * 验证验证码是否一致
     * *  1. 获取用户输入的验证码
     * *  2. 获取手机号码,获取redis中的验证码
     *  *  3. 判断验证码是否一致
     * @param map
     * @return
     */
    @RequestMapping("/check")
    public Result check(@RequestBody Map<String,String>map){
        //获取用户输入的验证码
        String validateCode = map.get("validateCode");
        //获取手机号
        String telephone = map.get("telephone");
        //获取redis中的验证码
        String ss = jedisPool.getResource().get(RedisMessageConstant.SENDTYPE_LOGIN+"-"+telephone);
        //判断验证码是否一致
        if (validateCode != null && validateCode.equals(ss)){
            return new Result(true,MessageConstant.LOGIN_SUCCESS);
        }else {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
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
    