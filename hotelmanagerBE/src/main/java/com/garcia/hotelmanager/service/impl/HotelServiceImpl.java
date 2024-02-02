package com.garcia.hotelmanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.garcia.hotelmanager.dao.HotelRepository;
import com.garcia.hotelmanager.dao.HotelRoomRepository;
import com.garcia.hotelmanager.model.HotelRoom;
import com.garcia.hotelmanager.model.RoomSize;
import com.garcia.hotelmanager.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private HotelRoomRepository hotelRoomRepository;

	@Override
	public List<HotelRoom> showListHotelRoom() {
		return hotelRoomRepository.findAll();
	}

	@Override
	public List<HotelRoom> getFreeHotelRooms() {
		return hotelRoomRepository.findByIsFree(true);
	}

	@Override
	public HotelRoom readHotelRoom(long id) {
		Optional<HotelRoom> optionalHotelRoom = hotelRoomRepository.findById(id);
		return optionalHotelRoom.orElse(null);
	}

	@Override
	public Optional<HotelRoom> createHotelRoom(RoomSize roomSize, boolean hasMinibar) {
		HotelRoom hotelRoom = new HotelRoom(roomSize, hasMinibar);
		return Optional.ofNullable(hotelRoomRepository.save(hotelRoom));
	}

	@Override
	public Optional<HotelRoom> updateHotelRoom(long id, RoomSize roomSize, boolean hasMinibar, boolean isFree) {
		Optional<HotelRoom> optionalHotelRoom = hotelRoomRepository.findById(id);
		if (optionalHotelRoom.isPresent()) {
			HotelRoom hotelRoom = optionalHotelRoom.get();
			hotelRoom.setRoomSize(roomSize);
			hotelRoom.setHasMinibar(hasMinibar);
			hotelRoom.setIsFree(isFree);
			return Optional.ofNullable(hotelRoomRepository.save(hotelRoom));
		}
		return Optional.empty();
	}

	@Override
	public Optional<HotelRoom> updateHotelRoom(long id, HotelRoom updatedRoom) {
		Optional<HotelRoom> optionalHotelRoom = hotelRoomRepository.findById(id);
		if (optionalHotelRoom.isPresent()) {
			HotelRoom hotelRoom = optionalHotelRoom.get();

			if (updatedRoom.getRoomSize() != null) {
				hotelRoom.setRoomSize(updatedRoom.getRoomSize());
			}

			if (updatedRoom.getIsFree() != null) {
				hotelRoom.setIsFree(updatedRoom.getIsFree());
			}

			if (updatedRoom.getHasMinibar() != null) {
				hotelRoom.setHasMinibar(updatedRoom.getHasMinibar());
			}

			return Optional.ofNullable(hotelRoomRepository.save(hotelRoom));
		}
		return Optional.empty();
	}

}
