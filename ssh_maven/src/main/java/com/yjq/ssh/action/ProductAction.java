package com.yjq.ssh.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.yjq.ssh.model.Product;
import com.yjq.ssh.service.ProductService;


@Controller
@Scope("prototype")
public class ProductAction  extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	ProductService productService;
	
	private String pname;
	private double price;
	
	/**
	 * 保存商品
	 * @return
	 */
	public String saveProduct() {
        Product product = new Product(pname, price);
        productService.saveProduct(product);
        this.addActionMessage("保存成功...");
        return SUCCESS;
    }
	
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}




	@Override
	public void validate() {
		if (pname==null||" ".equals(pname.trim())) {
			this.addFieldError("pname", "商品名称不能为空");
		}
	}
}
