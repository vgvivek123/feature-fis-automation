package com.fis.Automation;

import org.junit.Test;

import com.fis.Pages.HomePage;

public class test_ebay_ui  {
	

	@Test
	public void validate_cart_value() {
		// TODO Auto-generated method stub
		
		String num_of_items = HomePage.validate_cart_items("https://www.ebay.com/", "books");
		Integer total_items = Integer.valueOf(num_of_items);
		assert total_items==1;
         
	}      
    
}
