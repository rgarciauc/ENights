package com.garcia.hotelmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.garcia.hotelmanager.dao.HotelRepository;
import com.garcia.hotelmanager.dao.HotelRoomRepository;
import com.garcia.hotelmanager.model.RoomSize;
import com.garcia.hotelmanager.service.HotelService;

@Component
public class DataInitializer implements CommandLineRunner {

	public static final Long HOTEL_ID = 101L;

	@Autowired
	private HotelRoomRepository hotelRoomRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private HotelService hotelService;

	@Override
	public void run(String... args) throws Exception {

		hotelRoomRepository.deleteAll();
		hotelRepository.deleteAll();

		hotelService.createHotel(HOTEL_ID, "eXXellent Nights!");

		hotelService.createHotelRoom(RoomSize.DOUBLE, true);
		hotelService.createHotelRoom(RoomSize.SINGLE, true);
		hotelService.createHotelRoom(RoomSize.SUITE, false);
	}

}
