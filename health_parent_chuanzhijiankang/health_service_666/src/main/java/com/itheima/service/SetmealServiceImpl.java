package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.RedisConstant;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/12 16:49
 * @description :
 * @version: 1.0
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    JedisPool jedisPool;

    @Autowired
    SetmealDao setmealDao;
    /**
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findByPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
       Page<Setmeal> page = setmealDao.findByPage(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page);
    }

    /**
     * 添加
     *
     * @param checkgroupIds
     * @param setmeal
     */
    @Override
    public void add(Integer[] checkgroupIds, Setmeal setmeal) {
        //添加套餐数据(注意:主键回显)
        setmealDao.add(setmeal);
        //维护套餐和检查组的关系
        setSetmelAndCheckGroup(setmeal.getId(), checkgroupIds);
        //把图片名称写入redis
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
    }



    /**
     * 维护套餐和检查组的关系
     *
     * @param id
     * @param checkgroupIds
     */
    private void setSetmelAndCheckGroup(Integer id, Integer[] checkgroupIds) {
        if (id != null && checkgroupIds != null && checkgroupIds.length > 0){
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.insert(id,checkgroupId);
            }
        }
    }
    /**
     * 查询套餐所有数据
     * @return
     */
    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    /**
     * 根据Id查询套餐数据
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    /**
     * 查询套餐统计数据
     * @return
     */
    @Override
    public List<Map<String, String>> findSetmealCount() {
        return setmealDao.findSetmealCount();
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
    