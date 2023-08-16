package org.example.service.core.dto.error;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class LocalError {
    private String field;
    private String message;
}
