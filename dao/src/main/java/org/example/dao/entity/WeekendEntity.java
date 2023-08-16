package org.example.dao.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "weekends")
public class WeekendEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weekend_id")
    private Long weekendId;

    @Column(name = "calendar_date")
    private LocalDate calendarDate;

    @Column(name = "is_day_off")
    private boolean isDayOff;
}
