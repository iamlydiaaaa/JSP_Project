package com.example.reservation.vo;

import java.util.Date;

public class Reservation_Culture_VO {
    private Long rc_no; //pk
    private String id; // 회원 아이디 fk
    private Long cno; // 행사번호 fk
    private int resCount; //~cno.capacity
    private int resPrice;
    private Date resDate;
    private Date regDate;
}
