
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



