package luisguadagnin.cpfcnpj.exceptions;

import lombok.Getter;

public class InvalidCpfException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Invalid CPF: %s";

    @Getter
    private final String input;

    public InvalidCpfException(String input) {
        super(String.format(DEFAULT_MESSAGE, input));
        this.input = input;
    }

    public InvalidCpfException(String message, String input) {
        super(message);
        this.input = input;
    }

}
