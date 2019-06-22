package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/16 20:21
 * @description :
 * @version: 1.0
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    JedisPool jedisPool;

    @Reference
    OrderService orderService;


    /**
     * 1,获取用户输入的验证码
     * 2,获取手机号
     * 3,从redis获取验证码
     * 4,比较验证码,不同,return error
     * 相同 开始预约
     *
     * @param map
     * @return
     */
    @RequestMapping("/submit")
    public Result submit(@RequestBody Map<String, Object> map) {
        //获取用户输入的验证码
        Object validateCodeObj = map.get("validateCode");
        String validateCode = String.valueOf(validateCodeObj);
        //获取手机号
        Object telephoneObj = map.get("telephone");
        String telephone = String.valueOf(telephoneObj);
        //从redis中获取验证码
        String redisv = jedisPool.getResource().get(RedisMessageConstant.SENDTYPE_ORDER + "-" + telephone);
        //比较验证码
        if (redisv == null || !redisv.equals(validateCode)) {
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        } else {
            //添加预约方式为微信预约
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            Result result = orderService.oddOrder(map);
            return result;
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Map<String,Object> map =  orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
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
    