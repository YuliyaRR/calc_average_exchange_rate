package by.fin.module.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
    @JsonProperty(value = "Cur_ID")
    private int id;
    @JsonProperty(value = "Cur_Abbreviation")
    private String symbol;
    @JsonProperty(value = "Cur_OfficialRate")
        private double rate;
    @JsonProperty(value = "Date")
    private LocalDate date;
}
