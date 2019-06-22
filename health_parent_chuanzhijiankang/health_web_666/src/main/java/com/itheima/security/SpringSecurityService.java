package com.itheima.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.SysUser;
import com.itheima.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/19 16:11
 * @description :
 * @version: 1.0
 */
public class SpringSecurityService implements UserDetailsService {
    //使用注解扫描
    @Reference
    UserService userService;
    /**
     * 根据用户名载入一个安全框架的用户对象
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询数据库中用户信息
          SysUser sysuser = userService.findByUsername(username);
        //创建UserDetatall对象返回安全框架
        if (sysuser == null){
            return null;
        }
        //创建集合权限列表
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        //创建权限对象
        for (Role role : sysuser.getRoles()) {
            for (Permission permission : role.getPermissions()) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission.getKeyword());
                //添加到list集合
                list.add(authority);
            }
        }
        UserDetails userDetails = new User(sysuser.getUsername(),sysuser.getPassword(),list);
        return userDetails;
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
    