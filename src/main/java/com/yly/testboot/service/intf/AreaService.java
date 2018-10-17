package com.yly.testboot.service.intf;

import com.yly.testboot.entity.AreaEntity;
import com.yly.testboot.util.page.PageParam;
import com.yly.testboot.util.page.PageResult;

import java.util.List;
import java.util.Map;

public interface AreaService {
    public List<AreaEntity> getAreasByName(String areaName);
    public List<AreaEntity> getAll();
    public AreaEntity getAareaByAreaNo(String areaNum);

    PageResult<AreaEntity> getAreasByNameWithPage(PageParam pageParam);

}
