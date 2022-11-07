package ee.erik.backend.impl.rest;

import ee.erik.backend.application.dto.create.CreatePaymentMethodDto;
import ee.erik.backend.application.dto.read.ErrorDto;
import ee.erik.backend.application.dto.read.PaymentMethodDto;
import ee.erik.backend.application.dto.update.UpdatePaymentMethodDto;
import ee.erik.backend.application.managers.PaymentMethodManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/payment_methods")
@Tag(name = "Payment methods / Maksmise viisid", description = "Payment methods API / Maksmiste viiside API")
public class PaymentMethodController {

    private final PaymentMethodManager paymentMethodManager;

    @Autowired
    public PaymentMethodController(PaymentMethodManager paymentMethodManager) {
        this.paymentMethodManager = paymentMethodManager;
    }

    @Operation(summary = "List all payment methods", description = "Returns all payment methods.")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PaymentMethodDto.class))))
    @GetMapping(produces = { "application/json" })
    public Set<PaymentMethodDto> findAll() {
        return this.paymentMethodManager.findAll();
    }

    @Operation(summary = "Get payment method by id / Tagastab maksmisviisi id kaudu", description = "Returns a payment method by id. / Tagastab maksmisviisi id kaudu.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = PaymentMethodDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @GetMapping(path = "/{id}", produces = { "application/json" })
    public PaymentMethodDto findById(@PathVariable Long id) {
        return this.paymentMethodManager.findById(id);
    }

    @Operation(summary = "Create new payment method / Lisab uue maksmise viisi", description = "Creates new payment method / Loob uue maksmise viisi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = PaymentMethodDto.class))),
    })
    @PostMapping(produces = { "application/json" })
    public PaymentMethodDto createPaymentMethod(@RequestBody CreatePaymentMethodDto createPaymentMethodDto) {
        return this.paymentMethodManager.createPaymentMethod(createPaymentMethodDto);
    }


    @Operation(summary = "Updates payment method / Uuendab maksmis viisi", description = "Updates payment method. / Uuendab maksmis viisi.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @PutMapping(path = "/{id}", produces = { "application/json" })
    public PaymentMethodDto updatePaymentMethod(@PathVariable Long id, @RequestBody UpdatePaymentMethodDto updatePaymentMethodDto) {
        return this.paymentMethodManager.updatePaymentMethodDto(id, updatePaymentMethodDto);
    }

    @Operation(summary = "Deletes payment method / Kustutab maksmis viisi", description = "Deletes payment method / Kustutab maksmis viisi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = PaymentMethodDto.class))),
            @ApiResponse(responseCode = "404", description = "Not found. Returs error with status.", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
    })
    @DeleteMapping(path = "/{id}", produces = { "application/json" })
    public void deletePaymentMethod(@PathVariable  Long id) {
        this.paymentMethodManager.deletePaymentMethod(id);
    }
}
