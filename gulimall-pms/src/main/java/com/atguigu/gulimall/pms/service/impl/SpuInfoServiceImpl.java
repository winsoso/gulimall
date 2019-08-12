package com.atguigu.gulimall.pms.service.impl;

import com.atguigu.gulimall.commons.to.SkuSaleInfoTo;
import com.atguigu.gulimall.commons.utils.AppUtils;
import com.atguigu.gulimall.pms.dao.*;
import com.atguigu.gulimall.pms.entity.*;
import com.atguigu.gulimall.pms.feign.SmsSkuSaleInfoFeignService;
import com.atguigu.gulimall.pms.service.SpuInfoDescService;
import com.atguigu.gulimall.pms.vo.BaseAttrVo;
import com.atguigu.gulimall.pms.vo.SaleAttrVo;
import com.atguigu.gulimall.pms.vo.SkuVo;
import com.atguigu.gulimall.pms.vo.SpuAllSaveVo;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gulimall.commons.bean.PageVo;
import com.atguigu.gulimall.commons.bean.Query;
import com.atguigu.gulimall.commons.bean.QueryCondition;

import com.atguigu.gulimall.pms.service.SpuInfoService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Slf4j
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {

    @Autowired
    SmsSkuSaleInfoFeignService smsSkuSaleInfoFeignService;

    @Autowired
    SpuInfoDescService spuInfoDescService;

    @Autowired
    SpuInfoDao spuInfoDao;

    @Autowired
    SpuInfoDescDao spuInfoDescDao;

    //pms_product_attr_value
    @Autowired
    ProductAttrValueDao spuAttrValueDao;

    @Autowired
    SkuInfoDao skuInfoDao;

    @Autowired
    SkuImagesDao imagesDao;

    @Autowired
    AttrDao attrDao;

    @Autowired
    SkuSaleAttrValueDao skuSaleAttrValueDao;


    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageVo(page);
    }

    @Override
    public PageVo queryPageByCatId(QueryCondition queryCondition, Long catId) {
        //SELECT id,spu_name,spu_description,catalog_id,brand_id,publish_status,create_time,uodate_time FROM pms_spu_info WHERE catalog_id = ? AND ( spu_name LIKE ? OR id LIKE ? )
        //1、封装查询条件
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        if (catId != 0) {
            //查全站的
            // catalog_id = 227 and (spu_name like ss or id = 1)
            wrapper.eq("catalog_id", catId);
            if (!StringUtils.isEmpty(queryCondition.getKey())) {
                wrapper.and(obj -> {
                    obj.like("spu_name", queryCondition.getKey());
                    obj.or().like("id", queryCondition.getKey());
                    return obj;
                });

            }


        }


        //2、封装翻页条件
        IPage<SpuInfoEntity> page = new Query<SpuInfoEntity>().getPage(queryCondition);

        //3、去数据库查询
        IPage<SpuInfoEntity> data = this.page(page, wrapper);

        //(List<?> list, int totalCount, int pageSize, int currPage)
        //PageVo pageVo = new PageVo(data.getRecords(), data.getTotal(), data.getSize(), data.getCurrent());
        PageVo vo = new PageVo(data);
        return vo;
    }

    /**
     * 编译异常默认是不会滚的。
     * rollbackFor：指定让那些异常滚。
     *
     *
     * 运行时异常默认滚；
     *  默认生效。
     *
     *  timeout = 3;统计的是第一次开始sql与最后一次结束sql。
     *  timeout特指数据库超时而不是业务超时
     *
     * @param spuInfo
     * @throws FileNotFoundException
     */
