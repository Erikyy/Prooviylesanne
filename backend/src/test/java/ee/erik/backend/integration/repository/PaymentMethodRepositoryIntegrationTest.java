package ee.erik.backend.integration.repository;

import ee.erik.backend.domain.entities.PaymentMethod;
import ee.erik.backend.domain.repositories.PaymentMethodRepository;
import ee.erik.backend.infrastructure.persistance.entities.participant.PaymentMethodEntity;
import ee.erik.backend.infrastructure.persistance.repositories.DbPaymentMethodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentMethodRepositoryIntegrationTest {
    @Container
    static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14");


    @DynamicPropertySource
    static void registerDb(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Autowired
    private DbPaymentMethodRepository dbPaymentMethodRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Test
    public void shouldSavePaymentMethodAndContain() {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethod("Test Method");

        PaymentMethod saved = this.paymentMethodRepository.save(paymentMethod);

        assertThat(this.dbPaymentMethodRepository.findAllSet()).isNotEmpty();
        assertThat(this.dbPaymentMethodRepository.findAllSet()).contains(PaymentMethodEntity.toEntity(saved));
    }

}
