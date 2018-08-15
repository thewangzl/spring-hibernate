package com.thewangzl.sh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;

@Profile("oracle")
@Configuration
@ComponentScan(basePackages="com.thewangzl.sh",includeFilters={@Filter(type=FilterType.ANNOTATION,classes=Repository.class)}, excludeFilters= {@Filter(type=FilterType.REGEX,pattern="com.thewangzl.sh.dao.impl.mysql.*")})
public class OracleConfig {

}
