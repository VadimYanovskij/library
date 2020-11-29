package com.senla.training.library.service;

import com.senla.training.library.entity.Token;

public interface BlockedTokenService {

    String findById(String id);

    Token add(Token token);

}
