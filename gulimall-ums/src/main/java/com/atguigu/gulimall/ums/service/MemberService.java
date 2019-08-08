package com.atguigu.gulimall.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.ums.entity.MemberEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;


/**
 * 会员
 *
 * @author winsoso
 * @email 358281809@qq.com
 * @date 2019-08-02 18:49:49
 */
public interface MemberService extends IService<MemberEntity> {

    PageVo queryPage(QueryCondition params);
}

