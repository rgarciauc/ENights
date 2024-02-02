package com.garcia.hotelmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.garcia.hotelmanager.model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>{

}
