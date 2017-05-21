/**
 * 
 */
package org.mayank.shop.services;

import java.math.BigDecimal;

import org.mayank.shop.exceptions.ShopException;
import org.mayank.shop.json.request.ShopRequest;
import org.mayank.shop.model.Shop;

/**
 * 
 * Interface for shop related services
 * @author Mayank
 *
 */
public interface ShopService {

	 
	 /**
	 * Method to set add the Shop into Repository with calculated Latitude and Longitude 
	 * @param request the shoprequest from RetailManager
	 * @throws ShopException 
	 */
	public void addShop(ShopRequest request) throws ShopException;
	 
	
	 /**
	  * 
	 * @param Longitude the longitude of customer
	 * @param Latitude the latitude of customer
	 * @return the nearest shop to customer
	 */
	public Shop getNearestShop(BigDecimal Longitude,BigDecimal Latitude);
	
}
