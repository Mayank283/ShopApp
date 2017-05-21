/**
 * 
 */
package org.mayank.shop.controllers;

import org.mayank.shop.exceptions.ErrorResponse;
import org.mayank.shop.exceptions.ShopException;
import org.mayank.shop.json.request.ShopRequest;
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
	 * @return ResponseEntity<String> Success on adding the shop to repository.
	 * @throws ShopException 
	 * */
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addShop(@RequestBody ShopRequest request) throws ShopException{
		
		final String methodName = "addShop()";
		final Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Sending the Retail Manager to method: " + methodName);
		
        shopService.addShop(request);
        
        return new ResponseEntity<String>("Success",HttpStatus.OK);
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
	
}
