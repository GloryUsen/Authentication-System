package com.glory.Authentication.utlis;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtil {

    public  static KeyPair generateRsaKey(){
        KeyPair  keyPair;

        try{
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            keyPair =generator.generateKeyPair();

        }catch(Exception exception){
          throw new IllegalStateException();
        }

        return keyPair;
    }
}
