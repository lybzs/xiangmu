package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/11 16:30
 * @description :
 * @version: 1.0
 */
public interface CheckGroupService {

    /**
     * 添加
     * @param checkitemIds
     * @param checkGroup1
     */
    void add(Integer[] checkitemIds, CheckGroup checkGroup1);

    /**
     * 查询
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 回显
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);

    /**
     * 查询中间表
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdById(Integer id);

    /**
     * 编辑
     * @param checkGroup
     * @param checkitemIds
     */
    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 删除检查项
     * @param id
     */
    void deleteId(Integer id);

    List<CheckGroup> findAll();
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
    