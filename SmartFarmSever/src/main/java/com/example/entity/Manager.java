package com.example.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 管理员实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Manager {
    
    /**
     * 数据id，主键
     */
    private String uid;
    
    /**
     * 管理员用户名
     */
    private String username;
    
    /**
     * 登录密码
     */
    private String passw;
    
    /**
     * 权限
     */
    private String purviews;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 状态：-1：删除；1：停用；2：正常
     */
    private Integer state;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdtime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updatedtime;


}
