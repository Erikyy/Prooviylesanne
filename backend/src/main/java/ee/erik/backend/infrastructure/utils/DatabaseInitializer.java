package ee.erik.backend.infrastructure.utils;

import ee.erik.backend.infrastructure.persistance.entities.PaymentMethodEntity;
import ee.erik.backend.infrastructure.persistance.repositories.DbPaymentMethodRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initPaymentMethods(DbPaymentMethodRepository paymentMethodRepository){
        return args -> {
            if (paymentMethodRepository.findAllSet().isEmpty()) {
                PaymentMethodEntity cash = new PaymentMethodEntity();
                cash.setMethod("Sularaha");
                PaymentMethodEntity bankTransfer = new PaymentMethodEntity();
                bankTransfer.setMethod("Pangaülekanne");

                paymentMethodRepository.save(cash);
                paymentMethodRepository.save(bankTransfer);
            }
        };
    }
}
