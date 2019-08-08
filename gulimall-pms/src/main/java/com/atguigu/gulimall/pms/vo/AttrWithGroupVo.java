package com.atguigu.gulimall.pms.vo;

import com.atguigu.gulimall.pms.entity.AttrEntity;
import com.atguigu.gulimall.pms.entity.AttrGroupEntity;
import lombok.Data;

/**
 * @author winsoso
 * @title: AttrWithGroupVo
 * @projectName gulimall
 * @date 2019/8/51:57
 */
@Data
public class AttrWithGroupVo extends AttrEntity {
    private AttrGroupEntity group;
}
