package ru.khusyainov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.khusyainov.controller.ProductRepository;
import ru.khusyainov.model.Product;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductsController {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAllProducts(Model uiModel){
        List<Product> productsList = productRepository.findAll();
        uiModel.addAttribute("productsList", productsList);
        return "products";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String getAddProduct(Model uiModel){
        Product product = new Product();
        uiModel.addAttribute("product", product);
        return "add-product";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addProduct(Model uiModel, Product product) {
        productRepository.addProduct(product);
        uiModel.addAttribute("product", product);
        return "add-product";
    }
}