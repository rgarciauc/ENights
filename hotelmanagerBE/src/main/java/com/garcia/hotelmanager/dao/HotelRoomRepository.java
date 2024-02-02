package com.garcia.hotelmanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.garcia.hotelmanager.model.HotelRoom;

@Repository
public interface HotelRoomRepository extends JpaRepository<HotelRoom, Long> {
	List<HotelRoom> findByIsFree(boolean isFree);
}
