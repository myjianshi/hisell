package edu.gyc.hisell;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("edu.gyc.hisell.dao")
@SpringBootApplication
public class HisellApplication {

    public static void main(String[] args) {
        SpringApplication.run(HisellApplication.class, args);
    }

}
