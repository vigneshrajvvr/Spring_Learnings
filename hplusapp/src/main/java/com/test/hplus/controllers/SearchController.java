package com.test.hplus.controllers;

import com.test.hplus.beans.Product;
import com.test.hplus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        System.out.println("in search controller");
        System.out.println("search criteria " + search);

        List<Product> products = new ArrayList<>();
        System.out.println("Product List : " + products.toString());
        products = productRepository.searchByName(search);
        model.addAttribute("products", products);
        return "search";
    }

//    @GetMapping
//    public ModelAndView search() {
//        System.out.println("in search controller");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("search");
//        return modelAndView;
//    }

}
