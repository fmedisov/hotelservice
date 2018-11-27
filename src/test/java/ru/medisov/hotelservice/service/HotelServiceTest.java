package ru.medisov.hotelservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.medisov.hotelservice.exception.HotelNotFoundException;
import ru.medisov.hotelservice.exception.InvalidHotelException;
import ru.medisov.hotelservice.model.Hotel;
import ru.medisov.hotelservice.repository.HotelRepository;
import ru.medisov.hotelservice.test.TestUtil;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class HotelServiceTest {

    @TestConfiguration
    static class HotelServiceTestContextConfiguration {

        @Bean
        public HotelService hotelService() {
            return new HotelService();
        }
    }

    @Autowired
    private HotelService service;

    @MockBean
    private HotelRepository repository;

    @Test
    public void addHotelShouldReturnExistingHotelId() {
        Hotel expectedHotel = TestUtil.defaultTestHotel();
        when(repository.findById(expectedHotel.getId())).thenReturn(Optional.of(expectedHotel));
        when(repository.saveAndFlush(expectedHotel)).thenReturn(expectedHotel);

        Hotel actualHotel = service.save(expectedHotel);

        assertEquals(expectedHotel, actualHotel);
    }

    @Test
    public void addHotelShouldReturnNewHotelId() {
        Hotel newHotel = TestUtil.defaultTestHotel();
        Hotel expectedHotel = TestUtil.defaultTestHotel().setId(2L);
        when(repository.findById(newHotel.getId())).thenReturn(Optional.empty());
        when(repository.save(newHotel)).thenReturn(expectedHotel);

        Hotel actualHotel = service.save(newHotel);

        assertEquals(expectedHotel, actualHotel);
    }

    @Test(expected = InvalidHotelException.class)
    public void addHotelAddInvalidHotel() {
        Hotel hotel = TestUtil.defaultTestHotel().setName("");
        service.save(hotel);
    }

    @Test
    public void getHotelShouldReturnHotel() {
        Hotel expectedHotel = TestUtil.defaultTestHotel();
        when(repository.findById(expectedHotel.getId())).thenReturn(Optional.of(expectedHotel));

        Hotel actualHotel = service.findById(expectedHotel.getId().toString());

        assertEquals(expectedHotel, actualHotel);
    }

    @Test(expected = HotelNotFoundException.class)
    public void getHotelIfHotelNotFound() {
        Hotel hotel = TestUtil.defaultTestHotel();
        service.findById(hotel.getId().toString());
    }

    @Test
    public void updateHotelShouldReturnSameHotel() {
        Hotel expectedHotel = TestUtil.defaultTestHotel();
        when(repository.saveAndFlush(expectedHotel)).thenReturn(expectedHotel);
        when(repository.findById(expectedHotel.getId())).thenReturn(Optional.of(expectedHotel));

        Hotel actualHotel = service.saveAndFlush(expectedHotel);

        assertEquals(expectedHotel, actualHotel);
    }

    @Test(expected = HotelNotFoundException.class)
    public void updateHotelIdNotFound() {
        Hotel hotel = TestUtil.defaultTestHotel();
        when(repository.findById(hotel.getId())).thenReturn(Optional.empty());
        service.saveAndFlush(hotel);
    }

    @Test(expected = InvalidHotelException.class)
    public void updateHotelInvalidHotel() {
        Hotel hotel = TestUtil.defaultTestHotel().setName("");
        service.saveAndFlush(hotel);
    }

    @Test(expected = HotelNotFoundException.class)
    public void updateHotelWithNullIdNotFound() {
        Hotel hotel = TestUtil.defaultTestHotel().setId(null);
        service.saveAndFlush(hotel);
    }

    @Test
    public void deleteHotelShouldReturnHotelInfo() {
        Hotel hotel = TestUtil.defaultTestHotel();
        String expectedString = "Removed hotel " + hotel;
        when(repository.findById(hotel.getId())).thenReturn(Optional.of(hotel));

        String actualString = service.deleteById(hotel.getId().toString());

        assertEquals(expectedString, actualString);
    }

    @Test(expected = HotelNotFoundException.class)
    public void deleteHotelIfHotelNotFound() {
        Hotel hotel = TestUtil.defaultTestHotel();
        when(repository.findById(hotel.getId())).thenReturn(Optional.empty());

        service.deleteById(hotel.getId().toString());
    }
}