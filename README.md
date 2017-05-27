# ShopApp
This Project is generated using Spring-Boot-1.5.2.RELEASE

## Checkpoints:
1.	Code base via Git
2.	Document for server side API
3.	Test Command
4.	Code Coverage Command
5.	Java Document Generate Command
6.	Build Command
7.	Run Command
8.	Run instructions

## Code Base:
Download/clone the Shop API code from below Github Repository.

https://github.com/mayankagd/ShopApp.git

## Documentation of API
Shop API contains below packages along with their significance:
1.  Org.mayank.shop - Contains Main method which is executed when the API is packaged as jar.
2.	Org.mayank.shop.model - Contains POJO’s of Shop and it’s address.
3.	Org.mayank.shop.json.request - POJO to map the request from the Retail manager.
4.	Org.mayank.shop.controllers - RESTFUL API to expose methods.
5.	Org.mayank.shop.services - Methods to handle Business logic.
6.	Org.mayank.shop.dao - Methods to store/persist data from Retail Manager.
7.	Org.mayank.shop.exceptions - Contains custom Exceptions for Shop Application.

#### URL’s
1. **Retail manager**

   URL:`localhost:8090/shop/repository`
  
   Request Method: Post
   
   Header: Key: Content-Type, Value- application/json
   
   Success Request Payload:
	 
		{
		"shopname":"Brijwasi",
		"shopaddress": { 
				 "number" : "Bus stand" ,
				 "postCode": 281002
			       }
		}

    Success Response Payload:
	
		{
		  "shopName": "Brijwasi",
		  "shopAddress": {
		    		   "number": "Bus stand",
		    		   "postCode": 281002
		   		 },
		  "shopLongitude": 79.4386454,
		  "shopLatitude": 23.8320934
		}

     Failure Request Payload:

		{
		  "shopname":"",
		  "shopaddress": { 
			          "number" : "",
		   	 	  "postCode": ""
				 }
		}
				
	Failure Response Payload

		{
		  "errorCode": "SA-001",
		  "message": “Request Does not contain shop information”
		}

2.	**Customer**

	URL:`localhost:8090/shop/nearby?customerLatitude={Latitude}&customerLongitude={Longitude}`

	Request Method: Get

	Success Response Payload:

		{
		  "shopName": "Puneri”,
		  "shopAddress": {
		    		   "number": "Pune",
		        	   "postCode": 411057
		  		 },
		  "shopLongitude": 73.89988760000001,
		  "shopLatitude": 18.4947829
		}

	Failure Response Payload: - When no shops are added by Retail manager into Repository
	
		{
		  "errorCode": "SA-002",
		  "message": "No shops present in repository"
		}

	Failure Response Payload: - When customer has not entered Latitude/Longitude
	
		{
		  "errorCode": "SA-001",
		  "message": "Latitude/Longitude are not present"
		}

## Test Command:
Use `gradle test` to run Test Scripts and goto `build\reports\tests\test` path inside the project to view the generated report.

Use `gradle jacocoTestReport` to generate report for code coverage and goto `build\customJacocoReportDir\test\html` path inside the project to view Jacoco test report.
     
## Java Document Generate:
Use `gradle javadoc` to generate Document and goto `build\docs\javadoc` to view the generated java documentation.

## Build Command:
Use `gradle build` to build the ShopAPP Jar.

## Run Command:
Run `java -jar build/libs/ShopAPP.jar` to execute the application.The application would be up on localhost and port 8090 i.e. localhost:8090.

Location of Log file- `C:/logs/spring-boot-logging.txt`

## Run instruction:
1.	**Retail Manager** 

	URL:`localhost:8090/shop/repository`

	Request Method: POST

	**Case 1: Enter valid Request**
	
		{
		"shopname":"Brijwasi",
		"shopaddress": { 
				 "number" : "Bus stand",
				 "postCode": 281002
			       }
		}

	Response:  

		{
		  "shopName": "Brijwasi",
		  "shopAddress": {
				   "number": "Bus stand",
				   "postCode": 281002
				 },
		  "shopLongitude": 79.4386454,
		  "shopLatitude": 23.8320934
		}
		
	**Case 2: Enter invalid Request:**
		
		{
		 "shopname":"",
		 "shopaddress": {  
				  "number" : "Pune",
				  "postCode": 411057
				}
		}

	Response:
	
		{
		  "errorCode": "SA-001",
		  "message": “Request Does not contain shop information”
		}

2.  **Customer**
	
	URL:`localhost:8090/shop/nearby?customerLatitude={Latitude}&customerLongitude={Longitude}`
	
	Request Method: Get
	
	**Case1: Enter Invalid request without customer Latitude or Longitude**
	
	`localhost:8090/shop/nearby?customerLatitude=27.1761049&customerLongitude=`

	Response:

		{
		  "errorCode": "SA-001",
		  "message": “Latitude/Longitude are not present”
		}

	**Case 2: Enter valid request**
	
	`localhost:8090/shop/nearby?customerLatitude=23.9520934&customerLongitude=79.2366454`

	Response:

		{
		  "shopName": "Brijwasi",
		  "shopAddress": {
		    		   "number": "Bus stand",
		   		   "postCode": 281002
		  		 },
		  "shopLongitude": 79.4386454,
		  "shopLatitude": 23.8320934
		}

	**Case 3: Enter valid request after restarting the application and without adding any shop**

	`localhost:8090/shop/nearby?customerLatitude=27.1761049&customerLongitude=77.8399274`

	Response:

		{
		  "errorCode": "SA-002",
		  "message": “No shops present in repository”
		}
