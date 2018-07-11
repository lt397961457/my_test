package com.yly.testboot.service;

import com.yly.testboot.entity.GoodInfoEntity;
import com.yly.testboot.mapper.test.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 保存商品基本信息
     * @param good 商品实例
     * @return
     */
    public Long saveGood(GoodInfoEntity good) throws Exception
    {
        goodsMapper.saveGood(good);
        return good.getId();
    }


}
