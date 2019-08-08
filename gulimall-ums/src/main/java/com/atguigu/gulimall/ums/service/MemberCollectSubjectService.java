package com.atguigu.gulimall.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.ums.entity.MemberCollectSubjectEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;


/**
 * 会员收藏的专题活动
 *
 * @author winsoso
 * @email 358281809@qq.com
 * @date 2019-08-02 18:49:49
 */
public interface MemberCollectSubjectService extends IService<MemberCollectSubjectEntity> {

    PageVo queryPage(QueryCondition params);
}

