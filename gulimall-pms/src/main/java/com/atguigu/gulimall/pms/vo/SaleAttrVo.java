package com.atguigu.gulimall.pms.vo;

import lombok.Data;

/**
 * @author winsoso
 * @title: SaleAttrVo
 * @projectName gulimall
 * @date 2019/8/63:02
 */
@Data
public  class SaleAttrVo{
        /**
         * {
         * "attrId": 0, //属性id
         * "attrValue": "string" //属性值
         *
         */
        private  Long attrId;
        private  String attrValue;

}
