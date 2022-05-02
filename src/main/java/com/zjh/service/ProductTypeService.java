package com.zjh.service;

import com.zjh.pojo.ProductType;

import java.util.List;

public interface ProductTypeService {
    //返回所有商品类型
    List<ProductType> getAll();
}
