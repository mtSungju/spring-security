package com.example.securitytest.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data // getter,setter toString ..등 담겨있음
public class JoinDTO {

    private String username;
    private String password;
}
