package ru.medisov.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.medisov.hotelservice.model.Hotel;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
