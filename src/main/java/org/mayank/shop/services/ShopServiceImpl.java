/**
 * 
 */
package org.mayank.shop.services;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.mayank.shop.dao.ShopDao;
import org.mayank.shop.exceptions.ShopException;
import org.mayank.shop.json.request.ShopRequest;
import org.mayank.shop.model.Shop;
import org.mayank.shop.model.ShopAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.geocoder.AdvancedGeoCoder;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.LatLng;

/**
 * Implementation of ShopService
 * 
 * @author Mayank
 */

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;
	
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
					shopDao.addShop(shop);
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

	
	
	
	
	/**
	 * Method to use Geocoder API and return the latitude and longitude of shop based on shop location
	 * @param shopName the name of shop
	 * @param shopAddress the Valid address of shop
	 * @return GeocoderResponse object with details
	 * @throws ShopException
	 */
	private GeocodeResponse getLatitudeAndLongitude(String shopName, ShopAddress shopAddress) throws ShopException {
		String address = "";

		address = address + shopName + "," + shopAddress.getNumber();
		if (shopAddress.getPostCode() != null) {
			address = address + "," + shopAddress.getPostCode().toString();
		}

		HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
		httpClient.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60 * 1000);

		Geocoder geocoder = new AdvancedGeoCoder(httpClient);

		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(address).setLanguage("en")
				.getGeocoderRequest();

		GeocodeResponse geocoderResponse;
		
		try {
			geocoderResponse = geocoder.geocode(geocoderRequest);
			return geocoderResponse;
			
		} catch (IOException e) {
			
			throw new ShopException("Exception from geocode",e);
		}
	}
}
