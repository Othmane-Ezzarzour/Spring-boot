package com.brightcoding.springboot.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	private final Random random = new SecureRandom();
	private final String alphaNumeric = "0123456789azertyuiopmlkjhgfdsqwxcvbnAZERTYUIOPMLKJHGFDSQWXCVBN";
    public String generateStringId(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(alphaNumeric.charAt(random.nextInt(alphaNumeric.length())));
        }

        return new String(returnValue);
    }
}
