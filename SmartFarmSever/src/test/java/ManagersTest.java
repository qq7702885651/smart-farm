import com.example.SpringBootApplication;
import com.example.entity.Manager;
import com.example.service.ManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest(classes = SpringBootApplication.class)
public class ManagersTest {
    @Autowired
    ManagerService managerService;

    @Test
    void addManager() {
        Manager manager = new Manager();
        manager.setUid("d6b5f0b280284ad0a16def563256de3a");
        manager.setUsername("zhangsan");
        manager.setPassw("123456");
        manager.setPurviews("user");
        manager.setState(2);
        manager.setCreatedtime(LocalDateTime.now());
        manager.setUpdatedtime(LocalDateTime.now());

        String result = managerService.addManager(manager) ? "添加成功":"添加失败";
        System.out.println(result);
    }

    @Test
    void deleteManager() {
        String result = managerService.deleteManager("d6b5f0b280284ad0a16def563256de3a") ? "删除成功":"删除失败";
        System.out.println(result);
    }

    @Test
    void updateManager() {
        Manager manager = new Manager();
        manager.setUid("d6b5f0b280284ad0a16def563256de3a");//必填根据这个查询数据库中的用户表
        manager.setUsername("lisi");
        manager.setPassw("123456789");
        manager.setPurviews("super_user");
        manager.setState(1);
        manager.setCreatedtime(LocalDateTime.now());
        manager.setUpdatedtime(LocalDateTime.now());
        String result = managerService.updateManager(manager)? "修改成功":"修改失败";
        System.out.println(result);
    }

    @Test
    void getManagerById() {
        System.out.println(managerService.getManagerById("d6b5f0b280284ad0a16def563256de1a"));
    }
}
