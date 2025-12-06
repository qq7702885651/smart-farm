package com.example.service.Impl;

import com.example.entity.Cyclers;
import com.example.mapper.CyclersMapper;
import com.example.service.CyclersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CyclersServiceImpl implements CyclersService {
    @Autowired
    private CyclersMapper mapper;


    @Override
    public int insertCyclers(Cyclers cyclers) {
        return mapper.insertCyclers(cyclers);
    }

    @Override
    public int updateCyclers(Cyclers cyclers) {
        return mapper.updateCyclers(cyclers);
    }

    @Override
    public List<Cyclers> selectListForPage(int offset, int pageSize) {
        return mapper.selectListForPage(offset,pageSize);
    }

    @Override
    public int countCyclers() {
        return mapper.countCyclers();
    }

    @Override
    public List<Cyclers> search(String keyword) {
        return mapper.search(keyword);
    }
}
