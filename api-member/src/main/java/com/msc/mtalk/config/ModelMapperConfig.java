package com.msc.mtalk.config;

import com.msc.mtalk.domain.member.dto.MemberCreateRequest;
import com.msc.mtalk.entity.Member;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public ModelMapper memberMapper(){
        return new ModelMapper();
    }

}
