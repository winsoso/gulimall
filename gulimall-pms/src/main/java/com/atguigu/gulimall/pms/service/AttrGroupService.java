package com.atguigu.gulimall.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.gulimall.pms.entity.AttrGroupEntity;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.QueryCondition;


/**
 * 属性分组
 *
 * @author winsoso
 * @email 358281809@qq.com
 * @date 2019-08-02 11:29:38
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageVo queryPage(QueryCondition params);

    PageVo queryPageAttrGroupsByCatId(QueryCondition queryCondition, Long catId);

    /**
     * 根据属性的id 找到 它的分组信息
     * @param attrId
     * @return
     */
    AttrGroupEntity getGroupInfoByAttrId(Long attrId);

}

