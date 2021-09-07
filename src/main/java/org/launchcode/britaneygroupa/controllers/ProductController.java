package org.launchcode.britaneygroupa.controllers;

import org.launchcode.britaneygroupa.models.Product;
import org.launchcode.britaneygroupa.models.User;
import org.launchcode.britaneygroupa.models.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "addProduct";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") int id, @ModelAttribute @Valid Product newProduct, Errors errors, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        newProduct.setUserId(((User) request.getSession().getAttribute("user")).getId());
        // save product to db
        newProduct.setId(id);
        productRepository.save(newProduct);

        redirectAttrs.addFlashAttribute("info", "Product Saved");

        return "redirect:/products";
    }

     @RequestMapping("/delete/{id}")
    public String processDeleteProductForm(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("product", productRepository.deleteById(id));
        return "redirect:/products";
    }
  }
