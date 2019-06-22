package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/12 17:15
 * @description :
 * @version: 1.0
 */
public interface SetmealDao {

    /**
     * 添加
     * @param setmeal
     */
    void add(Setmeal setmeal);

    /**
     * 维护套餐和检查组关系
     * @param id
     * @param checkgroupId
     */
    void insert(Integer id, Integer checkgroupId);

    /**
     *查询
     * @param queryString
     * @return
     */
    Page<Setmeal> findByPage(String queryString);

    /**
     * 查询所有套餐
     * @return
     */
    List<Setmeal> findAll();

    /**
     * 根据id查询套餐数据
     * @param id
     * @return
     */
    Setmeal findById(Integer id);

    /**
     * 查询套餐统计数据
     * @return
     */
    List<Map<String, String>> findSetmealCount();

    /**
     * 查询热门套餐
     * @return
     */
    List<Map<String, Object>> findHotSetmeal();

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
    