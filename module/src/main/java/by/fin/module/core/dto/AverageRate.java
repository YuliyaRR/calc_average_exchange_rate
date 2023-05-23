package by.fin.module.core.dto;

import by.fin.module.validator.api.ValidString;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AverageRate {
    @ValidString
    @JsonView(value = Views.Public.class)
    private String symbol;
    @JsonView(value = Views.Public.class)
    private int month;
    @JsonView(value = Views.Internal.class)
    @JsonProperty(value = "average_rate")
    private double averageRate;
}
