package ee.erik.core.application.dto;

import lombok.Data;

@Data
public class CreateCitizenDto {
    private String lastName;
    private Long idNumber;
    private String info;
}
