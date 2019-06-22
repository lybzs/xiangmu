package com.itheima.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 好好学习 天天向上!!
 * @date : 2019/6/11 16:31
 * @description :
 * @version: 1.0
 */
@Service(interfaceClass = CheckGroupService.class )
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

     @Autowired
     CheckGroupDao checkGroupDao;

    /**
     * 添加检查组
     * @param checkitemIds
     * @param checkGroup1
     */
    @Override
    public void add(Integer[] checkitemIds, CheckGroup checkGroup1) {
       //添加检查组,必须拿到检查组Id(mybatis中的主键回显操作)
        checkGroupDao.add(checkGroup1);
        //维护检查组和检查项的关系
        setCheckGroupAndCheckItem(checkGroup1.getId(),checkitemIds);
    }

    /**
     * 维护检查组和检查项 的关系
     * @param id
     * @param checkitemIds
     */
    private void setCheckGroupAndCheckItem(Integer id, Integer[] checkitemIds) {
          if (id != null && checkitemIds != null && checkitemIds.length > 0){
              for (Integer checkitemId : checkitemIds) {
                  checkGroupDao.insert(id,checkitemId);
              }
          }
    }

    /**
     * 查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
         //开始分页
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
       Page<CheckGroup> page = checkGroupDao.findByCondition(queryPageBean.getQueryString());
        return new PageResult(page.getTotal(),page);
    }

    /**
     * 数据回显
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    /**
     * 查询中间表
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public List<Integer> findCheckItemIdById(Integer id) {
        return checkGroupDao.findCheckItemIdById(id);
    }

    /**
     * 编辑提交表单
     * 1,删除检查组
     * 2,删除该检查组与检查项的关系
     * 3,维护新的关系
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
      //修改检查组
        checkGroupDao.edit(checkGroup);
        //删除该检查组与检查项的关系'
        checkGroupDao.deleteAssociation(checkGroup.getId());
        //维护新关系
        this.setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }

    /**
     * 删除检查组
     * 如果检查组被套餐关联 不能删除
     * 1,删除检查项与检查组的关系
     * 2,判断是否被关联
     * 3,如果没关连,删除
     * 4,如果关联,抛出异常
     * @param id
     */
    @Override
    public void deleteId(Integer id) {
        checkGroupDao.deleteAssociation(id);
      int count = checkGroupDao.findCountById(id);
       if (count > 0){
           throw new RuntimeException("该检查组被套餐关联,不能删除!!");
       }
       checkGroupDao.deleteId(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return  checkGroupDao.findAll();
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
    