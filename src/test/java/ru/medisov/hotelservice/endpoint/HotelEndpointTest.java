package ru.medisov.hotelservice.endpoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.medisov.hotelservice.exception.HotelNotFoundException;
import ru.medisov.hotelservice.exception.InvalidHotelException;
import ru.medisov.hotelservice.model.Hotel;
import ru.medisov.hotelservice.service.HotelService;
import ru.medisov.hotelservice.test.TestUtil;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HotelEndpoint.class)
public class HotelEndpointTest {

    private static final String baseUrl = "/hotelservice/hotel/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService service;

    @Test
    public void addHotelShouldReturnHotelId() throws Exception {
        Hotel hotel = TestUtil.defaultTestHotel();
        when(service.save(hotel)).thenReturn(hotel);

        mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.jsonFromObject(hotel)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(hotel.getId().toString()));
    }

    @Test
    public void addHotelAddInvalidHotel() throws Exception {
        Hotel hotel = TestUtil.defaultTestHotel().setName("");
        when(service.save(hotel)).thenThrow(new InvalidHotelException(hotel));

        mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtil.jsonFromObject(hotel)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid hotel model " + hotel));
    }

    @Test
    public void addHotelEmptyRequestBody() throws Exception {
        mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getHotelShouldReturnHotelJson() throws Exception {
        Hotel expectedHotel = TestUtil.defaultTestHotel();

        when(service.findById(expectedHotel.getId().toString())).thenReturn(expectedHotel);
        this.mockMvc.perform(get(baseUrl + "{hotelId}", expectedHotel.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(TestUtil.jsonFromObject(expectedHotel)));
    }

    @Test
    public void getHotelIfHotelNotFound() throws Exception {
        Hotel expectedHotel = TestUtil.defaultTestHotel();

        when(service.findById(expectedHotel.getId().toString())).thenThrow(new HotelNotFoundException(expectedHotel.getId()));
        this.mockMvc.perform(get(baseUrl + "{hotelId}", expectedHotel.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Could not find hotel " + expectedHotel.getId()));
    }

    @Test
    public void updateHotelShouldReturnHotelInfo() throws Exception {
        Hotel hotel = TestUtil.defaultTestHotel();
        String expectedJson = TestUtil.jsonFromObject(hotel);

        when(service.saveAndFlush(hotel)).thenReturn(hotel);
        mockMvc.perform(put(baseUrl).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(expectedJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void updateHotelIdNotFound() throws Exception {
        Hotel hotel = TestUtil.defaultTestHotel();
        String hotelJson = TestUtil.jsonFromObject(hotel);

        when(service.saveAndFlush(hotel)).thenThrow(new HotelNotFoundException(hotel.getId()));
        mockMvc.perform(put(baseUrl).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(hotelJson))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Could not find hotel " + hotel.getId()));
    }

    @Test
    public void updateHotelWithNullIdNotFound() throws Exception {
        Hotel hotel = TestUtil.defaultTestHotel().setId(null);
        String hotelJson = TestUtil.jsonFromObject(hotel);

        when(service.saveAndFlush(hotel)).thenThrow(new HotelNotFoundException(hotel.getId()));
        mockMvc.perform(put(baseUrl).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(hotelJson))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Could not find hotel " + hotel.getId()));
    }

    @Test
    public void deleteHotelShouldReturnHotelInfo() throws Exception {
        Hotel hotel = TestUtil.defaultTestHotel();
        String expectedString = "Removed hotel " + hotel;

        when(service.deleteById(hotel.getId().toString())).thenReturn(expectedString);
        this.mockMvc.perform(delete(baseUrl + "{hotelId}", hotel.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedString));
    }

    @Test
    public void deleteHotelIfHotelNotFound() throws Exception {
        Hotel hotel = TestUtil.defaultTestHotel();

        when(service.deleteById(hotel.getId().toString())).thenThrow(new HotelNotFoundException(hotel.getId()));
        this.mockMvc.perform(delete(baseUrl + "{hotelId}", hotel.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Could not find hotel " + hotel.getId()));
    }
}