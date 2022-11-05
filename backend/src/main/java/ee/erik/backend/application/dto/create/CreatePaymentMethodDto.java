package ee.erik.backend.application.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentMethodDto {
    private String method;
}
