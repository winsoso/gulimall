package com.atguigu.gulimall.pms.dao;

import com.atguigu.gulimall.pms.entity.ProductAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * spu属性值
 * 
 * @author winsoso
 * @email 358281809@qq.com
 * @date 2019-08-02 11:29:38
 */
@Mapper
public interface ProductAttrValueDao extends BaseMapper<ProductAttrValueEntity> {


    void insertBatch(@Param("baseAttrs") List<ProductAttrValueEntity> allSave);


}