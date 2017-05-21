/**
 * 
 */
package org.mayank.shop.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.mayank.shop.dao.ShopDao;
import org.mayank.shop.exceptions.RepositoryException;
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

		if (request.getShopName() != null && !request.getShopName().isEmpty() && request.getShopAddress() != null && request.getShopAddress().getNumber()!=null && !request.getShopAddress().getNumber().isEmpty()
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
	public Shop getNearestShop(BigDecimal customerLongitude, BigDecimal customerLatitude) throws ShopException, RepositoryException {
		
		if (customerLongitude != null && customerLatitude != null) {

			Double minDistance = null;

			/** Retrieve list of available shops in repository */
			List<Shop> shopList = shopDao.getShops();

			Shop minShop = null;

			/**
			 * Logic to find nearest shop: Calculates distance of customer
			 * location from each shop. Returns the nearest shop based on
			 * minimum distance shop
			 */
			if (!shopList.isEmpty()) {
				for (Shop shop : shopList) {
					Double distance = getDistance(customerLatitude, customerLongitude, shop.getShopLatitude(),
							shop.getShopLongitude());
					if ( minDistance == null || distance < minDistance) {
						minDistance = distance;
						minShop = shop;
					}
				}
				return minShop;
			} else {
				logger.error("Repository is empty add some shops");
				throw new RepositoryException("No shops present in repository");
			}
			
		} else {
			logger.error("Latitude and Longitude are invalid");
			throw new ShopException("Latitude/Longitude are not present");
		}
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
	
	
	
	
	/**
	 * @param value Value in DecimalDegrees to convert into Radians
	 * @return value in Radian's
	 */
	private BigDecimal toRad(BigDecimal value) {
		return value.multiply(BigDecimal.valueOf(Math.PI/180));
	}
	
	
	
	
	
	/**
	 * @param lat1 Latitude of the customer
	 * @param lon1 Longitude of the customer
	 * @param lat2 Latitude of the shop
	 * @param lon2 Longitude of the shop
	 * @return Distance of customer from shop in KM
	 */
	public double getDistance(BigDecimal lat1, BigDecimal lon1, BigDecimal lat2, BigDecimal lon2) {

		int r = 6371; // radius of earth in km

		// Convert to Radians
		lat1 = toRad(lat1);
		lon1 = toRad(lon1);
		lat2 = toRad(lat2);
		lon2 = toRad(lon2);

		// Spherical Law of Cosines
		double distance = Math.acos(Math.sin(lat1.doubleValue()) * Math.sin(lat2.doubleValue()) + Math.cos(lat1.doubleValue()) * Math.cos(lat2.doubleValue()) * Math.cos(lon2.doubleValue() - lon1.doubleValue())) * r;
		return distance;
	}
}
