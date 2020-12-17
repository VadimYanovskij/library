package com.senla.training.library.repository;

import com.senla.training.library.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring JPA Repository for Token
 *
 * @author Vadim Yanovskij
 */
public interface BlockedTokenRepository extends JpaRepository<Token, String> {
}
