package com.yly.testboot.controller;

import com.yly.testboot.entity.AreaEntity;
import com.yly.testboot.service.intf.AreaService;
import com.yly.testboot.util.page.PageParam;
import com.yly.testboot.util.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaService areaService;
    @GetMapping("/all")
    public List<AreaEntity> getAll(){
        return areaService.getAll();
    }
    @GetMapping("/byNum/{areaNum}")
    public AreaEntity getByNum(@PathVariable(value = "areasNum") String areano){
        return areaService.getAareaByAreaNo(areano);
    }
    @GetMapping("/byName/{areaName}")
    public List<AreaEntity> getByName(@PathVariable(value = "areaName") String areaName){

        return areaService.getAreasByName(areaName);
    }

    @PostMapping("/page/all")
    public PageResult<AreaEntity> getAllWithPage(@RequestBody PageParam pageParam){
        return areaService.getAreasByNameWithPage(pageParam);
    }
}
