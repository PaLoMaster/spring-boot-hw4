package ru.khusyainov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.khusyainov.controller.ProductRepository;
import ru.khusyainov.model.Product;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAllProducts(Model uiModel,
                                  @RequestParam(required = false) Integer minCost,
                                  @RequestParam(required = false) Integer maxCost){
        List<Product> productsList;
        if (minCost == null && maxCost == null)
            productsList = productRepository.findAll();
        else if (minCost != null && maxCost != null)
            productsList = productRepository.findByCostBetween(minCost, maxCost);
        else if (minCost != null)
            productsList = productRepository.findByCostGreaterThan(minCost);
        else
            productsList = productRepository.findByCostLessThan(maxCost);
        uiModel.addAttribute("productsList", productsList);
        return "products";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddProduct(Model uiModel){
        Product product = new Product();
        uiModel.addAttribute("product", product);
        return "add-product";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(Model uiModel, Product product) {
        productRepository.save(product);
        uiModel.addAttribute("product", product);
        return "add-product";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getProduct(Model uiModel) {
        uiModel.addAttribute("product", null);
        return "get-product";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProductById(Model uiModel, @PathVariable Integer id) {
        Product product = productRepository.findById(id).orElse(new Product(id, null, 0));
        uiModel.addAttribute("product", product);
        return "get-product";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(Model uiModel, @PathVariable Integer id) {
        productRepository.deleteById(id);
        return showAllProducts(uiModel, null, null);
    }
}