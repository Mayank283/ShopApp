/**
 * 
 */
package org.mayank.shop.model;

import java.math.BigDecimal;

/**
 * Object to store complete information of shop.
 * 
 * @author Mayank
 *
 */
public class Shop {

	/**Name of Shop*/
	private String shopName;
	
	/**Address of Shop*/
	private ShopAddress shopAddress;
	
	/**Longitude of Shop*/
	private BigDecimal shopLongitude;
	
	/**Latitude of Shop*/
	private BigDecimal shopLatitude;

	
	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @param shopName the shopName to set
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	/**
	 * @return the shopAddress
	 */
	public ShopAddress getShopAddress() {
		return shopAddress;
	}

	/**
	 * @param shopAddress the shopAddress to set
	 */
	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}

	/**
	 * @return the shopLongitude
	 */
	public BigDecimal getShopLongitude() {
		return shopLongitude;
	}

	/**
	 * @param shopLongitude the shopLongitude to set
	 */
	public void setShopLongitude(BigDecimal shopLongitude) {
		this.shopLongitude = shopLongitude;
	}

	/**
	 * @return the shopLatitude
	 */
	public BigDecimal getShopLatitude() {
		return shopLatitude;
	}

	/**
	 * @param shopLatitude the shopLatitude to set
	 */
	public void setShopLatitude(BigDecimal shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	
}
