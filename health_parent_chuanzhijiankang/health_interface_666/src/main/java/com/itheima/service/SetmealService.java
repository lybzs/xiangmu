package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/12 16:47
 * @description :
 * @version: 1.0
 */
public interface SetmealService {

    /**
     * 添加
     * @param checkgroupIds
     * @param setmeal
     */
    void add(Integer[] checkgroupIds, Setmeal setmeal);

    /**
     * 查询
     * @param queryPageBean
     * @return
     */
    PageResult findByPage(QueryPageBean queryPageBean);

    /**
     * 查询套餐所有数据
     * @return
     */
    List<Setmeal> findAll();

    /**
     *查询套餐信息
     * @return
     */
    Setmeal findById(Integer id);

    /**
     * 查询套餐统计数据
     * @return
     */
    List<Map<String, String>> findSetmealCount();

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
    