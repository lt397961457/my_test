package com.yly.testboot.util.page;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PageParam {
    private Integer length;
    private Integer start;
    private Map<String,Object> params = new HashMap<>();

    public void setParam(String paramName,String paramValue){
        params.put(paramName,paramValue);
    }
}
