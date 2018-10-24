package com.wangfei.dao;

import com.wangfei.domain.User;
import com.wangfei.domain.UserDif;


import java.util.List;

/**
 * 创建持久层接口
 */
public interface UserDao {
    /**
     * 查询所有并返回集合，封装对象方法
     * @return
     */
    List<User> searchAll();

    /**
     * 删除用户操作
     * @param id
     */
    void deleteUser(int id);

    /**
     * 根据多个条件查询
     * @param id
     * @param username
     * @return
     */
    User selectBy(int id ,String username);

    /**
     * 根据参数模糊查询
     * @param str
     * @return
     */
    List<User> selectLike(String str);

    /**
     * 查询实体类属性名与表格列名不一样时
     * @return
     */
    List<UserDif> selectDif();
}
