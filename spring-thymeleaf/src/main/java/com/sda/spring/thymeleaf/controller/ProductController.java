package com.sda.spring.thymeleaf.controller;

import com.sda.spring.thymeleaf.dto.ProductUpdate;
import com.sda.spring.thymeleaf.model.Product;
import com.sda.spring.thymeleaf.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// mvc controller
@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // http://localhost:8080/products

    // map url to controller method
    @GetMapping("/products")
    public String showProductsPage(Model model) {
        // return a html page with products
        // add list of products
        List<Product> products = productService.findAll();
        model.addAttribute("productsInView", products);

        // resolved by the view resolver
        return "index";
    }

    @GetMapping("/products/add")
    public String showAddFrom(Model model) {
        Product newProduct = new Product();
        model.addAttribute("product", newProduct);
        return "product-add";
    }

    @PostMapping("/products/add")
    public String add(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/edit")
    public String showEditForm(Model model,
                               @PathVariable Long id) {

        model.addAttribute("product", productService.findById(id));
        return "product-edit";
    }

    public ModelAndView showEditForm2(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("product-edit");
        modelAndView.addObject("product", productService.findById(id));
        return modelAndView;
    }

    @PostMapping("/products/{id}/edit")
    public String edit(
            @PathVariable Long id,
            @ModelAttribute ProductUpdate productData) {

        productService.update(id, productData);
        return "redirect:/products";
    }

    @GetMapping("/products/{id}/delete")
    public String delete(@PathVariable long id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
