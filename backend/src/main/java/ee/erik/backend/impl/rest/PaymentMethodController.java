package ee.erik.backend.impl.rest;

import ee.erik.backend.application.dto.create.CreatePaymentMethodDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.managers.PaymentMethodManager;
import ee.erik.backend.domain.entities.participant.PaymentMethod;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class PaymentMethodController {

    private PaymentMethodManager paymentMethodManager;

    public PaymentMethodController(PaymentMethodManager paymentMethodManager) {
        this.paymentMethodManager = paymentMethodManager;
    }

    @GetMapping("/payment_methods")
    public Set<PaymentMethodDto> findAll() {
        return this.paymentMethodManager.findAll();
    }

    @PostMapping("/payment_methods")
    public PaymentMethodDto createPaymentMethod(@RequestBody CreatePaymentMethodDto createPaymentMethodDto) {
        return this.paymentMethodManager.createPaymentMethod(createPaymentMethodDto);
    }

    @DeleteMapping("/payment_methods/{id}")
    public void deletePaymentMethod(Long id) {
        this.paymentMethodManager.deletePaymentMethod(id);
    }
}
