package com.atguigu.funding;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcTest {
    @Test
    public void test1(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        CharSequence rawPassword = "123123";
        for (int i = 0; i < 10; i++) {
            System.out.println(passwordEncoder.encode(rawPassword));
        }
        System.out.println("******************************");

        String passWoardPaa = "$2a$10$8owcKVXqoa7T2WyOD94hauqIZzNBGlKp8Y16Ru1XI6dEU1gkQouiy";
        boolean mathcerResult = passwordEncoder.matches(rawPassword, passWoardPaa);
        System.out.println(mathcerResult);


    }
}
