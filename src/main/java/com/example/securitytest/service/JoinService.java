package com.example.securitytest.service;

import com.example.securitytest.dto.JoinDTO;
import com.example.securitytest.entity.UserEntitiy;
import com.example.securitytest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Join;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void joinProcess(JoinDTO joinDTO){

        // db에 이미 동일한 username을 가진 회원이 존재하는가?
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());

        if(isUser){
            log.info("이미 ID 존재함");
            return;
        }

        UserEntitiy data = new UserEntitiy();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_USER");

        log.info("encode_pwd :{}", bCryptPasswordEncoder.encode(joinDTO.getPassword()));

        userRepository.save(data);

    }
}
