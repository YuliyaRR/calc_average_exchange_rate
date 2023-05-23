package by.fin.module.core.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Month {
    private String name;
    private int serialNumber;
    private int amountDays;

}
