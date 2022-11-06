package ee.erik.backend.application.managers;

import ee.erik.backend.application.dto.create.CreatePaymentMethodDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.dto.update.UpdatePaymentMethodDto;

import java.util.Set;

/**
 *
 */
public interface PaymentMethodManager {
    Set<PaymentMethodDto> findAll();

    PaymentMethodDto findById(Long id);

    PaymentMethodDto createPaymentMethod(CreatePaymentMethodDto createPaymentMethodDto);

    PaymentMethodDto updatePaymentMethodDto(Long id, UpdatePaymentMethodDto updatePaymentMethodDto);

    void deletePaymentMethod(Long paymentMethodId);
}
