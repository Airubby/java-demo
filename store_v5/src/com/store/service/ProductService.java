package com.store.service;

import java.util.List;

import com.store.domain.Product;

public interface ProductService {

	List<Product> findHots() throws Exception;

	List<Product> findNews() throws Exception;

}
