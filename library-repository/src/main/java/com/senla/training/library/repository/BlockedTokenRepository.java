package com.senla.training.library.repository;

import com.senla.training.library.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockedTokenRepository extends JpaRepository<Token, String> {
}
