package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.service.ServiceManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
  private ServiceManager<Product> service;
  private ProductService productservice;

  public ProductController(ServiceManager<Product> service, ProductService productservice) {
    this.service = service;
    this.productservice = productservice;
  }

  @GetMapping("/create")
  public String createProductPage(Model model) {
    Product product = new Product(); // ngeiniciate model product untuk pertama kali
    model.addAttribute("product", product);
    return "CreateProduct";
  }

  @PostMapping("/create")
  public String createProductPost(@ModelAttribute Product product, Model model) {
    service.create(product);
    return "redirect:list";
  }

  @GetMapping("/list")
  public String productListPage(Model model) {
    List<Product> allProducts = service.findAll();
    model.addAttribute("products", allProducts);
    return "ProductList";
  }

  @GetMapping("/edit/{productId}")
  public String editProductPage(@PathVariable("productId") String productId, Model model) {
    Product product = service.findById(productId);
    model.addAttribute("product", product);
    return "EditProduct";
  }

  @PutMapping("/edit")
  public String editProductPost(@ModelAttribute Product product) {
    productservice.edit(product);
    return "redirect:list";
  }

  @DeleteMapping("/delete/{productId}")
  public String deleteProduct(@PathVariable("productId") String productId) {
    service.deleteById(productId);
    return "redirect:../list";
  }
}