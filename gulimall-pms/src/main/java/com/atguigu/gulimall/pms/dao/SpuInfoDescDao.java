package com.atguigu.gulimall.pms.dao;

import com.atguigu.gulimall.pms.entity.SpuInfoDescEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息介绍
 * 
 * @author winsoso
 * @email 358281809@qq.com
 * @date 2019-08-02 11:29:38
 */
@Mapper
public interface SpuInfoDescDao extends BaseMapper<SpuInfoDescEntity> {

    /**
     * 强制要求
     * @param entity
     */
    void insertInfo(@Param("entity") SpuInfoDescEntity entity);
}

