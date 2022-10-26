package com.example.reservation.vo;

import com.example.culture.vo.CultureVO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReservationVO {

    private Long rno;//pk
    private String id;//유저아이디 fk
    private Date resDate;//예약희망날짜
    //조회를 위한 멤버들
    private Integer resPrice;//res_culture 예약당시 결제금액
    private Date regDate;//res_culture 예약 등록일
    private CultureVO culture;
}
