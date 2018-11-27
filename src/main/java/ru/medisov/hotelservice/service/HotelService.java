package ru.medisov.hotelservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.medisov.hotelservice.exception.HotelNotFoundException;
import ru.medisov.hotelservice.exception.InvalidHotelException;
import ru.medisov.hotelservice.model.Hotel;
import ru.medisov.hotelservice.repository.HotelRepository;

import java.util.Optional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Hotel findById(String hotelId) {
        Long id = Long.parseLong(hotelId);
        return hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
    }

    public Hotel save(Hotel newHotel) {
        if (isValid(newHotel)) {
            Optional<Hotel> optional = newHotel.getId() == null
                    ? Optional.empty()
                    : hotelRepository.findById(newHotel.getId());

            return optional
                    .map(hotel -> hotelRepository.saveAndFlush(hotel
                            .setName(newHotel.getName())
                            .setRating(newHotel.getRating())))
                    .orElseGet(() -> hotelRepository.save(newHotel));
        } else {
            throw new InvalidHotelException(newHotel);
        }
    }

    public Hotel saveAndFlush(Hotel updated) {
        if (isValid(updated)) {
            if (updated.getId() == null) {
                throw new HotelNotFoundException(null);
            } else {
                return hotelRepository.saveAndFlush(
                        findById(updated.getId().toString())
                        .setName(updated.getName())
                        .setRating(updated.getRating())
                );
            }
        } else {
            throw new InvalidHotelException(updated);
        }
    }

    public String deleteById(String hotelId) {
        Long id = Long.parseLong(hotelId);
        Optional<Hotel> optional = hotelRepository.findById(id);

        if (optional.isPresent()) {
            hotelRepository.deleteById(id);
        }

        return optional.map(hotel -> "Removed hotel " + hotel).orElseThrow(() -> new HotelNotFoundException(id));
    }

    private boolean isValid(Hotel hotel) {
        return hotel != null
                && hotel.getName() != null
                && !hotel.getName().isEmpty();
    }
}
