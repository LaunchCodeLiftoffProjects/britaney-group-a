package org.launchcode.britaneygroupa.controllers;

import org.launchcode.britaneygroupa.models.Product;
import org.launchcode.britaneygroupa.models.User;
import org.launchcode.britaneygroupa.models.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String displayProductList(Model model, HttpServletRequest request) {
        Integer userId = ((User) request.getSession().getAttribute("user")).getId();

        model.addAttribute("products", productRepository.findAllByUserId(userId));
        return "listProduct";
    }

    @GetMapping("add")
    public String displayAddProductForm(Model model) {
        model.addAttribute(new Product());
        return "addProduct";
    }

    @PostMapping("add")
    public String addProduct(@ModelAttribute @Valid Product newProduct, Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return "addProduct";
        }

        // get user id from session
        newProduct.setUserId((Integer) request.getSession().getAttribute("user"));

        // save product to db
        productRepository.save(newProduct);

        return "addProduct";

    }
}
