package com.atguigu.gulimall.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.ums.entity.MemberLevelEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;


/**
 * 会员等级
 *
 * @author winsoso
 * @email 358281809@qq.com
 * @date 2019-08-02 18:49:49
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageVo queryPage(QueryCondition params);
}

