package com.example.service;

import com.example.entity.Manager;

import java.util.List;

public interface ManagerService {

    /**
     * 添加管理员
     * @param manager 管理员对象
     * @return 是否成功
     */
    boolean addManager(Manager manager);

    /**
     * 删除管理员（逻辑删除）
     * @param uid 管理员ID
     * @return 是否成功
     */
    boolean deleteManager(String uid);

    /**
     * 更新管理员信息
     * @param manager 管理员对象
     * @return 是否成功
     */
    boolean updateManager(Manager manager);

    /**
     * 根据ID查询管理员
     * @param uid 管理员ID
     * @return 管理员对象
     */
    Manager getManagerById(String uid);

    /**
     * 查询所有管理员
     * @return 管理员列表
     */
    List<Manager> getAllManagers();

    /**
     * 根据用户名查询管理员
     * @param username 用户名
     * @return 管理员对象
     */
    Manager getManagerByUsername(String username);

    /**
     * 根据状态查询管理员列表
     * @param state 状态
     * @return 管理员列表
     */
    List<Manager> getManagersByState(Integer state);
}
