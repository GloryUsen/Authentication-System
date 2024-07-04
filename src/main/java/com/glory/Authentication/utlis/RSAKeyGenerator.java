package com.glory.Authentication.utlis;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@AllArgsConstructor

@Getter
@Setter
public class RSAKeyGenerator {

    public RSAPrivateKey rsaPrivateKey;

    public RSAPublicKey rsaPublicKey;

    public RSAKeyGenerator() {
        KeyPair keyPair = KeyGeneratorUtil.generateRsaKey();
        this.rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        this.rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
    }
}
