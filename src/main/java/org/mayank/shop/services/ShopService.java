/**
 * 
 */
package org.mayank.shop.services;

import java.math.BigDecimal;

import org.mayank.shop.exceptions.RepositoryException;
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
	 * @return 
	 * @throws ShopException Exception Exception thrown on wrong location/address of shop/customer
	 */
	public Shop addShop(ShopRequest request) throws ShopException;
	 
	
	 /**
	  * Method to calculate and return the nearest shop to customer 
	 * @param Longitude the longitude of customer
	 * @param Latitude the latitude of customer
	 * @return the nearest shop to customer
	 * @throws ShopException Exception thrown on wrong location/address of shop/customer
	 * @throws RepositoryException Exception thrown when No shops are present in Shop repository
	 */
	public Shop getNearestShop(BigDecimal Longitude,BigDecimal Latitude) throws ShopException, RepositoryException;
	
}
