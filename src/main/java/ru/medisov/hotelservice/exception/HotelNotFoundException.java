package ru.medisov.hotelservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HotelNotFoundException extends RuntimeException {

    public HotelNotFoundException(Long id) {
        super("Could not find hotel " + id);
    }
}
