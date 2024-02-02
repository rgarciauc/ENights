package com.garcia.hotelmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HotelRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Enumerated(EnumType.STRING)
	private RoomSize roomSize;
	private Boolean hasMinibar;
	private Boolean isFree;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	@JsonIgnore
	private Hotel hotel;
	
	public HotelRoom() {}

	public HotelRoom(RoomSize roomSize, boolean hasMinibar) {
		super();
		this.roomSize = roomSize;
		this.hasMinibar = hasMinibar;
		this.isFree = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RoomSize getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(RoomSize roomSize) {
		this.roomSize = roomSize;
	}

	public Boolean getHasMinibar() {
		return hasMinibar;
	}

	public void setHasMinibar(Boolean hasMinibar) {
		this.hasMinibar = hasMinibar;
	}

	public Boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}
