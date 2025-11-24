package com.kh.spring.controller;

import com.kh.spring.model.dto.ToiletListResponse;
import com.kh.spring.model.vo.PageInfo;
import com.kh.spring.service.ToiletService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ToiletService toiletService;

//    @Autowired
//    public HomeController(ToiletService toiletService){
//        this.toiletService = toiletService;
//    }

    @RequestMapping("/")
    public String home(@RequestParam(value = "cpage", defaultValue = "1") int currentPage, Model model){
        //공중화장실 목록을 받아서 페이지에 전달
        //http://openapi.seoul.go.kr:8088/인증키/응답형식/mgisToiletPoi/시작인덱스/끝인덱스/
        //http://openapi.seoul.go.kr:8088/48764a4169776c6431313078426c7341/json/mgisToiletPoi/6/15/

        //PageInfo를 사용해서 페이징 처리
        int itemLimit = 10; //한페이지에 보여줄 화장실 갯수
        int pageLimit = 5; //페이징 버튼 갯수

        int startIndex = (currentPage - 1) * itemLimit + 1;
        int endIndex = startIndex + itemLimit - 1;

        ToiletListResponse result = toiletService.getToiletList(startIndex, endIndex);

        PageInfo pi = new PageInfo(currentPage, result.getListTotalCount(), pageLimit, itemLimit);

        model.addAttribute("pi", pi);
        model.addAttribute("toiletList", result.getRows());

        return "index";
    }
}
