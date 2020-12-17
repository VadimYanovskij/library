package com.senla.training.library.service.impl;

import com.senla.training.library.entity.Token;
import com.senla.training.library.repository.BlockedTokenRepository;
import com.senla.training.library.service.BlockedTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Vadim Yanovskij
 */
@Slf4j
@Service
public class BlockedTokenServiceImpl implements BlockedTokenService {

    private final BlockedTokenRepository blockedTokenRepository;

    public BlockedTokenServiceImpl(BlockedTokenRepository blockedTokenRepository) {
        this.blockedTokenRepository = blockedTokenRepository;
    }

    /**
     * Finds a token in the database by id.
     * If token not found return empty string.
     *
     * @return String
     */
    @Override
    public String findById(String id) {
        log.info("Finding token with id = {} in database", id);
        Optional<Token> result = blockedTokenRepository.findById(id);
        if (result.isPresent()){
            log.info("Token found in database");
            return result.get().toString();
        } else {
            log.info("Token not found in database");
            return "";
        }
    }

    /**
     * Save the token to the database
     *
     * @return saved Token
     */
    @Override
    public Token add(Token token) {
        log.info("Creating in database blocked token: {}", token);
        Token result = blockedTokenRepository.save(token);
        log.info("Blocked token created in database successfully");
        return result;
    }
}
