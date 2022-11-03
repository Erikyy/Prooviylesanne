package ee.erik.backend.integration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class DBContext {
    private static final PostgreSQLContainer container;

    static {
        container = new PostgreSQLContainer<>("postgres")
                .withDatabaseName("test_database")
                .withUsername("postgres")
                .withPassword("postgres");
        container.start();

    }


    @DynamicPropertySource
    public static void registerDb(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
