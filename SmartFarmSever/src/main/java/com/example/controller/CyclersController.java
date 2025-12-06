package com.example.controller;

import com.example.entity.Cyclers;
import com.example.service.CyclersService;
import com.example.util.Code;
import com.example.util.PageResult;
import com.example.util.Result;
import com.example.util.UUIDUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @Description 循环任务（Cyclers）管理接口
 * @Date
 */
@RestController
@RequestMapping("/manage/cyclers")
public class CyclersController {

    @Autowired
    private CyclersService service;

    /**
     * 新增循环任务
     * @param cyclers 待新增的任务实体
     * @return Result 返回操作是否成功
     */
    @PostMapping("/addCyclers")
    public Result addCyclers(@RequestBody  Cyclers cyclers) {
        // 生成唯一 ID
        cyclers.setUid(UUIDUtil.randomUUID());


        int i = service.insertCyclers(cyclers);

        if (i > 0) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    /**
     * 编辑循环任务（动态更新）
     * @param cyclers 任务实体（包含要更新的字段）
     * @return Result 返回操作是否成功
     */
    @PutMapping("/editCyclers")
    public Result editCyclers(@RequestBody  Cyclers cyclers) {
        int i = service.updateCyclers(cyclers);

        if (i > 0) {
            return Result.success("添加成功");
        } else {
            return Result.error("添加失败");
        }
    }

    /**
     * 分页查询循环任务列表
     *
     * @param offset   偏移量（起始行）
     * @param pageSize 每页条数
     * @return PageResult 返回分页数据 + 总条数
     */
    @GetMapping("/list")
    public PageResult<Cyclers> getPageResult(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        PageResult<Cyclers> ps = new PageResult<>();

        // 查询当前页数据
        ps.setData(service.selectListForPage(offset, pageSize));

        // 查询总记录数
        ps.setTotal(service.countCyclers());

        ps.setCode(Code.SUCCESS);
        ps.setMessage("success");

        return ps;
    }

    @GetMapping("/search")
    public Result search(@RequestParam String keyword) {
        List<Cyclers> cyclers = service.search(keyword);
        return Result.success(cyclers);
    }

}
