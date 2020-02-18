package luisguadagnin.cpfcnpj;

import luisguadagnin.cpfcnpj.exceptions.InvalidCnpjException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Cnpj {

    private static final String RAW_REGEX = "\\d{14}";
    private static final String FORMATTED_REGEX = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}";
    private static final List<String> ALL_CNPJ_REGEX = Arrays.asList(RAW_REGEX, FORMATTED_REGEX);

    private static final List<Integer> WEIGHTS_FIRST_CHECK_DIGIT = Arrays.asList(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
    private static final List<Integer> WEIGHTS_SECOND_CHECK_DIGIT = Arrays.asList(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);

    public static boolean isCnpj(String input) {
        return ALL_CNPJ_REGEX.stream()
                .anyMatch(regex -> Pattern.matches(regex, input));
    }

    public static boolean isValidCnpj(String input) {
        return isCnpj(input) && new Cnpj(input).isValid();
    }

    private final String rawCnpj;

    private final List<Integer> cnpjAsIntegerList;

    public Cnpj(String input) {
        if (!isCnpj(input)) {
            throw new InvalidCnpjException(input);
        }

        rawCnpj = input.replaceAll("[^\\d]", "");
        cnpjAsIntegerList = rawCnpj.chars()
                .map(CpfCnpjUtils::charToInt)
                .boxed().collect(Collectors.toList());
    }

    public String getCheckDigits() {
        return rawCnpj.substring(12);
    }

    public String toRawString() {
        return rawCnpj;
    }

    public String toFormattedString() {
        return rawCnpj.substring(0, 2) +
                "." + rawCnpj.substring(2, 5) +
                "." + rawCnpj.substring(5, 8) +
                "/" + rawCnpj.substring(8, 12) +
                "-" + rawCnpj.substring(12);
    }

    public boolean isValid() {
        String expectedCheckDigits = obtainExpectedFirstCheckDigit() + obtainExpectedSecondCheckDigit();
        return expectedCheckDigits.equals(getCheckDigits());
    }

    private String obtainExpectedFirstCheckDigit() {
        int sum = CpfCnpjUtils.sumWeighted(cnpjAsIntegerList.subList(0, 12), WEIGHTS_FIRST_CHECK_DIGIT);
        int expectedFirstDigit = 11 - (sum % 11);
        return Integer.toString(expectedFirstDigit >= 10 ? 0 : expectedFirstDigit);
    }

    private String obtainExpectedSecondCheckDigit() {
        int sum = CpfCnpjUtils.sumWeighted(cnpjAsIntegerList.subList(0, 13), WEIGHTS_SECOND_CHECK_DIGIT);
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
        Cnpj cpf = (Cnpj) o;
        return rawCnpj.equals(cpf.rawCnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawCnpj);
    }

}
