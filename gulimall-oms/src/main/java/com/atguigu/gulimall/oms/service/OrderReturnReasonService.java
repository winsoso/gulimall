package com.atguigu.gulimall.oms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.oms.entity.OrderReturnReasonEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;


/**
 * 退货原因
 *
 * @author winsoso
 * @email 358281809@qq.com
 * @date 2019-08-02 18:46:33
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageVo queryPage(QueryCondition params);
}

