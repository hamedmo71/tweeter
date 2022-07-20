package com.mytweeter.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EcryptPassword {
    private String password;

    public EcryptPassword(String password) {
        this.password = password;
    }

    public String encPassword() throws NoSuchAlgorithmException {
        String encryptedpassword = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(password.getBytes());
            byte[] bytes = m.digest();
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            encryptedpassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedpassword;
    }
}

