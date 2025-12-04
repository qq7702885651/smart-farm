package com.example.service;

import com.example.entity.Lands;
import com.example.util.PageResult;

import java.util.List;

public interface LandsService {
    int insertLands(Lands lands);
    int updateLands(Lands lands);
    int deleteLands(String uid);
    int superDeleteByUid(String uid);
    List<Lands> pageLands(int offset, int pageSize);
    PageResult<Lands> getLandsPage(int pageNum, int pageSize);
    List<Lands> selectByTitleSearchLands(String title);
    int countLands();
}
