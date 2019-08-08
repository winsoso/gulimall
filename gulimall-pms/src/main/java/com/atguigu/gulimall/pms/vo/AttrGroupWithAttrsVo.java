package com.atguigu.gulimall.pms.vo;

import com.atguigu.gulimall.pms.entity.AttrAttrgroupRelationEntity;
import com.atguigu.gulimall.pms.entity.AttrEntity;
import com.atguigu.gulimall.pms.entity.AttrGroupEntity;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author winsoso
 * @title: AttrGroupWithAttrsVo
 * @projectName gulimall
 * @date 2019/8/51:15
 */
@Data
public class AttrGroupWithAttrsVo extends AttrGroupEntity {
        private List<AttrEntity> attrEntities;

        private List<AttrAttrgroupRelationEntity> relations;
}
