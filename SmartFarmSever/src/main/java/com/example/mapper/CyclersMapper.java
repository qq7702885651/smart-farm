package com.example.mapper;

import com.example.entity.Cyclers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CyclersMapper {
    int insertCyclers(Cyclers cyclers);
    int updateCyclers(Cyclers cyclers);
    List<Cyclers> selectListForPage(@Param("offset") int offset,@Param("pageSize") int pageSize);
    int countCyclers();
    List<Cyclers> search(@Param("title") String keyword);
}
