package ru.khusyainov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.khusyainov.controller.ProductRepository;
import ru.khusyainov.model.Product;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductRepository productRepository;
    private final int DEFAULT_PAGE_NUMBER = 0;
    private int currentPage = DEFAULT_PAGE_NUMBER;
    private final int DEFAULT_PAGE_SIZE = 10;

    @Autowired
    public void setProductRepository (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showAllProducts(Model uiModel,
                                  @RequestParam(required = false) Integer minCost,
                                  @RequestParam(required = false) Integer maxCost,
                                  @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER + "") int page,
                                  @RequestParam(defaultValue = DEFAULT_PAGE_SIZE + "") int pageSize) {
        Page<Product> productsPage;
        StringBuilder urlQuery = new StringBuilder("/products?");
        Pageable pageable = PageRequest.of(page, pageSize);
        if (minCost == null && maxCost == null) {
            productsPage = productRepository.findAll(pageable);
        } else if (minCost != null && maxCost != null) {
            productsPage = productRepository.findByCostBetween(minCost, maxCost, pageable);
            urlQuery.append("minCost=").append(minCost).append("&maxCost=").append(maxCost).append("&");
        } else if (minCost != null) {
            productsPage = productRepository.findByCostGreaterThan(minCost, pageable);
            urlQuery.append("minCost=").append(minCost).append("&");
        } else {
            productsPage = productRepository.findByCostLessThan(maxCost, pageable);
            urlQuery.append("maxCost=").append(maxCost).append("&");
        }
        if (productsPage.getNumberOfElements() == 0 && productsPage.getPageable().hasPrevious()) {
            return showAllProducts(uiModel, minCost, maxCost, page - 1, pageSize);
        }
        currentPage = page;
        uiModel.addAttribute("urlQuery", urlQuery.toString());
        uiModel.addAttribute("currentPage", currentPage);
        uiModel.addAttribute("totalPages", productsPage.getTotalPages());
        uiModel.addAttribute("productsList", productsPage.getContent());
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
    public String deleteProduct(Model uiModel, @PathVariable Integer id,
                                @RequestParam(required = false) Integer minCost,
                                @RequestParam(required = false) Integer maxCost) {
        productRepository.deleteById(id);
        return showAllProducts(uiModel, minCost, maxCost, currentPage, DEFAULT_PAGE_SIZE);
    }
}