package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/manage/upload")
public class UploadController {

    // 从 application.properties 读取配置
    @Value("${img.realpath}")
    private String realPath; // 本地存储路径，如 E:/.../data_app 或 ./data_app

    @Value("${img.path}")
    private String serverPath; // 访问前缀，如 http://127.0.0.1:8080/image/

    /**
     * 单文件上传接口
     */
    @PostMapping("/image")
    public Map<String, Object> uploadImage(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();

        if (file.isEmpty()) {
            result.put("code", 400);
            result.put("msg", "文件为空");
            return result;
        }

        try {
            // 原始文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件后缀（如 .jpg）
            String ext = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            // 生成唯一文件名（避免重名）
            String newFileName = UUID.randomUUID().toString().replace("-", "") + ext;

            // 确保目录存在
            Path uploadPath = Paths.get(realPath);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 保存文件
            Path filePath = uploadPath.resolve(newFileName);
            file.transferTo(filePath.toFile());

            // 构造可访问的 URL
            String fileUrl = serverPath + newFileName;

            result.put("code", 200);
            result.put("msg", "上传成功");
            result.put("url", fileUrl); // 返回给前端的访问地址

        } catch (IOException e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "文件保存失败: " + e.getMessage());
        }

        return result;
    }
}