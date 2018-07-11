package com.yly.testboot.mapper.test;

import com.yly.testboot.entity.GoodInfoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.mapping.StatementType;

public interface GoodsMapper {

    @Insert({"insert into basic_good_info (bgi_id,bgi_name, bgi_unit, bgi_price) values(#{id},#{name}, #{unit}, #{price})"})
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", before = false, resultType = Long.class, statementType = StatementType.STATEMENT)
    public void saveGood(GoodInfoEntity goodInfoEntity);
}
