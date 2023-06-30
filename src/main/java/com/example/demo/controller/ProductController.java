package com.example.demo.controller;

import com.example.demo.entity.Categories;
import com.example.demo.entity.Products;
import com.example.demo.service.CategoriService;
import com.example.demo.service.ProductService;
import com.nimbusds.oauth2.sdk.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoriService categoriService;

    @RequestMapping("/product/list")
    public String list(Model model, @RequestParam("cid") Optional<Integer> cid,@RequestParam("page") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0),6);
        if (cid.isPresent()) {
            Page<Products> list = productService.findByCategoryId(cid.get(),pageable);
            model.addAttribute("items", list);
        } else {
            Page<Products> list = productService.findAll(pageable);
            model.addAttribute("items", list);
        }
        return "product/list";
    }

    @RequestMapping("/product/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Products item = productService.findById(id);
        model.addAttribute("item", item);
        return "product/detail";
    }

    @RequestMapping("/cates")
    @ResponseBody
    public List<Categories> cates(Model model) {
        return categoriService.findAll();
    }

}
