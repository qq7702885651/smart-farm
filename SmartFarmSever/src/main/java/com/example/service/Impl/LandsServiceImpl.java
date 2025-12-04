package com.example.service.Impl;

import com.example.entity.Lands;
import com.example.mapper.LandsMapper;
import com.example.service.LandsService;
import com.example.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandsServiceImpl implements LandsService {
    @Autowired
    LandsMapper mapper;

    @Override
    public int insertLands(Lands lands) {
        return mapper.insertLands(lands);
    }

    @Override
    public int updateLands(Lands lands) {
        return mapper.updateLands(lands);
    }

    @Override
    public int deleteLands(String uid) {
        return mapper.deleteByUid(uid);
    }

    @Override
    public int superDeleteByUid(String uid) {
        return mapper.superDeleteByUid(uid);
    }

    @Override
    public List<Lands> selectByTitleSearchLands(String title) {
        //实现模糊查询
        return mapper.selectByTitleSearchLands("%" + title + "%");
    }

    @Override
    public List<Lands> pageLands(int offset, int pageSize) {
        return mapper.pageLands(offset, pageSize);
    }

    @Override
    public PageResult<Lands> getLandsPage(int pageNum, int pageSize) {
        if (pageNum < 1) pageNum = 1;
        if (pageSize < 1) pageSize = 10;

        int offset = (pageNum - 1) * pageSize;
        List<Lands> list = mapper.pageLands(offset, pageSize);
        int total = mapper.countLands();

        return new PageResult<>(list, total, pageNum, pageSize);
    }


    @Override
    public int countLands() {
        return mapper.countLands();
    }
}
