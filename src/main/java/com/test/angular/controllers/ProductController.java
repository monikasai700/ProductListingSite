package com.test.angular.controllers;

import com.test.angular.models.Product;
import com.test.angular.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public ModelAndView index(Model model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("products/index");
        mv.addObject("products",repository.findAll());
        //model.addAttribute("products", repository.findAll());
        //return "products/index";
        return mv;
    }

    @GetMapping("/add")
    public String add(Product product,Model model) {
        model.addAttribute(product);
        return "products/add";
    }

    @PostMapping("/add")
    public String add(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "products/add";
        }
        repository.save(product);
        model.addAttribute(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id,Product product, Model model) {
        logger.info("Hello id is "+id);
        logger.info("Hello model is "+model);
        logger.info("Hello product is "+repository.findById(id));
        model.addAttribute("product", repository.findById(id));

        logger.info("Hello model is "+model);
        return "products/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, @Valid Product product,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "redirect:/";
        }
        repository.save(product);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, Model model) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
