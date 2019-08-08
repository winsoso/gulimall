package com.atguigu.gulimall.pms.vo;

import com.atguigu.gulimall.pms.entity.SpuInfoEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author winsoso
 * @title: SpuAllSaveVo
 * @projectName gulimall
 * @date 2019/8/520:20
 */
/**
 * spu 全量信息 【spu基本信息，spu 的基本属性。所有的sku 的基本信息 所有的促销信息】
 *
 */
@Data
public class SpuAllSaveVo extends  SpuInfoEntity  {
        //spu的详情图
        private String[] spuImages;
        //当前spu de 所有基本属性名值对
        private List<BaseAttrVo> baseAttrs;

        //当前spu对应的 所有sku 的信息
        private  List <SkuVo> skus;

}
