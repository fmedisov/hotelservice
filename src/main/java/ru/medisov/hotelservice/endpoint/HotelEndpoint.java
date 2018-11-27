package ru.medisov.hotelservice.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.medisov.hotelservice.model.Hotel;
import ru.medisov.hotelservice.service.HotelService;

@RestController
@RequestMapping(value = "/hotelservice")
@Api(value = "/hotelservice", description = "CRUD operations for working with hotels")
public class HotelEndpoint {

    @Autowired
    private HotelService hotelService;

    @PostMapping(value = "/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Adding a new hotel. Accepts JSON of hotel. Returns the id of the new hotel")
    public Long addHotel(@RequestBody Hotel hotel) {
        return hotelService.save(hotel).getId();
    }

    @GetMapping(value = "/hotel/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Getting a hotel by id. Returns JSON Hotel")
    public Hotel getHotel(@PathVariable("hotelId") String hotelId) {
        return hotelService.findById(hotelId);
    }

    @PutMapping(value = "/hotel", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update hotel data. Accepts JSON hotel")
    public Hotel updateHotel(@RequestBody Hotel hotel) {
        return hotelService.saveAndFlush(hotel);
    }

    @DeleteMapping(value = "/hotel/{hotelId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Remove hotel by id")
    public String deleteHotel(@PathVariable("hotelId") String hotelId) {
        return hotelService.deleteById(hotelId);
    }
}