package com.example.securitytest.controller;

import com.example.securitytest.dto.JoinDTO;
import com.example.securitytest.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP(){
        return "join";
    }


    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO){

        log.info("joinDto : {}" , joinDTO);

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
