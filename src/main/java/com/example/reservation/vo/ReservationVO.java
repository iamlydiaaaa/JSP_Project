package com.example.reservation.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReservationVO {

    private Long rno;//pk
    private String id;//유저아이디 fk
    private Long rc_no; //예약행사아이디 fk
}
