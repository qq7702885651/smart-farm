package com.example.mapper;

import com.example.entity.Lands;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LandsMapper {
    int insertLands(Lands lands);
    Lands selectByUid(String uid);
    int updateLands(Lands lands);
    int deleteByUid(String uid);
    int superDeleteByUid(String uid);
    List<Lands> selectAll();
    List<Lands> pageLands(int offset, int pageSize);
    List<Lands> selectByTitleSearchLands(String title);
    int countLands();

}
