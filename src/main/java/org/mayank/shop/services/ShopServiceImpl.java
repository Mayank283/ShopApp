/**
 * 
 */
package org.mayank.shop.services;

import java.math.BigDecimal;

import org.mayank.shop.exceptions.ShopException;
import org.mayank.shop.json.request.ShopRequest;
import org.mayank.shop.model.Shop;
import org.mayank.shop.model.ShopAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.LatLng;

/**
 * Implementation of ShopService
 * 
 * @author Mayank
 */

@Service
public class ShopServiceImpl implements ShopService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void addShop(ShopRequest request) throws ShopException {

		ShopAddress shopAddress;
		String shopName;

		if (!request.getShopName().isEmpty() && request.getShopName() != null && request.getShopAddress() != null && request.getShopAddress().getNumber()!=null && !request.getShopAddress().getNumber().isEmpty()
				&& request.getShopAddress().getPostCode()!=null) {

			shopName = request.getShopName();
			shopAddress = request.getShopAddress();

			GeocodeResponse geoResponse = getLatitudeAndLongitude(shopName, shopAddress);
			if (geoResponse.getStatus().toString().equalsIgnoreCase("OK")) {

				LatLng location = geoResponse.getResults().get(0).getGeometry().getLocation();
				if (location != null) {
					logger.info("Successfully fetched shops latitude and longitude to add shop");
					Shop shop = new Shop();
					shop.setShopAddress(shopAddress);
					shop.setShopName(request.getShopName());
					shop.setShopLongitude(location.getLng());
					shop.setShopLatitude(location.getLat());
					// shopdao.addShop(shop);
				}
			} else {
				throw new ShopException(geoResponse.getStatus().toString());
			}
		} else {
			throw new ShopException("Request Does not contain shop information");
		}
	}

	@Override
	public Shop getNearestShop(BigDecimal Longitude, BigDecimal Latitude) {
		// TODO Auto-generated method stub
		return null;
	}

	private GeocodeResponse getLatitudeAndLongitude(String shopName, ShopAddress shopAddress) {
		// TODO Auto-generated method stub
		return null;
	}

}
