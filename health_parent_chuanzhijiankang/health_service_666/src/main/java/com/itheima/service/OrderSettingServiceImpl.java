package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/13 15:45
 * @description :
 * @version: 1.0
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    OrderSettingDao orderSettingDao;

    /**上传文件
       1,遍历集合添加预约对象
     * 2,查询该日期是否已经预约,如果已将预约,执行更新操作,如果没有设置执行添加操作
     * 3,注意:修改操作时,可预约人数必须大于已预约人数
     * @param orderSettingList
     */
    @Override
    public void addOrderSettings(List<OrderSetting> orderSettingList) {
            if (orderSettingList != null){
                //遍历集合添加预约对象
                for (OrderSetting orderSetting : orderSettingList) {
                    //查询该日期是否已预约
                   OrderSetting orderSettingDB = findByOrderDate(orderSetting.getOrderDate());
                    //如果已预约,执行更新操作,如果没有执行添加操作
                    if (orderSettingDB != null){
                        //修改操作时,可预约人数必须大于已预约人数
                        if (orderSetting.getNumber() >= orderSettingDB.getReservations()){
                            edit(orderSetting);
                        }else {
                            throw new RuntimeException("可预约人数必须大于等于已预约人数!!");
                        }
                    }else {
                        add(orderSetting);
                    }
              }
         }
    }

    /**
     * 查询
     * @param month
     * @return
     */
    @Override
    public List<OrderSetting> findByMonth(String month) {
        String beginDate = month + "-1";
        String endDate = month + "-31";
       List<OrderSetting> orderSettingList =  orderSettingDao.findByMonth(beginDate,endDate);
        return orderSettingList;
    }

    public OrderSetting findByOrderDate(Date orderDate){
        return orderSettingDao.findByOrderDate(orderDate);
    }

    public void edit(OrderSetting orderSetting){
       orderSettingDao.edit(orderSetting);
    }

    public void add(OrderSetting orderSetting){
        orderSettingDao.add(orderSetting);
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
    