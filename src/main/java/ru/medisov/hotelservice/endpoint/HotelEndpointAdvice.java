package ru.medisov.hotelservice.endpoint;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.medisov.hotelservice.exception.HotelNotFoundException;
import ru.medisov.hotelservice.exception.InvalidHotelException;

@ControllerAdvice
class HotelEndpointAdvice {

    @ResponseBody
    @ExceptionHandler(HotelNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String hotelNotFoundHandler(HotelNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InvalidHotelException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidHotelHandler(InvalidHotelException ex) {
        return ex.getMessage();
    }
}