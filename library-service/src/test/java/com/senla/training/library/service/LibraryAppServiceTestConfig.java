package com.senla.training.library.service;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.senla.training.library")
@EntityScan(basePackages = "com.senla.training.library.entity")
@SpringBootConfiguration
@AutoConfigurationPackage
public class LibraryAppServiceTestConfig {
}
