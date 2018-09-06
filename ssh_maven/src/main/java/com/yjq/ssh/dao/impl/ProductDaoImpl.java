package com.yjq.ssh.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.yjq.ssh.dao.ProductDao;
import com.yjq.ssh.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao{
	
	
	private HibernateTemplate template;
	
	@Autowired
	public ProductDaoImpl(SessionFactory sessionFactory){
		this.template=new HibernateTemplate(sessionFactory);
	}

	@Override
	public void saveProduct(Product product) {
			template.save(product);
	}

	
}
