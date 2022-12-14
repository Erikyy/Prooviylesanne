package ee.erik.backend.application.managers;

import ee.erik.backend.application.dto.create.CreatePaymentMethodDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.dto.update.UpdatePaymentMethodDto;
import ee.erik.backend.application.dto.utils.Converters;
import ee.erik.backend.domain.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * A simple implementation class for managing payment methods,
 */
@Component
public class PaymentMethodManagerImpl implements PaymentMethodManager {

    private PaymentMethodService paymentMethodService;

    @Autowired
    public void setPaymentMethodService(PaymentMethodService paymentMethodServiceImpl) {
        this.paymentMethodService = paymentMethodServiceImpl;
    }

    public Set<PaymentMethodDto> findAll() {
        return this.paymentMethodService.findAll().stream().map(Converters::convertPaymentMethodToPaymentMethodDto).collect(Collectors.toSet());
    }

    public PaymentMethodDto findById(Long id) {
        return Converters.convertPaymentMethodToPaymentMethodDto(this.paymentMethodService.findById(id));
    }

    public PaymentMethodDto createPaymentMethod(CreatePaymentMethodDto createPaymentMethodDto) {
        return Converters.convertPaymentMethodToPaymentMethodDto(this.paymentMethodService.save(Converters.convertCreatePaymentMethodDtoToPaymentMethod(createPaymentMethodDto)));
    }

    public PaymentMethodDto updatePaymentMethodDto(Long id, UpdatePaymentMethodDto updatePaymentMethodDto) {
        return Converters.convertPaymentMethodToPaymentMethodDto(this.paymentMethodService.update(Converters.convertUpdatePaymentMethodDtoToPaymentMethod(id, updatePaymentMethodDto)));
    }

    public void deletePaymentMethod(Long paymentMethodId) {
        this.paymentMethodService.delete(paymentMethodId);
    }
}
