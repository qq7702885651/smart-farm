# SmartFarmSever

智能农场服务端项目，实现了分页、MD5加密、主键获取等功能。

## 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── com/example/
│   │       ├── SpringBootApplication.java  # 主启动类
│   │       ├── controller/
│   │       │   └── TestController.java              # 测试控制器
│   │       ├── entity/
│   │       │   └── User.java                        # 用户实体类
│   │       └── util/
│   │           ├── PageDiv.java                     # 分页工具类
│   │           ├── MD5Util.java                     # MD5加密工具类
│   │           └── IdGenerator.java                 # 主键获取工具类
│   └── resources/
│       └── application.properties                   # 配置文件
└── pom.xml                                          # Maven配置文件
```

## 功能说明

### 1. 分页工具类 (PageDiv)
- 支持自定义页码和每页大小
- 自动计算总页数、是否有上一页/下一页等状态
- 提供分页信息字符串
- 支持泛型，可处理任意类型的数据

### 2. MD5加密工具类 (MD5Util)
- 支持普通MD5加密
- 支持带盐值的MD5加密
- 支持大小写转换
- 提供密码验证功能
- 支持随机盐值生成

### 3. 主键获取工具类 (IdGenerator)
- 雪花算法ID生成（分布式唯一ID）
- 自增ID生成
- 时间戳ID生成
- 纳秒ID生成
- UUID风格ID生成
- 带前缀ID生成
- 基于日期的ID生成
- 随机数字/字符串ID生成

## 运行方式

### 1. 环境要求
- JDK 8+
- Maven 3.6+
- MySQL 5.7+ (可选，用于数据库测试)

### 2. 启动项目
```bash
# 编译项目
mvn clean compile

# 运行项目
mvn spring-boot:run
```

### 3. 访问测试接口
项目启动后，可以通过以下接口测试功能：

- 健康检查: `GET http://localhost:8080/api/test/health`
- 分页测试: `GET http://localhost:8080/api/test/page?page=1&size=10`
- MD5加密测试: `GET http://localhost:8080/api/test/md5?input=hello`
- 主键生成测试: `GET http://localhost:8080/api/test/id`
- 综合测试: `GET http://localhost:8080/api/test/all`

## 配置说明

### application.properties
- 服务器端口: 8080
- 数据库配置: MySQL (需要根据实际情况修改)
- JPA配置: 自动建表、显示SQL等
- 日志配置: 控制台输出格式

## 使用示例

### 分页使用
```java
List<User> users = userService.findAll();
PageDiv<User> pageDiv = PageDiv.of(1, 10, users.size(), users);
```

### MD5加密使用
```java
String password = "123456";
String encrypted = MD5Util.encrypt(password);
boolean isValid = MD5Util.verify(password, encrypted);
```

### 主键生成使用
```java
IdGenerator idGenerator = new IdGenerator();
long id = idGenerator.nextId();
String uuid = IdGenerator.generateUuidStyleId();
```

## 注意事项

1. 数据库配置需要根据实际环境修改
2. 雪花算法需要确保机器ID和数据中心ID不重复
3. MD5加密建议使用盐值提高安全性
4. 分页工具类适用于内存分页，大数据量建议使用数据库分页
