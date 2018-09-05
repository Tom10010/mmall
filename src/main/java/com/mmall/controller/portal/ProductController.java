package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.service.IProductService;
import com.mmall.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhang on 2018/3/20.
 */

@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<ProductDetailVo> detail(Integer productId) {
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping(value = "{productId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<ProductDetailVo> detailRestful(@PathVariable Integer productId) {
        return iProductService.getProductDetail(productId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> List(@RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                         @RequestParam(value = "categoryId", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "categoryId", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "orderBy", defaultValue = "") String orderBy) {
        return iProductService.getProductKeywordCategoryId(keyword, categoryId, pageNum, pageSize, orderBy);
    }

    @RequestMapping("/{keyword}/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
    @ResponseBody
    public ServerResponse<PageInfo> ListRestful(@PathVariable(value = "keyword") String keyword,
                                         @PathVariable(value = "categoryId") Integer categoryId,
                                         @PathVariable(value = "pageNum") int pageNum,
                                         @PathVariable(value = "pageSize") int pageSize,
                                         @PathVariable(value = "orderBy") String orderBy) {
        return iProductService.getProductKeywordCategoryId(keyword, categoryId, pageNum, pageSize, orderBy);
    }

//    @RequestMapping("/{keyword}/{categoryId}/{pageNum}/{pageSize}/{orderBy}")
//    @ResponseBody
//    public ServerResponse<PageInfo> ListRestfulBadcase(@PathVariable(value = "keyword") String keyword,
//                                                @PathVariable(value = "categoryId") Integer categoryId,
//                                                @PathVariable(value = "pageNum") int pageNum,
//                                                @PathVariable(value = "pageSize") int pageSize,
//                                                @PathVariable(value = "orderBy") String orderBy) {
//        return iProductService.getProductKeywordCategoryId(keyword, categoryId, pageNum, pageSize, orderBy);
//    }


}
