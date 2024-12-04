package com.cn.mutual;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 **/
@MapperScan("com.cn.mutual.mapper") // 配置包扫描
@SpringBootApplication
public class MutualApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutualApplication.class);
    }

}
