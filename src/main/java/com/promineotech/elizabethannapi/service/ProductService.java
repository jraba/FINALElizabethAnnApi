package com.promineotech.elizabethannapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.promineotech.elizabethannapi.entity.Customer;
import com.promineotech.elizabethannapi.entity.Product;
import com.promineotech.elizabethannapi.repository.ProductRepository;
import com.promineotech.elizabethannapi.service.ProductService;

@Service
public class ProductService {

	private static final Logger logger = LogManager.getLogger(ProductService.class);

	@Autowired
	private ProductRepository repo;

	public Iterable<Product> getProducts() {
		return repo.findAll();
	}

	public Product createProduct(Product product) {
		return repo.save(product);
	}

	public Product updateProduct(Product product, Long id) throws Exception {
		try {
			Product oldProduct = repo.findOne(id);
			oldProduct.setName(product.getName());
			oldProduct.setPrice(product.getPrice());
			oldProduct.setType(product.getType());
			oldProduct.setDescription(product.getDescription());
			return repo.save(oldProduct);
		} catch (Exception e) {
			logger.error("Issue occurred while trying to update product: " + id, e);
			throw new Exception("Update of product unsuccessful.");
		}
	}

	public void deleteProduct(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			logger.error("Issue occurred while trying to delete product: " + id, e);
			throw new Exception("Deletion of product unsuccessful.");

		}
	}

	public Product getProductById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			logger.error("Issue occurred while trying to retrieve product: " + id, e);
			throw e;
		}
	}

}
