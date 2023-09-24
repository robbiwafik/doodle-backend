package com.doodle.application.utils;

import java.util.UUID;


public class UniqueStringGenerator {
    public static String generateUniqueString() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
