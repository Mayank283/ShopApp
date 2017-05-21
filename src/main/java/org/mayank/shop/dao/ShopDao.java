/**
 * 
 */
package org.mayank.shop.dao;

import java.util.List;

import org.mayank.shop.model.Shop;

/**
 * Dao Class to persist the shop object into Array
 * 
 * @author Mayank
 *
 */
public interface ShopDao {

	/**
	 * Method to add Shop into Repository
	 * @param shop Shop to be added into repository
	 */
	public void addShop(Shop shop);

	/**
	 * Method to return all the shops in repository
	 * @return List of shop present in repository
	 */
	public List<Shop> getShops();
}
