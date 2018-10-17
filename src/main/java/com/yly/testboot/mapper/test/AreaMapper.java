package com.yly.testboot.mapper.test;

import com.yly.testboot.entity.AreaEntity;
import com.yly.testboot.util.page.PageParam;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

public interface AreaMapper {
    @Select("" +
            "select * from a_md_area where name like CONCAT(CONCAT('%',#{areaName},'%'))")
    @Results(id = "areaMap",value = {
            @Result(column = "areano",property = "areano",jdbcType= JdbcType.VARCHAR,id = true),
            @Result(column = "name",property = "name"),
            @Result(column = "areacode",property = "areacode"),
            @Result(column = "telcount",property = "telcount"),
            @Result(column = "superarea",property = "superarea"),
            @Result(column = "billcycle",property = "billcycle"),
            @Result(column = "powerlevel",property = "powerlevel")
    })
    public List<AreaEntity> getAreasByName(String areaName);
    @Select("select * from a_md_area")
    @ResultMap(value = "areaMap")
    public List<AreaEntity> getAll();
    @Select("select * from a_md_area where areano = #{areaNum}")
    public AreaEntity getAareaByAreaNo(String areaNum);


    /**
     * 注意：map的遍历，取key，取值的方式，以及#{..} 不能放入引号中
     * @param pageParam
     * @return
     */
    @Select("<script> " +
                 "select * from a_md_area where 1=1 " +
                "<if test='pageParam.params.size()>0'>" +
                    "<foreach collection='pageParam.params.keys' item='item' separator=\"and\" >" +
                        "and ${item} like  CONCAT(CONCAT('%',#{pageParam.params[${item}]},'%'))" +
                    "</foreach>" +
                "</if>" +
                "limit #{pageParam.start},#{pageParam.length}" +
            "</script>")
    public List<AreaEntity> getAreasByNameWithPage(@Param(value = "pageParam") PageParam pageParam);

    @Select("<script>" +
                "select count(1) from a_md_area where 1=1" +
                    "<if test='paraMap.size()>0'>" +
                        "<foreach collection=\"paraMap.keys\"  item=\"item\" separator=\"and\">" +
                             "and ${item} like  CONCAT(CONCAT('%',#{paraMap[${item}]},'%'))" +
                        "</foreach>" +
                    "</if>" +
            "</script>")
    int countByCondition(@Param(value = "paraMap") Map param);
}
