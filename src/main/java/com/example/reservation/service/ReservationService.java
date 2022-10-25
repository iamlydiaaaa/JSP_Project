package com.example.reservation.service;

import java.util.Date;

public interface ReservationService {
    Long reservation(String id, Long cno, Date resDate); //id,cno <-유저id,문화행사id
}
