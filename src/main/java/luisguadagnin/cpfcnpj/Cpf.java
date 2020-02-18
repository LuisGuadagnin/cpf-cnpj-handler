package luisguadagnin.cpfcnpj;

import luisguadagnin.cpfcnpj.exceptions.InvalidCpfException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Cpf {

    private static final String RAW_REGEX = "\\d{11}";
    private static final String FORMATTED_REGEX = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
    private static final List<String> ALL_CPF_REGEX = Arrays.asList(RAW_REGEX, FORMATTED_REGEX);

    private static final List<Integer> WEIGHTS_FIRST_CHECK_DIGIT = Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2);
    private static final List<Integer> WEIGHTS_SECOND_CHECK_DIGIT = Arrays.asList(11, 10, 9, 8, 7, 6, 5, 4, 3, 2);

    public static boolean isCpf(String input) {
        return ALL_CPF_REGEX.stream()
                .anyMatch(regex -> Pattern.matches(regex, input));
    }

    public static boolean isValidCpf(String input) {
        return isCpf(input) && new Cpf(input).isValid();
    }

    private final String rawCpf;

    private final List<Integer> cpfAsIntegerList;

    public Cpf(String input) {
        if (!isCpf(input)) {
            throw new InvalidCpfException(input);
        }

        rawCpf = input.replaceAll("[^\\d]", "");
        cpfAsIntegerList = rawCpf.chars()
                .map(CpfCnpjUtils::charToInt)
                .boxed().collect(Collectors.toList());
    }

    public String getCheckDigits() {
        return rawCpf.substring(9);
    }

    public String toRawString() {
        return rawCpf;
    }

    public String toFormattedString() {
        return rawCpf.substring(0, 3) +
                "." + rawCpf.substring(3, 6) +
                "." + rawCpf.substring(6, 9) +
                "-" + rawCpf.substring(9);
    }

    public boolean isValid() {
        String expectedCheckDigits = obtainExpectedFirstCheckDigit() + obtainExpectedSecondCheckDigit();
        return expectedCheckDigits.equals(getCheckDigits());
    }

    private String obtainExpectedFirstCheckDigit() {
        int sum = CpfCnpjUtils.sumWeighted(cpfAsIntegerList.subList(0, 9), WEIGHTS_FIRST_CHECK_DIGIT);
        int expectedFirstDigit = 11 - (sum % 11);
        return Integer.toString(expectedFirstDigit >= 10 ? 0 : expectedFirstDigit);
    }

    private String obtainExpectedSecondCheckDigit() {
        int sum = CpfCnpjUtils.sumWeighted(cpfAsIntegerList.subList(0, 10), WEIGHTS_SECOND_CHECK_DIGIT);
        int expectedSecondDigit = 11 - (sum % 11);
        return Integer.toString(expectedSecondDigit >= 10 ? 0 : expectedSecondDigit);
    }

    @Override
    public String toString() {
        return toRawString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cpf cpf = (Cpf) o;
        return rawCpf.equals(cpf.rawCpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawCpf);
    }

}
