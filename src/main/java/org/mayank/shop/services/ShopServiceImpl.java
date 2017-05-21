/**
 * 
 */
package org.mayank.shop.services;

import java.math.BigDecimal;

import org.mayank.shop.json.request.ShopRequest;
import org.mayank.shop.model.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Implementation of ShopService
 * @author Mayank
 */

@Service
public class ShopServiceImpl implements ShopService{

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public void addShop(ShopRequest request) {
		
	}

	
	@Override
	public Shop getNearestShop(BigDecimal Longitude, BigDecimal Latitude) {
		// TODO Auto-generated method stub
		return null;
	}

}
