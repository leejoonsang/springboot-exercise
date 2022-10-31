package com.springboot.api.parser;

import com.springboot.api.domain.Hospital;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserFactory {
    @Bean
    public ReadLineContext<Hospital> readLineContext() {
        return new ReadLineContext<Hospital>(new HospitalParser());
    }
}
