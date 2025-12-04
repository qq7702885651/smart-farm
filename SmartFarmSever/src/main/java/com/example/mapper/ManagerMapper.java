package com.example.mapper;

import com.example.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员Mapper接口
 */
@Mapper
public interface ManagerMapper {
    
    /**
     * 插入管理员
     * @param manager 管理员对象
     * @return 影响行数
     */
    int insert(Manager manager);
    
    /**
     * 根据ID删除管理员（逻辑删除）
     * @param uid 管理员ID
     * @return 影响行数
     */
    int deleteById(@Param("uid") String uid);
    
    /**
     * 更新管理员信息
     * @param manager 管理员对象
     * @return 影响行数
     */
    int update(Manager manager);
    
    /**
     * 根据ID查询管理员
     * @param uid 管理员ID
     * @return 管理员对象
     */
    Manager selectById(@Param("uid") String uid);
    
    /**
     * 查询所有管理员
     * @return 管理员列表
     */
    List<Manager> selectAll();
    
    /**
     * 根据用户名查询管理员
     * @param username 用户名
     * @return 管理员对象
     */
    Manager selectByUsername(@Param("username") String username);
    
    /**
     * 根据状态查询管理员列表
     * @param state 状态
     * @return 管理员列表
     */
    List<Manager> selectByState(@Param("state") Integer state);
}
