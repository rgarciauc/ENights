package com.garcia.hotelmanager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.garcia.hotelmanager.dao.HotelRepository;
import com.garcia.hotelmanager.model.HotelRoom;
import com.garcia.hotelmanager.model.RoomSize;
import com.garcia.hotelmanager.service.HotelService;

@RestController
@RequestMapping("/api/hotel")
@CrossOrigin(origins = "http://localhost:3000")
public class HotelController {
	@Autowired
	private HotelService hotelService;

	@GetMapping("/")
	public String index() {
		return "Greeting from Spring";
	}

	@GetMapping("/rooms")
	public ResponseEntity<List<HotelRoom>> showListHotelRoom() {
		List<HotelRoom> hotelRooms = hotelService.getListHotelRoom();
		if (hotelRooms.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok(hotelRooms);
	    }
	}

	@GetMapping("/rooms/free")
	public List<HotelRoom> getFreeHotelRooms() {
		return hotelService.getFreeHotelRooms();
	}

	@GetMapping("/rooms/{id}")
	public ResponseEntity<HotelRoom> readHotelRoom(@PathVariable long id) {
		HotelRoom hotelRoom = hotelService.readHotelRoom(id);
		return hotelRoom != null ? new ResponseEntity<>(hotelRoom, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping("/rooms")
	public ResponseEntity<HotelRoom> createHotelRoom(@RequestParam RoomSize roomSize,
			@RequestParam boolean hasMinibar) {
		Optional<HotelRoom> createdRoom = hotelService.createHotelRoom(roomSize, hasMinibar);

		if (createdRoom.isPresent()) {
			// TODO: roomDTO could be also used
			return new ResponseEntity<>(createdRoom.get(), HttpStatus.CREATED);

		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/* Converted to RequestBody */
	@PutMapping("/{id}")
	public ResponseEntity<HotelRoom> updateHotelRoom(@PathVariable long id, @RequestParam RoomSize roomSize,
			@RequestParam boolean hasMinibar, @RequestParam boolean isFree) {
		Optional<HotelRoom> updatedRoom = hotelService.updateHotelRoom(id, roomSize, hasMinibar, isFree);
		if (updatedRoom.isPresent()) {
			return new ResponseEntity<>(updatedRoom.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/rooms/{id}")
	public ResponseEntity<HotelRoom> updateHotelRoom(@PathVariable long id, @RequestBody HotelRoom updatedRoom) {
		Optional<HotelRoom> updatedHotelRoom = hotelService.updateHotelRoom(id, updatedRoom);
		if (updatedHotelRoom.isPresent()) {
			return new ResponseEntity<HotelRoom>(updatedHotelRoom.get(), HttpStatus.OK);
		}
		return null;
	}
}
