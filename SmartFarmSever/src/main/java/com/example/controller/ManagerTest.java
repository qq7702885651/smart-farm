package com.example.controller;

import com.example.entity.Manager;
import com.example.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试管理员功能控制器
 */
@RestController
@RequestMapping("/manage/test")
@RequiredArgsConstructor
public class ManagerTest {

    private final ManagerService managerService;

    /**
     * 添加管理员
     */
    @PostMapping
    @RequestMapping("/add-manager")
    public ResponseEntity<Map<String, Object>> addManager(@RequestBody Manager manager) {
        Map<String, Object> result = new HashMap<>();

        try {
            // 自动生成 UID 和时间
            if (manager.getUid() == null || manager.getUid().isEmpty()) {
                manager.setUid(java.util.UUID.randomUUID().toString().replace("-", ""));
            }
            LocalDateTime now = LocalDateTime.now();
            manager.setCreatedtime(now);
            manager.setUpdatedtime(now);

            boolean success = managerService.addManager(manager);

            result.put("success", success);
            result.put("message", success ? "管理员添加成功" : "添加失败，可能用户名已存在");
            result.put("data", manager);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "服务器错误: " + e.getMessage());
            return ResponseEntity.status(500).body(result);
        }
    }


    /**
     * 根据 UID 查询单个管理员
     */
    @GetMapping("/{uid}")
    public ResponseEntity<Map<String, Object>> getManager(@PathVariable String uid) {
        Map<String, Object> result = new HashMap<>();

        try {
            Manager manager = managerService.getManagerById(uid);
            if (manager != null) {
                result.put("success", true);
                result.put("message", "查询成功");
                result.put("data", manager);
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "未找到该管理员");
                return ResponseEntity.status(404).body(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败: " + e.getMessage());
            return ResponseEntity.status(500).body(result);
        }
    }

    /**
     * 更新管理员信息
     */
    @PutMapping("/{uid}")
    public ResponseEntity<Map<String, Object>> updateManager(@PathVariable String uid, @RequestBody Manager manager) {
        Map<String, Object> result = new HashMap<>();

        try {
            Manager existing = managerService.getManagerById(uid);
            if (existing == null) {
                result.put("success", false);
                result.put("message", "无法更新：管理员不存在");
                return ResponseEntity.status(404).body(result);
            }

            // 只更新允许的字段
            existing.setUsername(manager.getUsername() != null ? manager.getUsername() : existing.getUsername());
            existing.setPurviews(manager.getPurviews() != null ? manager.getPurviews() : existing.getPurviews());
            existing.setRemark(manager.getRemark());
            existing.setState(manager.getState() != null ? manager.getState() : existing.getState());
            existing.setUpdatedtime(LocalDateTime.now());

            boolean success = managerService.updateManager(existing);

            result.put("success", success);
            result.put("message", success ? "更新成功" : "更新失败");
            result.put("data", existing);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败: " + e.getMessage());
            return ResponseEntity.status(500).body(result);
        }
    }
    @GetMapping("/list")
    public  ResponseEntity<Map<String, Object>> getAllManagers() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Manager> managers = managerService.getAllManagers();
            result.put("data", managers);
            result.put("success", true);
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            result.put("success", false);
            return  ResponseEntity.status(500).body(result);
        }
    }



    /**
     * 删除管理员
     */
    @DeleteMapping("/{uid}")
    public ResponseEntity<Map<String, Object>> deleteManager(@PathVariable String uid) {
        Map<String, Object> result = new HashMap<>();

        try {
            Manager existing = managerService.getManagerById(uid);
            if (existing == null) {
                result.put("success", false);
                result.put("message", "删除失败：管理员不存在");
                return ResponseEntity.status(404).body(result);
            }

            boolean success = managerService.deleteManager(uid);
            result.put("success", success);
            result.put("message", success ? "删除成功" : "删除失败");

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败: " + e.getMessage());
            return ResponseEntity.status(500).body(result);
        }
    }
}
