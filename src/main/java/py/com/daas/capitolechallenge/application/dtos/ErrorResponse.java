package py.com.daas.capitolechallenge.application.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(@JsonProperty("message") String message,
                            @JsonProperty("details") List<String> details) {
    public ErrorResponse(String message) {
        this(message, List.of());
    }
}
