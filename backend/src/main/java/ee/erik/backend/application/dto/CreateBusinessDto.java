package ee.erik.backend.application.dto;

import lombok.Data;

@Data
public class CreateBusinessDto {
    private Long regCode;
    private Long numOfParticipants;
    private String info;
}
