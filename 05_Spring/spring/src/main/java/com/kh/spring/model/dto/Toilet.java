package com.kh.spring.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Toilet {
    @JsonProperty("OBJECTID")
    private int objectId; //순서

    @JsonProperty("ADDR_NEW")
    private String addrNew; //도로명주소

    @JsonProperty("ADDR_OLD")
    private String addrOld; //지번주소

    @JsonProperty("COORD_X")
    private String coordX; // x좌표

    @JsonProperty("COORD_Y")
    private String coordY; //y좌표

    @JsonProperty("CONTS_NAME")
    private String contsName; //건물명

    @JsonProperty("GU_NAME")
    private String guName; //구 명칭

    @JsonProperty("TEL_NO")
    private String telNo; // 전환번호

    @JsonProperty("VALUE_01")
    private String value01; //유형

    @JsonProperty("VALUE_02")
    private String value02; //개방시간

    @JsonProperty("VALUE_03")
    private String value03; //소재지 용도

    @JsonProperty("VALUE_04")
    private String value04; //화장실 현황

    @JsonProperty("VALUE_05")
    private String value05; //장애인화장실 현황

    @JsonProperty("VALUE_06")
    private String value06; //편의시설 (기타설비)

    @JsonProperty("VALUE_07")
    private String value07; //안내표지

    @JsonProperty("VALUE_08")
    private String value08; //소재지

    @JsonProperty("VALUE_09")
    private String value09; //비고
}
