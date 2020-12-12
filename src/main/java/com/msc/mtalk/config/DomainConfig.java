package com.msc.mtalk.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"com.msc.mtalk.entity"})
@EnableJpaRepositories(basePackages = {"com.msc.mtalk.domain.member"})
@EnableTransactionManagement
public class DomainConfig {
}
