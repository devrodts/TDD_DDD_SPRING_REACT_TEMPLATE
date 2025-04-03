package com.devrodts.commerce.commerce;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.List;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration implements DisposableBean {

    private final List<AutoCloseable> containers = new ArrayList<>();

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        PostgreSQLContainer<?> container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
        containers.add(container);
        return container;
    }

    @Bean
    @ServiceConnection(name = "redis")
    GenericContainer<?> redisContainer() {
        GenericContainer<?> container = new GenericContainer<>(DockerImageName.parse("redis:latest")).withExposedPorts(6379);
        containers.add(container);
        return container;
    }

    @Override
    public void destroy() throws Exception {
        for (AutoCloseable container : containers) {
            container.close();
        }
    }
}
