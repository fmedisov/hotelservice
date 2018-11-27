# Api Documentation


## Overview
Api Documentation


### Version information
*Version* : 1.0


### License information
*License* : Apache 2.0  
*License URL* : http://www.apache.org/licenses/LICENSE-2.0  
*Terms of service* : urn:tos


### URI scheme
*Host* : localhost:8080  
*BasePath* : /


### Tags

* hotel-endpoint : CRUD operations for working with hotels

## Documentation for API Endpoints

All URIs are relative to *https://localhost:8080*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*HotelEndpoint* | [**addHotel**](#addHotel) | **POST** /hotelservice/hotel | Adding a new hotel. Accepts JSON of hotel. Returns the id of the new hotel
*HotelEndpoint* | [**deleteHotel**](#removeHotel) | **DELETE** /hotelservice/hotel/{hotelId} | Remove hotel by id
*HotelEndpoint* | [**getHotel**](#getHotel) | **GET** /hotelservice/hotel/{hotelId} | Getting a hotel by id. Returns JSON Hotel
*HotelEndpoint* | [**updateHotel**](#updateHotel) | **PUT** /hotelservice/hotel | Update hotel data. Accepts JSON hotel


## Documentation for Models

 - [Hotel](HOTEL.md)
 
 
 
## Paths

<a name="addHotel"></a>
### Adding a new hotel. Accepts JSON of hotel. Returns the id of the new hotel

```
POST /hotelservice/hotel
```

#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**hotel**  <br>*required*|hotel|[Hotel](#hotel)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|Long|
|**400**|Invalid hotel model|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* hotel-endpoint

<a name="updateHotel"></a>
### Update hotel data. Accepts JSON hotel
```
PUT /hotelservice/hotel
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**hotel**  <br>*required*|hotel|[Hotel](#hotel)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Hotel](#hotel)|
|**400**|Invalid hotel model|No Content|


#### Consumes

* `application/json`


#### Produces

* `application/json`


#### Tags

* hotel-endpoint

<a name="getHotel"></a>
### Getting a hotel by id. Returns JSON Hotel
```
GET /hotelservice/hotel/{hotelId}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**hotelId**  <br>*required*|hotelId|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[Hotel](#hotel)|


#### Produces

* `application/json`


#### Tags

* hotel-endpoint

<a name="removeHotel"></a>
### Remove hotel by id
```
DELETE /hotelservice/hotel/{hotelId}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**hotelId**  <br>*required*|hotelId|string|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|string|


#### Produces

* `application/json`


#### Tags

* hotel-endpoint

