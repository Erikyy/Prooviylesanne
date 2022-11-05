package ee.erik.backend.application.dto.read;

import lombok.Data;

/**
 * Only used for defining swagger doc error model
 */
@Data
public class ErrorDto {
    private int status;
    private String message;
}