//    @Transactional(rollbackFor = {Exception.class}
//            ,timeout = 3)
    @GlobalTransactional(rollbackFor = {Exception.class})
    @Override
    public void spuBigSaveAll(SpuAllSaveVo spuInfo) throws FileNotFoundException, InterruptedException {



        /**
         * 第一次：
         *      大保存：
         *              this.saveSpuBaseInfo(设置了requires_new)
         *              this.saveSpuInfoImages(设置了requires_new)；
         * 如果有效果，数据库的这两个有东西，然而毫无变化；
         *
         * 第二次：
         *      大保存：
         *              this.saveSpuBaseInfo(设置了requires_new)
         *              spuInfoDescService.saveSpuInfoImages(设置了requires_new);
         *
         *  如果有效果，数据库的这两个有东西，然而别人就行，this不行；
         *
         * 第三次
         *      大保存：
         *          this代理.saveSpuBaseInfo(设置了requires_new)
         *          this代理.saveSpuInfoImages(设置了requires_new);
         *  这个是可以的.....
         *
         *
         *  为啥？
         *      @Transactional：底层是用aop；
         *      事务要生效必须是代理对象在调用；
         *      this不是代理对象，就相当于代码粘到了大方法里面，this.方法（）；是跟外面用的一个事务
         *
         *  解决：
         *      1）、把这些放别人的service；
         *      2）、如果能获取到本类的代理对象，直接调用本类方法就完事;
         *          如何获取：
         *              1）、导入aop的场景依赖；spring-boot-starter-aop
         *              2）、开启aop的高级功能；@EnableAspectJAutoProxy：开启自动代理
         *              3）、同时要暴露代理对象；@EnableAspectJAutoProxy(exposeProxy=true)
         *              4）、获取代理对象；
         *                  SpuInfoService proxy = (SpuInfoService) AopContext.currentProxy();
         */
        SpuInfoService proxy = (SpuInfoService) AopContext.currentProxy();
        //1、存spu的基本信息
        //1.1）、存spu的基本信息
        Long spuId = proxy.saveSpuBaseInfo(spuInfo);

        //1.2）、保存spu的所有图片信息
        proxy.saveSpuInfoImages(spuId, spuInfo.getSpuImages());
        //spuInfoDescService.saveSpuInfoImages(spuId, spuInfo.getSpuImages());


        //2、保存spu的基本属性信息
        List<BaseAttrVo> baseAttrs = spuInfo.getBaseAttrs();
        proxy.saveSpuBaseAttrs(spuId, baseAttrs);


        //3、保存sku以及sku的营销相关信息
        //这个东西里面会出现数学运算异常，应该不生效？
        //这个里面调用远程了，看炸不炸？
        proxy.saveSkuInfos(spuId, spuInfo.getSkus());



       // int i = 10/0;
    }

    //负责解析出数据做出相应的业务  (propagation = Propagation.REQUIRES_NEW)
    @Transactional
    @Override
    public Long saveSpuBaseInfo(SpuAllSaveVo spuInfo) {

        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        BeanUtils.copyProperties(spuInfo, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUodateTime(new Date());

        spuInfoDao.insert(spuInfoEntity);

        return spuInfoEntity.getId();
    }

    //(propagation = Propagation.REQUIRES_NEW)
    @Transactional
    @Override
    public void saveSpuInfoImages(Long spuId, String[] spuImages) {

        SpuInfoDescEntity entity = new SpuInfoDescEntity();
        entity.setSpuId(spuId);
        entity.setDecript(AppUtils.arrayToStringWithSeperator(spuImages, ","));
        //spuInfoDescDao.insert(entity);
        //YetNotSupport: getPk xxx (Primary Key)
        //Seata 分布式事务框架 。。。按照主键区分上次的修改记录是谁。。
        //每一张表都需要有自己的主键
        spuInfoDescDao.insertInfo(entity);
    }

    @Transactional
    @Override
    public void saveSpuBaseAttrs(Long spuId, List<BaseAttrVo> baseAttrs) {

        List<ProductAttrValueEntity> allSave = new ArrayList<>();

        for (BaseAttrVo baseAttr : baseAttrs) {
            ProductAttrValueEntity entity = new ProductAttrValueEntity();
            entity.setAttrId(baseAttr.getAttrId());
            entity.setAttrName(baseAttr.getAttrName());
            String[] selected = baseAttr.getValueSelected();
            entity.setAttrValue(AppUtils.arrayToStringWithSeperator(selected, ","));
            entity.setAttrSort(0);
            entity.setQuickShow(1);
            entity.setSpuId(spuId);

            allSave.add(entity);
        }
        //pms_product_attr_value


        spuAttrValueDao.insertBatch(allSave);
    }

    /**
     * 传播行为；
     *   大保存：80s
     *   小保存：3s；
     *   但是小保存3s没作用；
     *
     *   大保存{
     *       小保存(rollbackFor = {ArithmeticException.class},timeout = 3)
     *   }
     * @param spuId
     * @param skus
     */
    //保存sku的所有详情
    @Transactional(rollbackFor = {ArithmeticException.class})
    @Override
    public void saveSkuInfos(Long spuId, List<SkuVo> skus) {
        //0、查出这个spu的信息，
        SpuInfoEntity spuInfo = this.getById(spuId);

        List<SkuSaleInfoTo> tos = new ArrayList<SkuSaleInfoTo>();
        //catalog_id  brand_id
        //1、保存sku的info信息
        for (SkuVo skuVo : skus) {
            String[] images = skuVo.getImages();

            SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
            skuInfoEntity.setBrandId(spuInfo.getBrandId());
            skuInfoEntity.setCatalogId(spuInfo.getCatalogId());
            skuInfoEntity.setPrice(skuVo.getPrice());
            skuInfoEntity.setSkuCode(UUID.randomUUID().toString().substring(0, 5).toUpperCase());
            if (images != null && images.length > 0) {
                skuInfoEntity.setSkuDefaultImg(skuVo.getImages()[0]);
            }

            skuInfoEntity.setSkuDesc(skuVo.getSkuDesc());
            skuInfoEntity.setSkuName(skuVo.getSkuName());
            skuInfoEntity.setSkuSubtitle(skuVo.getSkuSubtitle());
            skuInfoEntity.setSpuId(spuId);
            skuInfoEntity.setSkuTitle(skuVo.getSkuTitle());
            skuInfoEntity.setWeight(skuVo.getWeight());
            //保存sku的基本信息
            skuInfoDao.insert(skuInfoEntity);
            Long skuId = skuInfoEntity.getSkuId();

            //2、保存sku的所有对应图片
            for (int i = 0; i < images.length; i++) {
                SkuImagesEntity imagesEntity = new SkuImagesEntity();
                imagesEntity.setSkuId(skuId);
                imagesEntity.setDefaultImg(i == 0?1:0);
                imagesEntity.setImgUrl(images[i]);
                imagesEntity.setImgSort(0);
                imagesDao.insert(imagesEntity);
            }

            //3、当前sku的所有销售属性组合保存起来
            List<SaleAttrVo> saleAttrs = skuVo.getSaleAttrs();
            for (SaleAttrVo attrVo : saleAttrs) {

                //查询当前属性的信息

                SkuSaleAttrValueEntity entity = new SkuSaleAttrValueEntity();
                entity.setAttrId(attrVo.getAttrId());
                //查出这个属性的真正信息
                AttrEntity attrEntity = attrDao.selectById(attrVo.getAttrId());
                entity.setAttrName(attrEntity.getAttrName());
                entity.setAttrSort(0);
                entity.setAttrValue(attrVo.getAttrValue());
                entity.setSkuId(skuId);
                //sku与销售属性的关联关系
                skuSaleAttrValueDao.insert(entity);

            }

            //以上都是pms系统完成的工作


            //以下需要由sms完成，保存每一个sku的相关优惠数据
            SkuSaleInfoTo info = new SkuSaleInfoTo();
            BeanUtils.copyProperties(skuVo,info);
            info.setSkuId(skuId);

            tos.add(info);

        }

//        int i = 10/0;
        //2、发给sms，让他去处理。我们不管
        log.info("pms准备给sms发出数据...{}",tos);
        smsSkuSaleInfoFeignService.saveSkuSaleInfos(tos);
        log.info("pms给sms发出数据完成...");


    }

}