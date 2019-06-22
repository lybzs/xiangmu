package com.itheima.dao;

import com.itheima.pojo.Order;

import java.util.Map;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/18 16:15
 * @description :
 * @version: 1.0
 */
public interface OrderDao {


    long findByCondition(Order order);

    void add(Order order);

    Map<String, Object> findById(Integer id);

    /**
     * 今日预约数
     * @param date
     * @return
     */
    long findCountByOrderDate(String date);

    /**
     * 今日实际到诊数
     * @param date
     * @return
     */
    long findVisitsNumber(String date);

    /**
     * 指定日期之后预约数
     * @param thisWeekMonday
     * @return
     */
    long findCountByAfterOrderData(String thisWeekMonday);

    /**
     * 指定日期到诊数
     * @param thisWeekMonday
     * @return
     */
    long findVisitsCountByAfterOrderData(String thisWeekMonday);
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
    