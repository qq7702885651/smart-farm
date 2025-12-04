package com.example.service.Impl;

import com.example.entity.Manager;
import com.example.mapper.ManagerMapper;
import com.example.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 管理员服务层
 */
@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    
    private final ManagerMapper managerMapper;
    @Override
    public boolean addManager(Manager manager) {
        // 生成UUID作为主键
        if (manager.getUid() == null || manager.getUid().isEmpty()) {
            manager.setUid(UUID.randomUUID().toString().replace("-", ""));
        }
        
        // 设置默认值
        if (manager.getState() == null) {
            manager.setState(2); // 默认正常状态
        }
        if (manager.getCreatedtime() == null) {
            manager.setCreatedtime(LocalDateTime.now());
        }
        if (manager.getUpdatedtime() == null) {
            manager.setUpdatedtime(LocalDateTime.now());
        }
        
        return managerMapper.insert(manager) > 0;
    }

    @Override
    public boolean deleteManager(String uid) {
        return managerMapper.deleteById(uid) > 0;
    }
    

    @Override
    public boolean updateManager(Manager manager) {
        manager.setUpdatedtime(LocalDateTime.now());
        return managerMapper.update(manager) > 0;
    }
    

    @Override
    public Manager getManagerById(String uid) {
        return managerMapper.selectById(uid);
    }
    

    @Override
    public List<Manager> getAllManagers() {
        return managerMapper.selectAll();
    }
    

    @Override
    public Manager getManagerByUsername(String username) {
        return managerMapper.selectByUsername(username);
    }
    

    @Override
    public List<Manager> getManagersByState(Integer state) {
        return managerMapper.selectByState(state);
    }
}
