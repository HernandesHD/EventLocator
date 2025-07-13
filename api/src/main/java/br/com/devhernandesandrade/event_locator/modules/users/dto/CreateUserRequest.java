package br.com.devhernandesandrade.event_locator.modules.users.dto;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;
    private String email;
    private String password;


}
