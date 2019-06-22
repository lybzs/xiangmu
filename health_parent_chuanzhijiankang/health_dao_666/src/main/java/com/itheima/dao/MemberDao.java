package com.itheima.dao;

import com.itheima.pojo.Member;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/18 16:15
 * @description :
 * @version: 1.0
 */
public interface MemberDao {


    Member findByPhoneNumber(String telephone);

    void reg(Member member);

    int findMeth(String s);

    /**
     * 新增会员数
     * @param date
     * @return
     */
    long findNewMemberCount(String date);

    /**
     * 总会员数
     * @return
     */
    long findTotalCount();

    /**
     * 指定日期新增会员数
     * @param date
     * @return
     */
    long findMemberCountByDate(String date);
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
    