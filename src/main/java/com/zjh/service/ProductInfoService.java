package com.zjh.service;

import com.github.pagehelper.PageInfo;
import com.zjh.pojo.ProductInfo;
import com.zjh.pojo.vo.ProductInfoVo;

import java.util.List;

public interface ProductInfoService {
    //获取全部数据
    List<ProductInfo> getAll();
    //商品分页展示
    PageInfo splitPage(int pageNum, int pageSize);
    //保存商品
    int save(ProductInfo productInfo);
    //根据主键查询商品
    ProductInfo selectById(int pid);
    //更新商品
    int update(ProductInfo productInfo);
    //删除单个商品
    int deleteOne(int pid);
    //批量删除商品
    int deleteBatch(String[] ids);
    //多条件查询商品
    PageInfo<ProductInfo> splitPageVo(ProductInfoVo vo, int pageSize);
}
