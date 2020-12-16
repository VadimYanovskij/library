package com.senla.training.library.repository;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootConfiguration
@AutoConfigurationPackage
@ComponentScan("com.senla.training.library")
@EntityScan(basePackages = "com.senla.training.library.entity")
public class LibraryAppRepositoryTestConfig {
}
