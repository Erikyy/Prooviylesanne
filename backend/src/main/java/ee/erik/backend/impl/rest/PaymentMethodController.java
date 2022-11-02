package ee.erik.backend.impl.rest;

import ee.erik.backend.application.dto.CreatePaymentMethodDto;
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
    public Set<PaymentMethod> findAll() {
        return this.paymentMethodManager.findAll();
    }

    @PostMapping("/payment_methods")
    public PaymentMethod createPaymentMethod(@RequestBody CreatePaymentMethodDto createPaymentMethodDto) {
        return this.paymentMethodManager.createPaymentMethod(createPaymentMethodDto);
    }

    @DeleteMapping("/payment_methods")
    public void deletePaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethodManager.deletePaymentMethod(paymentMethod);
    }
}
