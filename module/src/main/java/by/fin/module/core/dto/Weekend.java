package by.fin.module.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Weekend {
    private Long weekendId;
    @JsonProperty(value = "calendar_date")
    private LocalDate calendarDate;
    @JsonProperty(value = "is_day_off")
    private boolean isDayOff;
}
