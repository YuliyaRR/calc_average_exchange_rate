package by.fin.module.entity;

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
@Table(name = "rates", uniqueConstraints = @UniqueConstraint(columnNames = {"currency_id", "date"}))
public class RateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;
    private double rate;
    private LocalDate date;
}
