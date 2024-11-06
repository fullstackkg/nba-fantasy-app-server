package com.nba.nbafantasyapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class NbaFantasyAppServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NbaFantasyAppServerApplication.class, args);
    }
}
