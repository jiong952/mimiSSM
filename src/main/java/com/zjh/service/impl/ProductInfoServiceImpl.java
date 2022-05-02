package com.zjh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjh.mapper.ProductInfoMapper;
import com.zjh.pojo.ProductInfo;
import com.zjh.pojo.ProductInfoExample;
import com.zjh.pojo.vo.ProductInfoVo;
import com.zjh.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    ProductInfoMapper productInfoMapper;
    @Override
    public List<ProductInfo> getAll() {
        List<ProductInfo> productInfoList = productInfoMapper.selectByExample(new ProductInfoExample());
        return productInfoList;
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //设置分页
        PageHelper.startPage(pageNum,pageSize);
        ProductInfoExample example = new ProductInfoExample();
        //降序排列，使得新插入的数据显示在最前面
        example.setOrderByClause("p_id desc");
        //查询
        List<ProductInfo> productInfoList = productInfoMapper.selectByExample(example);
        //使用pageInfo进行封装
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(productInfoList);
        return pageInfo;
    }

    @Override
    public int save(ProductInfo productInfo) {
        int flag = productInfoMapper.insert(productInfo);
        return flag;
    }

    @Override
    public ProductInfo selectById(int pid) {
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(pid);
        return productInfo;
    }

    @Override
    public int update(ProductInfo productInfo) {
        int flag = productInfoMapper.updateByPrimaryKey(productInfo);
        return flag;
    }

    @Override
    public int deleteOne(int pid) {
        int flag = productInfoMapper.deleteByPrimaryKey(pid);
        return flag;
    }

    @Override
    public int deleteBatch(String[] ids) {
        int flag = productInfoMapper.deleteBatch(ids);
        return flag;
    }

    @Override
    public PageInfo<ProductInfo> splitPageVo(ProductInfoVo vo, int pageSize) {
        PageHelper.startPage(vo.getPage(),pageSize);
        List<ProductInfo> infos = productInfoMapper.selectCondition(vo);
        //使用pageInfo进行封装
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(infos);
        return pageInfo;
    }
}
