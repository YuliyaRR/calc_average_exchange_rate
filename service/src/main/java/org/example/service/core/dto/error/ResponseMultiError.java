package org.example.service.core.dto.error;

import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ResponseMultiError {
    private ErrorCode logref;
    private List<LocalError> errors;

}
