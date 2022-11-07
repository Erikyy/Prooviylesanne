package ee.erik.backend.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long id;
    private String name;
    private Date date;
    private String location;
    private String info;
}
