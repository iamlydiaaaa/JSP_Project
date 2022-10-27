package com.example.reservation.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReservationCntVO {
    Long cno;
    Date resDate;
    Integer currentResCnt;
    Integer capacity;
}
