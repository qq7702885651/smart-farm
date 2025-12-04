package com.example.controller;

import com.example.entity.Manager;
import com.example.service.Impl.ManagerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员控制器
 */
@RestController
@RequestMapping("/api/managers")
@RequiredArgsConstructor
public class ManagerController {
    
    private final ManagerServiceImpl managerServiceImpl;
    
    /**
     * 添加管理员
     * @param manager 管理员对象
     * @return 响应结果
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addManager(@RequestBody Manager manager) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean success = managerServiceImpl.addManager(manager);
            if (success) {
                response.put("success", true);
                response.put("message", "管理员添加成功");
                response.put("data", manager);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "管理员添加失败");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "添加管理员时发生错误: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    /**
     * 删除管理员
     * @param uid 管理员ID
     * @return 响应结果
     */
    @DeleteMapping("/{uid}")
    public ResponseEntity<Map<String, Object>> deleteManager(@PathVariable String uid) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean success = managerServiceImpl.deleteManager(uid);
            if (success) {
                response.put("success", true);
                response.put("message", "管理员删除成功");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "管理员删除失败，可能不存在");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "删除管理员时发生错误: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    /**
     * 更新管理员信息
     * @param uid 管理员ID
     * @param manager 管理员对象
     * @return 响应结果
     */
    @PutMapping("/{uid}")
    public ResponseEntity<Map<String, Object>> updateManager(@PathVariable String uid, @RequestBody Manager manager) {
        Map<String, Object> response = new HashMap<>();
        try {
            manager.setUid(uid);
            boolean success = managerServiceImpl.updateManager(manager);
            if (success) {
                response.put("success", true);
                response.put("message", "管理员更新成功");
                response.put("data", manager);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "管理员更新失败");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "更新管理员时发生错误: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    /**
     * 根据ID查询管理员
     * @param uid 管理员ID
     * @return 响应结果
     */
    @GetMapping("/{uid}")
    public ResponseEntity<Map<String, Object>> getManagerById(@PathVariable String uid) {
        Map<String, Object> response = new HashMap<>();
        try {
            Manager manager = managerServiceImpl.getManagerById(uid);
            if (manager != null) {
                response.put("success", true);
                response.put("message", "查询成功");
                response.put("data", manager);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "管理员不存在");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询管理员时发生错误: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    /**
     * 查询所有管理员
     * @return 响应结果
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllManagers() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Manager> managers = managerServiceImpl.getAllManagers();
            response.put("success", true);
            response.put("message", "查询成功");
            response.put("data", managers);
            response.put("total", managers.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询管理员列表时发生错误: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    /**
     * 根据用户名查询管理员
     * @param username 用户名
     * @return 响应结果
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<Map<String, Object>> getManagerByUsername(@PathVariable String username) {
        Map<String, Object> response = new HashMap<>();
        try {
            Manager manager = managerServiceImpl.getManagerByUsername(username);
            if (manager != null) {
                response.put("success", true);
                response.put("message", "查询成功");
                response.put("data", manager);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "管理员不存在");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询管理员时发生错误: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
    
    /**
     * 根据状态查询管理员列表
     * @param state 状态
     * @return 响应结果
     */
    @GetMapping("/state/{state}")
    public ResponseEntity<Map<String, Object>> getManagersByState(@PathVariable Integer state) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Manager> managers = managerServiceImpl.getManagersByState(state);
            response.put("success", true);
            response.put("message", "查询成功");
            response.put("data", managers);
            response.put("total", managers.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "查询管理员列表时发生错误: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
