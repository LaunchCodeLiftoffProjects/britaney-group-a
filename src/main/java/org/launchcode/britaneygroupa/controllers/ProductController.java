package org.launchcode.britaneygroupa.controllers;

import org.launchcode.britaneygroupa.models.Product;
import org.launchcode.britaneygroupa.models.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String displayProductList(Model model, HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("user");

        model.addAttribute("products", productRepository.findById(userId));
        return "listProduct";
    }

    @GetMapping("add")
    public String displayAddProductForm(Model model) {
        model.addAttribute(new Product());
        return "addProduct";
    }

    @PostMapping("add")
    public String addProduct() {
        return null;
    }

}
