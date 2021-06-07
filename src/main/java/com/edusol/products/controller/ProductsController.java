package com.edusol.products.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edusol.products.model.Products;
import com.edusol.products.service.ProductsService;

@RestController
@RequestMapping("products")

public class ProductsController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired

	ProductsService productsService;

	@GetMapping("/get-products")

	public Object getProducts() {
		@SuppressWarnings("unchecked")
		List<Products> products = (List<Products>) productsService.getProducts();
		logger.info(products.toString());
		return products;
	}

	@PostMapping("/add-products")
	public Object addProducts(@RequestBody Products products) {

		return productsService.addProducts(products);
	}

	@GetMapping("/get-products-by-id")
	public Object getProductsById(@RequestParam int id) {
		//@SuppressWarnings("unchecked")
		Object products = productsService.getProductsById(id);

		logger.info(products.toString());
		return products;

	}

	@GetMapping("/get-products-by-category")
	public Object getProductsByCategory(@RequestParam String category) {
		@SuppressWarnings("unchecked")
		List<Products> products = (List<Products>) productsService.getProductsByCategory(category);

		logger.info(products.toString());
		return products;

	}

	@PutMapping("/update-products")
	public Object updateProducts(@RequestBody Products products) {
		return productsService.updateProducts(products);
	}

	@DeleteMapping("/delete-products")
	public Object deleteProducts(@RequestParam int id) {
		return productsService.deleteProducts(id);
	}

	@GetMapping("/get-products-price")
	public Object getProductsPrice(@RequestParam float price) {
		@SuppressWarnings("unchecked")
		List<Products> products = (List<Products>) productsService.getProductsPrice(price);

		logger.info(products.toString());
		return products;

	}

	@GetMapping("/get-products-by-priceand-category")
	public Object getProdutsByPriceAndCategory(@RequestParam float price, @RequestParam String category) {
		@SuppressWarnings("unchecked")
		List<Products> products = (List<Products>) productsService.getProductsByPriceAndCategory(price, category);

		logger.info(products.toString());
		return products;

	}

	@GetMapping("get-products-price-range")
	public Object getProductsPriceByRange(@RequestParam float min, float max) {
		@SuppressWarnings("unchecked")
		List<Products> products = (List<Products>) productsService.getProductsPriceByRange(min, max);

		logger.info(products.toString());
		return products;

	}

	@GetMapping("/get-products-price-range-category")
	public Object getProductsPriceRangeAndCategory(@RequestParam float min, float max, String category) {
		@SuppressWarnings("unchecked")
		List<Products> products = (List<Products>) productsService.getProductsPriceRangeAndCategory(min, max, category);

		logger.info(products.toString());
		return products;

	}

}
