package org.example.service.core.dto.error;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ResponseSingleError {
    private ErrorCode logref;
    private String message;
}
