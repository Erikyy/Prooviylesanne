package ee.erik.core.application.managers.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreateEventDto {
    private String name;
    private Date date;
    private String location;
    private String info;
}
