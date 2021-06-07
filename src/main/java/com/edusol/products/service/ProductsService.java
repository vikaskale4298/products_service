package com.edusol.products.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.edusol.products.model.Products;
import com.edusol.products.repository.ProductsRepository;
import com.google.gson.JsonObject;

@Service
public class ProductsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductsRepository productsRepository;

	public Object getProducts() {
		return productsRepository.findAll();

	}

	public Object addProducts(Products products) {
		logger.info(products.toString());
		productsRepository.save(products);
		logger.info("products added successfully");
		return new ResponseEntity<>("products  added Successfully", HttpStatus.CREATED);
	}

	public Object getProductsById(int id) {
		return productsRepository.findById(id);

	}

	public Object getProductsByCategory(String category) {
		return productsRepository.findByCategory(category);

	}

	public Object updateProducts(Products products) {
		JsonObject response = new JsonObject();

		System.out.println("Request products:" + products);
		int id = products.getId();
		try {

			Products prod = productsRepository.getOne(id);
			System.out.println("Request products :" + prod);

			System.out.println("old products:" + prod);
			prod.setPrice(products.getPrice());
			System.out.println("New products:" + prod);
			productsRepository.save(prod);
		} catch (Exception e) {
			logger.error(e.getMessage());

			response.addProperty("statusCode", 404);
			response.addProperty("statusMessage", e.getMessage());

			return new ResponseEntity<>(response.toString(), HttpStatus.NOT_FOUND);

		}
		logger.info("Products updated Successfully");
		return new ResponseEntity<>("Products updated succesfully", HttpStatus.OK);

	}

	public Object deleteProducts(int id) {
		try {

			productsRepository.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

		}
		logger.info("Products deleted Successfully");
		return new ResponseEntity<>("Products deleted succesfully", HttpStatus.OK);

	}

	public Object getProductsByPriceAndCategory(float price, String category) {
		return productsRepository.findByPriceAndCategory(price, category);
	}

	public Object getProductsPriceByRange(float min, float max) {
		return productsRepository.findByPriceBetween(min, max);

	}

	public Object getProductsPriceRangeAndCategory(float min, float max, String category) {
		return productsRepository.findByPriceBetweenAndCategory(min, max, category);
	}

	public Object getProductsPrice(float price) {

		return productsRepository.findByPrice(price);
	}

}
