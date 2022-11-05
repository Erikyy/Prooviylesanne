package ee.erik.backend.application.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDto {
    private String name;
    private Date date;
    private String location;
    private String info;
}
