package com.atguigu.gulimall.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.wms.entity.WareOrderTaskEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;


/**
 * 库存工作单
 *
 * @author winsoso
 * @email 358281809@qq.com
 * @date 2019-08-02 18:50:49
 */
public interface WareOrderTaskService extends IService<WareOrderTaskEntity> {

    PageVo queryPage(QueryCondition params);
}

