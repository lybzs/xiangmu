package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/11 16:44
 * @description :
 * @version: 1.0
 */
public interface CheckGroupDao {

    /**
     * 添加检查组
     * @param checkGroup1
     */
    void add(CheckGroup checkGroup1);

    /**
     *维护检查项和检查租的关系
     * @param id
     * @param checkitemId
     */
    void insert(@Param("id") Integer id,@Param("checkitemId") Integer checkitemId);

    /**
     * 根据查询条件查询检查组
     * @param queryString
     * @return
     */
    Page<CheckGroup> findByCondition(String queryString);

    /**
     * 回显数据
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
     */
    void edit(CheckGroup checkGroup);

    /**
     * 删除检查组与检查项的关系
     * @param id
     */
    void deleteAssociation(Integer id);

    /**
     *查询表关系
     * @param id
     * @return
     */
    int findCountById(Integer id);

    /**
     * 删除
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
    