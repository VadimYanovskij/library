package com.senla.training.library.dto;

import com.senla.training.library.entity.Token;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String token;

    public Token tokenDtoToToken () {
        log.info("Converting TokenDto to Token");
        Token result = new Token(getToken());
        log.info("Token converted successfully");
        return result;
    }
}

