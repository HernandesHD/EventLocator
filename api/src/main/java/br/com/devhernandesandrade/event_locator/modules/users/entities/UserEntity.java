package br.com.devhernandesandrade.event_locator.modules.users.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserEntity {

    private Long id;
    private String name;
    private String email;
    private String password;
    private RoleUser role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
