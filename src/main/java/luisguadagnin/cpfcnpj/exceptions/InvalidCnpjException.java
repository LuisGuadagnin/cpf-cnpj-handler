package luisguadagnin.cpfcnpj.exceptions;

import lombok.Getter;

public class InvalidCnpjException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Invalid CNPJ: %s";

    @Getter
    private final String input;

    public InvalidCnpjException(String input) {
        super(String.format(DEFAULT_MESSAGE, input));
        this.input = input;
    }

    public InvalidCnpjException(String message, String input) {
        super(message);
        this.input = input;
    }

}
