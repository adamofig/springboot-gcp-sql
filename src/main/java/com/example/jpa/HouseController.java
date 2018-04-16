package com.example.jpa;

import com.example.SpringApplication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class HouseController {
    @Autowired
    HouseRepository houseRepository;

    private static final Log LOGGER = LogFactory.getLog(SpringApplication.class);

    @Bean
    public CommandLineRunner houses(HouseRepository houseRepository) {
        return args -> {
            Stream.of(new House("111 8th Av., NYC"),
                    new House("636 Avenue of the Americas, NYC"),
                    new House("White House"),
                    new House("Pentagon"))
                    .forEach(houseRepository::save);

            houseRepository.findAll().forEach(house -> LOGGER.info(house.getAddress()));
        };
    }

    @GetMapping("/getHouses")
    public @ResponseBody
    Iterable<House> getHouse() {
        return houseRepository.findAll();
    }

}

