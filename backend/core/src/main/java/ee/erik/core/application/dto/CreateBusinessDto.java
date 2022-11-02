package ee.erik.core.application.dto;

import lombok.Data;

@Data
public class CreateBusinessDto {
    private Long regCode;
    private Long numOfParticipants;
    private String info;
}
