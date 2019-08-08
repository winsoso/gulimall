package com.atguigu.gulimall.pms.vo;

import lombok.Data;

/**
 * @author winsoso
 * @title: BaseAttrVo
 * @projectName gulimall
 * @date 2019/8/63:00
 */
@Data
public class BaseAttrVo {
    /**
     * "attrId": 0, //属性id
     * "attrName": "string", //属性名
     * "valueSelected": [ "string" ] //属性值
     *
     */
    private Long attrId;
    private String attrName;
    private String[] valueSelected;
}
