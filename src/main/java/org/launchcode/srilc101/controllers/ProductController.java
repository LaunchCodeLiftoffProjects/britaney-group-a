package org.launchcode.srilc101.controllers;

import org.launchcode.srilc101.models.Product;
import org.launchcode.srilc101.models.User;
import org.launchcode.srilc101.models.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String addProduct(@ModelAttribute @Valid Product newProduct, Errors errors, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        if (errors.hasErrors()) {
            return "addProduct";
        }

        // get user id from session
        newProduct.setUserId(((User) request.getSession().getAttribute("user")).getId());

        // save product to db
        productRepository.save(newProduct);

        redirectAttrs.addFlashAttribute("info", "Product Added");

        return "redirect:/products";

    }
}
