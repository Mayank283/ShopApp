/**
 * 
 */
package org.mayank.shop.controllers;

import java.math.BigDecimal;

import org.mayank.shop.exceptions.ErrorResponse;
import org.mayank.shop.exceptions.RepositoryException;
import org.mayank.shop.exceptions.ShopException;
import org.mayank.shop.json.request.ShopRequest;
import org.mayank.shop.model.Shop;
import org.mayank.shop.services.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to map all the request for shop related tasks.
 * @author Mayank
 */

@RestController
@RequestMapping("/shop")
public class ShopController {
	
	
	@Autowired
	ShopService shopService;
	
	
	/**URI Method for Retail Manager to add shop into his shop repository list
	 * @param request Shop request payload by Retail Manager.
	 * @return ResponseEntity Success on adding the shop to repository.
	 * @throws ShopException Exception thrown on wrong location/address of shop/customer
	 * */
	@RequestMapping(value = "/repository", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shop> addShop(@RequestBody ShopRequest request) throws ShopException{
		
		final String methodName = "addShop()";
		final Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Sending the Retail Manager to method: " + methodName);
		
        Shop shop = shopService.addShop(request);
        
        return new ResponseEntity<Shop>(shop,HttpStatus.OK);
	}
	
	
	
	
	
	/**URI to find nearest shop from list of available shop to customer
	 * @param customerLatitude - Latitude of customer location.Cannot be null
	 * @param customerLongitude - Longitude of customer location.Cannot be null 
	 * @return Shop Location near customer
	 * @throws ShopException Exception thrown on wrong location/address of shop/customer
	 * @throws RepositoryException Exception thrown when No shops are present in Shop repository
	 * */
	@RequestMapping(value = "/nearby", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Shop> getNearestShop(@RequestParam("customerLongitude") BigDecimal customerLongitude,
			@RequestParam("customerLatitude") BigDecimal customerLatitude) throws ShopException, RepositoryException{
		
		final String methodName = "getNearestShop()";
		final Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Sending the Customer to method: " + methodName);
        
        Shop shop = shopService.getNearestShop(customerLongitude, customerLatitude);
        
		return new ResponseEntity<Shop>(shop,HttpStatus.OK);
	}
	
	
	
	
	
	/**
	 * Custom exception handler for invalid requests when user has not mentioned location.
	 * @param ex Exception trace
	 * @return Custom response for exception thrown
	 */
	@ExceptionHandler(ShopException.class)
	public ResponseEntity<ErrorResponse> shopExceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode("SA-001");
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	
	/**
	 * Custom exception handler when no shops are present in repository
	 * @param ex Exception trace
	 * @return Custom response for exception thrown
	 */
	@ExceptionHandler(RepositoryException.class)
	public ResponseEntity<ErrorResponse> repositoryExceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode("SA-002");
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
}
