package com.example.controller;

import com.example.entity.Lands;
import com.example.service.LandsService;
import com.example.util.PageResult;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manage/lands")
public class LandsController {
    @Autowired
    private LandsService landsService;

    //添加功能
    @PostMapping("/addLands")
    public Result addLand(@RequestBody Lands lands){
        Result result = new Result();
        int len = landsService.countLands();
        lands.setUid("00" + (len + 1) + "land");

        int i = landsService.insertLands(lands);
        if(i>0){
            result.setCode(200);
            result.setMessage("success");
        }else {
            result.setCode(500);
            result.setMessage("fail");
        }
        return  result;
    }

    // 分页接口：GET /manage/lands/page/1/10
    @GetMapping("/page/{pageNum}/{pageSize}")
    public PageResult<Lands> pageLands(
            @PathVariable int pageNum,
            @PathVariable int pageSize) {
        return landsService.getLandsPage(pageNum, pageSize);
    }

    //删除功能实现,逻辑删除
    @DeleteMapping("/delete/{uid}")
    public Result deleteLands(@PathVariable String uid){
        int i = landsService.deleteLands(uid);
        if(i > 0){
            return Result.success("success");
        }else {
            return Result.error("fail");
        }
    }

    //数据删除
    @DeleteMapping("/delete/super/{uid}")
    public Result superDeleteLands(@PathVariable String uid){
        int i = landsService.superDeleteByUid(uid);
        if(i > 0){
            return Result.success("success");
        }else {
            return Result.error("fail");
        }
    }

    //修改功能实现
    @PutMapping("/update/{uid}")
    public Result updateLands(@RequestBody Lands lands,@PathVariable String uid){
        // 👇 关键：把路径中的 uid 设置到实体对象中
        lands.setUid(uid);
        int i = landsService.updateLands(lands);
        if(i > 0){
            return Result.success("success");
        }else {
            return Result.error("fail");
        }
    }

    //基于标题查询土地数据
    @GetMapping("/search/{title}")
    public Result searchLands(@PathVariable String title){
        List<Lands> lands = landsService.selectByTitleSearchLands(title);
        if(!lands.isEmpty()){
            return Result.success(lands);
        }else {
            return Result.error("fail");
        }
    }
}
