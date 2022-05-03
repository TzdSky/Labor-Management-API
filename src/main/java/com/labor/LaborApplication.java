package com.labor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("com.labor.mapper")
public class LaborApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaborApplication.class, args);
    }

}
