package com.amit.springsercurity.domain;

import com.amit.springsercurity.redis.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenStore {
    @Autowired
    TokenRepository tokenRepository;


}
