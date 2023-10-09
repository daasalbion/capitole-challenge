package py.com.daas.capitolechallenge.domain.exceptions;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

    public DomainException(final String message) {
        super(message);
    }

}
