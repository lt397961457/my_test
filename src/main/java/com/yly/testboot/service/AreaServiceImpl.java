package com.yly.testboot.service;

import com.yly.testboot.entity.AreaEntity;
import com.yly.testboot.mapper.test.AreaMapper;
import com.yly.testboot.service.intf.AreaService;
import com.yly.testboot.util.page.PageParam;
import com.yly.testboot.util.page.PageResult;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Data
@Service("areaService")
//@Transactional
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaMapper areaMapper;
    @Override
    public List<AreaEntity> getAreasByName(String areaName) {
        return areaMapper.getAreasByName(areaName);
    }

    @Override
    public List<AreaEntity> getAll() {
        return areaMapper.getAll();
    }

    @Override
    public AreaEntity getAareaByAreaNo(String areaNum) {
        return areaMapper.getAareaByAreaNo(areaNum);
    }

    @Override
    public PageResult<AreaEntity> getAreasByNameWithPage(PageParam pageParam) {

        List<AreaEntity> resultData = areaMapper.getAreasByNameWithPage(pageParam);
        int totalCount = areaMapper.countByCondition(pageParam.getParams());
        PageResult<AreaEntity> pageResult = new PageResult<>();
        pageResult.setTotal(totalCount);
        pageResult.setData(resultData);
        return pageResult;
    }
}
