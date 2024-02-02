package com.garcia.hotelmanager.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String location;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
	@JsonIgnore
	private List<HotelRoom> hotelRooms;

	public Hotel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<HotelRoom> getHotelRooms() {
		return hotelRooms;
	}

	public void setHotelRooms(List<HotelRoom> hotelRooms) {
		this.hotelRooms = hotelRooms;
	}

	public boolean createHotelRoom(RoomSize roomSize, boolean hasMinibar) {
		HotelRoom hotelRoom = new HotelRoom(roomSize, hasMinibar);
		return hotelRooms.add(hotelRoom);
	}

	public HotelRoom readHotelRoom(Long idHotelRoLong) {
		return null;
	}

	public List<HotelRoom> showListHotelRoom() {
		return null;
	}

	public List<HotelRoom> getFreeHotelRooms() {
		return null;
	}

	public boolean updateRoom(Long idHotelRoom) {
		return false;
	}

	public void deleteHotelRoom() {
	}

}
