package com.yly.testboot.util.page;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private int total;
    private int currentPage;
    private List<T> data;
}
