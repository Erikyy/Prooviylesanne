package ee.erik.core.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Event {

    private Long id;
    private String name;
    private Date date;
    private String location;
    private String info;
    private List<Participant> participants;

}
