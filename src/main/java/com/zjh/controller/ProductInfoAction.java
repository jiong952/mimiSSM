package com.zjh.controller;

import com.github.pagehelper.PageInfo;
import com.zjh.pojo.ProductInfo;
import com.zjh.pojo.vo.ProductInfoVo;
import com.zjh.service.ProductInfoService;
import com.zjh.utils.FileNameUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //每页显示的记录数
    public static final int PAGE_SIZE = 5;
    //异步上传的图片的名称
    String saveFileName = "";
    @Autowired
    ProductInfoService productInfoService;
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest httpServletRequest){
        List<ProductInfo> list = productInfoService.getAll();
        httpServletRequest.setAttribute("list",list);
        return "product";
    }

    //初始默认显示第一页
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        PageInfo info = null;
        ProductInfoVo prodVo = (ProductInfoVo) request.getSession().getAttribute("prodVo");
        if(prodVo != null){
            info = productInfoService.splitPageVo(prodVo,PAGE_SIZE);
            request.getSession().removeAttribute("prodVo");
        }else {
            info = productInfoService.splitPage(1, PAGE_SIZE);
        }
        request.setAttribute("info",info);
        request.setAttribute("prodVo",prodVo);
        return "product";
    }
    //ajax更新分页
    @RequestMapping("/ajaxsplit")
    @ResponseBody
    public void ajaxSplit(ProductInfoVo vo, HttpSession session){
        PageInfo info = productInfoService.splitPageVo(vo,PAGE_SIZE);
        session.setAttribute("info",info);
    }

    //异步ajax文件上传处理
    @RequestMapping("/ajaxImg")
    @ResponseBody
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
        //生成存放的文件名（不可以使用用户上传的文件名，因为会重复）+文件名后缀
        saveFileName = FileNameUtil.getUUIDFileName() + FileNameUtil.getFileType(pimage.getOriginalFilename());
        //得到项目中图片存储的路径
        String path = request.getServletContext().getRealPath("/image_big");
        //使用文件核心上传组件进行转存到本地文件夹
        try {
            pimage.transferTo(new File(path + File.separator + saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回json数据（返回存放的文件名到前端进行显示）
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);
        return object.toString();
    }
    @RequestMapping("/save")
    public String save(ProductInfo productInfo,HttpServletRequest request){
        productInfo.setpImage(saveFileName);
        productInfo.setpDate(new Date());
        int flag = -1;
        try {
            flag = productInfoService.save(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(flag > 0){
            request.setAttribute("msg","增加成功");
        }else {
            request.setAttribute("msg","增加失败");
        }
        //清空saveFileName变量中的内容,为了下次增加或修改的异步ajax的上传处理
        saveFileName = "";
        return "forward:/prod/split.action";
    }
    @RequestMapping("/one")
    public ModelAndView one(int pid,ProductInfoVo vo,HttpSession session){
        session.setAttribute("prodVo",vo);
        ProductInfo productInfo = productInfoService.selectById(pid);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prod",productInfo);
        modelAndView.setViewName("update");
        return modelAndView;
    }
    @RequestMapping("/update")
    public String update(ProductInfo productInfo,HttpServletRequest request){
        //如果更新中更改了图片，则saveFileName就不为空
        //实体类info使用隐藏表单域提供上来的pImage原始图片的名称;
        if(!saveFileName.equals("")){
         productInfo.setpImage(saveFileName);
        }
        //完成更新处理
        int flag = -1;
        try {
            flag = productInfoService.update(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag > 0) {
            //此时说明更新成功
            request.setAttribute("msg", "更新成功!");
        } else {
            //更新失败
            request.setAttribute("msg", "更新失败!");
        }
        //处理完更新后,saveFileName里有可能有数据,
        // 而下一次更新时要使用这个变量做为判断的依据,就会出错,所以必须清空saveFileName.
        saveFileName = "";
        return "forward:/prod/split.action";
    }
    @RequestMapping("/delete")
    public String deleteOne(int pid ,ProductInfoVo vo, HttpServletRequest request){
        //完成删除处理
        int flag = -1;
        try {
            flag = productInfoService.deleteOne(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String msg = "";
        if (flag > 0) {
            //此时说明更新成功
            request.setAttribute("msg", "删除成功!");
            request.getSession().setAttribute("deleteProdVo",vo);
//            msg = "删除成功!";
        } else {
            //更新失败
            request.setAttribute("msg", "删除失败!");
//            msg = "删除失败!";
        }
        //删除结束后跳到分页显示
        return "forward:/prod/deleteAjaxSplit.action";
    }

    @RequestMapping(value = "/deleteAjaxSplit", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object deleteAjaxSplit(HttpServletRequest request){
        //取得第1页的数据
        PageInfo info = null;
        Object vo = request.getSession().getAttribute("deleteProdVo");
        if(vo != null){
            info = productInfoService.splitPageVo((ProductInfoVo)vo,PAGE_SIZE);
        }else {
            info = productInfoService.splitPage(1, PAGE_SIZE);
        }
        request.getSession().setAttribute("info",info);
        return request.getAttribute("msg");
    }

    //批量删除商品
    @RequestMapping("/deleteBatch")
    public String deleteBatch(String pids,HttpServletRequest request){
        String[] ids = pids.split(",");
        try {
            int flag = productInfoService.deleteBatch(ids);
            if(flag > 0 ){
                request.setAttribute("msg","批量删除成功!");
            }else{
                request.setAttribute("msg","批量删除失败!");
            }
        } catch (Exception e) {
            System.out.println(e);
            request.setAttribute("msg","商品不可被删除!");
        }
        return "forward:/prod/deleteAjaxSplit.action";
    }
}
