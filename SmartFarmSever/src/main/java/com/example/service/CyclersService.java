package com.example.service;

import com.example.entity.Cyclers;

import java.util.List;

public interface CyclersService {
    int insertCyclers(Cyclers cyclers);
    int updateCyclers(Cyclers cyclers);
    List<Cyclers> selectListForPage(int offset,int pageSize);
    int countCyclers();
    List<Cyclers> search(String keyword);
}
