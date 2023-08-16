package org.example.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "currency", uniqueConstraints = @UniqueConstraint(columnNames = {"symbol"}))
public class CurrencyEntity {
    @Id
    @Column(name = "cur_id")
    private int curId;
    @NotNull
    private String symbol;
}
