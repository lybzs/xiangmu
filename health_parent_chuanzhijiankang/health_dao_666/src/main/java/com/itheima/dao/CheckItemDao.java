package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/9 19:26
 * @description :
 * @version: 1.0
 */

public interface CheckItemDao {

    //添加
    void add(CheckItem checkItem);
     //分页查询
    Page<CheckItem> queryPage(String queryString);
     //删除
    int delete(Integer id);
    //检验是否能删
    int findByCheckItemId(Integer id);
     //编辑
    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    List<CheckItem> findAll();
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
    