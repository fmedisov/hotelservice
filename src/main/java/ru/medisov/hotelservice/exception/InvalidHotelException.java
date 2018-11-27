package ru.medisov.hotelservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.medisov.hotelservice.model.Hotel;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidHotelException extends RuntimeException {

    public InvalidHotelException(Hotel hotel) {
        super("Invalid hotel model " + hotel);
    }
}
