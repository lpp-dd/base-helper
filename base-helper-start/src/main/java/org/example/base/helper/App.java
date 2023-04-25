package org.example.base.helper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author panfudong
 * @description
 */
@SpringBootApplication(scanBasePackages = {"org.example.base.helper"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
