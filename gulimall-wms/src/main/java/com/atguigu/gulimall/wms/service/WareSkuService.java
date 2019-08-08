package com.atguigu.gulimall.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.wms.entity.WareSkuEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;


/**
 * 商品库存
 *
 * @author winsoso
 * @email 358281809@qq.com
 * @date 2019-08-02 18:50:49
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageVo queryPage(QueryCondition params);
}

