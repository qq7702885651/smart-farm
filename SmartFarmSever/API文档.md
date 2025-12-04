# 管理员管理API文档

## 项目概述
本项目使用Spring Boot + MyBatis + Lombok实现了管理员表的简易增删改查功能。

## 技术栈
- Spring Boot 3.4.0
- MyBatis 3.0.3
- Lombok
- MySQL 8.0
- Maven

## 数据库表结构
```sql
CREATE TABLE managers (
    uid VARCHAR(255) NOT NULL PRIMARY KEY COMMENT '数据id，主键',
    username VARCHAR(255) DEFAULT NULL COMMENT '管理员用户名',
    passw VARCHAR(255) DEFAULT NULL COMMENT '登录密码',
    purviews VARCHAR(255) DEFAULT NULL COMMENT '权限',
    remark TEXT DEFAULT NULL COMMENT '备注',
    state INT(1) DEFAULT NULL COMMENT '状态：-1：删除；1：停用；2：正常',
    createdtime DATETIME DEFAULT NULL COMMENT '创建时间',
    updatedtime DATETIME DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';
```

## API接口

### 1. 添加管理员
- **URL**: `POST /api/managers`
- **请求体**:
```json
{
    "username": "admin",
    "passw": "123456",
    "purviews": "all",
    "remark": "系统管理员",
    "state": 2
}
```
- **响应**:
```json
{
    "success": true,
    "message": "管理员添加成功",
    "data": {
        "uid": "生成的UUID",
        "username": "admin",
        "passw": "123456",
        "purviews": "all",
        "remark": "系统管理员",
        "state": 2,
        "createdtime": "2024-01-01T10:00:00",
        "updatedtime": "2024-01-01T10:00:00"
    }
}
```

### 2. 删除管理员（逻辑删除）
- **URL**: `DELETE /api/managers/{uid}`
- **响应**:
```json
{
    "success": true,
    "message": "管理员删除成功"
}
```

### 3. 更新管理员信息
- **URL**: `PUT /api/managers/{uid}`
- **请求体**:
```json
{
    "username": "admin_updated",
    "passw": "newpassword",
    "purviews": "read_only",
    "remark": "更新后的备注"
}
```
- **响应**:
```json
{
    "success": true,
    "message": "管理员更新成功",
    "data": {
        "uid": "原UID",
        "username": "admin_updated",
        "passw": "newpassword",
        "purviews": "read_only",
        "remark": "更新后的备注",
        "state": 2,
        "createdtime": "2024-01-01T10:00:00",
        "updatedtime": "2024-01-01T11:00:00"
    }
}
```

### 4. 根据ID查询管理员
- **URL**: `GET /api/managers/{uid}`
- **响应**:
```json
{
    "success": true,
    "message": "查询成功",
    "data": {
        "uid": "查询的UID",
        "username": "admin",
        "passw": "123456",
        "purviews": "all",
        "remark": "系统管理员",
        "state": 2,
        "createdtime": "2024-01-01T10:00:00",
        "updatedtime": "2024-01-01T10:00:00"
    }
}
```

### 5. 查询所有管理员
- **URL**: `GET /api/managers`
- **响应**:
```json
{
    "success": true,
    "message": "查询成功",
    "data": [
        {
            "uid": "uid1",
            "username": "admin1",
            "passw": "123456",
            "purviews": "all",
            "remark": "管理员1",
            "state": 2,
            "createdtime": "2024-01-01T10:00:00",
            "updatedtime": "2024-01-01T10:00:00"
        }
    ],
    "total": 1
}
```

### 6. 根据用户名查询管理员
- **URL**: `GET /api/managers/username/{username}`
- **响应**:
```json
{
    "success": true,
    "message": "查询成功",
    "data": {
        "uid": "uid1",
        "username": "admin",
        "passw": "123456",
        "purviews": "all",
        "remark": "系统管理员",
        "state": 2,
        "createdtime": "2024-01-01T10:00:00",
        "updatedtime": "2024-01-01T10:00:00"
    }
}
```

### 7. 根据状态查询管理员列表
- **URL**: `GET /api/managers/state/{state}`
- **参数说明**:
  - state: 1-停用, 2-正常, -1-删除
- **响应**:
```json
{
    "success": true,
    "message": "查询成功",
    "data": [
        {
            "uid": "uid1",
            "username": "admin",
            "passw": "123456",
            "purviews": "all",
            "remark": "系统管理员",
            "state": 2,
            "createdtime": "2024-01-01T10:00:00",
            "updatedtime": "2024-01-01T10:00:00"
        }
    ],
    "total": 1
}
```

## 测试接口

### 1. 测试添加管理员
- **URL**: `GET /test/add-manager`
- **说明**: 自动创建一个测试管理员

### 2. 测试查询所有管理员
- **URL**: `GET /test/list-managers`
- **说明**: 查询所有管理员列表

## 启动项目

1. 确保MySQL数据库已启动，并创建了相应的数据库和表
2. 修改 `application.properties` 中的数据库连接信息
3. 运行主类 `SpringBootApplication`
4. 访问 `http://localhost:8080` 进行测试

## 项目结构
```
src/main/java/com/example/
├── SpringBootApplication.java  # 主启动类
├── entity/
│   └── Manager.java                     # 管理员实体类
├── mapper/
│   └── ManagerMapper.java              # MyBatis Mapper接口
├── service/
│   └── ManagerService.java             # 服务层
└── controller/
    ├── ManagerController.java          # 管理员控制器
    └── TestManagerController.java      # 测试控制器

src/main/resources/
├── application.properties              # 配置文件
└── mapper/
    └── ManagerMapper.xml               # MyBatis映射文件
```
