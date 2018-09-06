package com.yjq.ssh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjq.ssh.dao.ProductDao;
import com.yjq.ssh.model.Product;
import com.yjq.ssh.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDao productDao;
	

	@Override
	public void saveProduct(Product product) {
		 productDao.saveProduct(product);
	}

}
