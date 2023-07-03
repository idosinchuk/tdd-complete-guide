/**package com.idosinchuk.tddcompleteguide.infraestructure.config;

import com.idosinchuk.tddcompleteguide.infraestructure.persistence.mapper.AddressMapper;
import com.idosinchuk.tddcompleteguide.infraestructure.persistence.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public AddressMapper addressMapper() {
        return Mappers.getMapper(AddressMapper.class);
    }

}
*/