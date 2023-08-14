package com.smartCode.ecommerce.util.codeGenerator;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RandomGenerator {

    private final static String NUMERIC = "0123456789";
    private final static SecureRandom rnd = new SecureRandom();

    public static String generateNumericString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(NUMERIC.charAt(rnd.nextInt(NUMERIC.length())));
        }
        return sb.toString();
    }
}