package ru.medisov.hotelservice.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.medisov.hotelservice.model.Hotel;

public class TestUtil {

    public static Hotel defaultTestHotel() {
        Long hotelId = 1L;
        Long hotelRating = 5L;

        return new Hotel().setId(hotelId).setName("Mariott").setRating(hotelRating);
    }

    public static String jsonFromObject(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        return ow.writeValueAsString(object);
    }
}
