package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.dao.OrderDao;
import com.itheima.dao.SetmealDao;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/21 19:13
 * @description :
 * @version: 1.0
 */
@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    MemberDao memberDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    SetmealDao setmealDao;

    /**
     * reportDate:null,
     * todayNewMember :0,
     * totalMember :0,
     * thisWeekNewMember :0,
     * thisMonthNewMember :0,
     * todayOrderNumber :0,
     * todayVisitsNumber :0,
     * thisWeekOrderNumber :0,
     * thisWeekVisitsNumber :0,
     * thisMonthOrderNumber :0,
     * thisMonthVisitsNumber :0,
     * hotSetmeal :[
     * {name:'阳光爸妈升级肿瘤12项筛查（男女单人）体检套餐',setmeal_count:200,proportion:0.222},
     * {name:'阳光爸妈升级肿瘤12项筛查体检套餐',setmeal_count:200,proportion:0.222}
     * ]
     *
     * @return
     */
    @Override
    public Map<String, Object> findBusinessReportData() throws Exception {
        //创建统计数据的map集合
        Map<String, Object> map = new HashMap<>();
        //reportDate
        String date = DateUtils.parseDate2String(new Date());
        //获取本周周一的日期
        String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
        //获取本月一号的日期
        String thisMonthFirstDay = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
        //todayNewMember 新增会员数
        long todayNewMember = memberDao.findNewMemberCount(date);
        //totalMember 查询总会员数
        long totalMember = memberDao.findTotalCount();
        //thisWeekNewMember 查询本周新增会员数
        long thisWeekNewMember = memberDao.findMemberCountByDate(thisWeekMonday);
        //thisMonthNewMember  本月新增会员
        long thisMonthNewMember = memberDao.findMemberCountByDate(thisMonthFirstDay);


        //todayOrderNumber  今日预约数
        long todayOrderNumber = orderDao.findCountByOrderDate(date);
        //todayVisitsNumber  今日实际到诊数
        long todayVisitsNumber = orderDao.findVisitsNumber(date);
        //thisWeekOrderNumber 本周预约数
        long thisWeekOrderNumber = orderDao.findCountByAfterOrderData(thisWeekMonday);
        //thisWeekVisitsNumber 本周到诊数
        long thisWeekVisitsNumber = orderDao.findVisitsCountByAfterOrderData(thisWeekMonday);
        //thisMonthOrderNumber 本月预约数
        long thisMonthOrderNumber = orderDao.findCountByAfterOrderData(thisMonthFirstDay);
        //thisMonthVisitsNumber 本月到诊数
        long thisMonthVisitsNumber = orderDao.findVisitsCountByAfterOrderData(thisMonthFirstDay);

        //hotSetmeal  热门套餐
        List<Map<String, Object>> hotSetmeal = setmealDao.findHotSetmeal();

        //添加统计数据到map集合
        map.put("todayNewMember", todayNewMember);
        map.put("reportDate", date);
        map.put("totalMember", totalMember);
        map.put("thisWeekNewMember", thisWeekNewMember);
        map.put("thisMonthNewMember", thisMonthNewMember);
        map.put("todayOrderNumber", todayOrderNumber);
        map.put("todayVisitsNumber", todayVisitsNumber);
        map.put("thisWeekOrderNumber", thisWeekOrderNumber);
        map.put("thisWeekVisitsNumber", thisWeekVisitsNumber);
        map.put("thisMonthOrderNumber", thisMonthOrderNumber);
        map.put("thisMonthVisitsNumber", thisMonthVisitsNumber);
        map.put("hotSetmeal", hotSetmeal);
        return map;
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
    