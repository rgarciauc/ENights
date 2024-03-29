package com.garcia.hotelmanager.service;

import java.util.List;
import java.util.Optional;

import com.garcia.hotelmanager.model.Hotel;
import com.garcia.hotelmanager.model.HotelRoom;
import com.garcia.hotelmanager.model.RoomSize;

public interface HotelService {
	public List<HotelRoom> getListHotelRoom();

	public List<HotelRoom> getFreeHotelRooms();

	public HotelRoom readHotelRoom(long id);
	
	public Optional<Hotel> createHotel(long id, String name);

	public Optional<HotelRoom> createHotelRoom(RoomSize roomSize, boolean hasMinibar);

	public Optional<HotelRoom> updateHotelRoom(long id, RoomSize roomSize, boolean hasMinibar, boolean isFree);

	public Optional<HotelRoom> updateHotelRoom(long id, HotelRoom updatedRoom);
}
