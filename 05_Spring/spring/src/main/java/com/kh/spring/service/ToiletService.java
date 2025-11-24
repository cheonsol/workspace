package com.kh.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring.model.dto.ToiletListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ToiletService {

    @Value("${seoul.api.key}")
    private String apiKey;

    private static final String API_URL = "http://openapi.seoul.go.kr:8088";

    public ToiletListResponse getToiletList(int start,int end){

        try {
            //http://openapi.seoul.go.kr:8088/인증키/응답형식/mgisToiletPoi/시작인덱스/끝인덱스/
            String url = String.format("%s/%s/json/mgisToiletPoi/%d/%d", API_URL, apiKey, start, end);

            log.info("url : {}", url);

            RestTemplate restTemplate = new RestTemplate();
            String jsonResponse = restTemplate.getForObject(url, String.class);

            ToiletListResponse result = parseJsonResponse(jsonResponse);

            return result;
        } catch (Exception e) {
            log.error("화장실 목록 조회 실패 ", e);
            return null;
        }
    }

    private ToiletListResponse parseJsonResponse(String jsonResponse) throws Exception {
        //jacson라이브러리의 클래스인 ObjectMapper, JSON <-> 문자열
        ObjectMapper objectMapper = new ObjectMapper();

        //알 수 없는 속성 무시
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //Root에 래핑 해제(JSON의 최상위 키를 자동으로 언래핑)
        objectMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);

        ToiletListResponse response = objectMapper.readValue(jsonResponse, ToiletListResponse.class);

        return response;
    }
}
