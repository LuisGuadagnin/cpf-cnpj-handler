package luisguadagnin.cpfcnpj;

import java.util.List;

public class CpfCnpjUtils {

    static int charToInt(int ch) {
        return ch - '0';
    }

    static int sumWeighted(List<Integer> digits, List<Integer> weights) {
        int sum = 0;
        for (int i = 0; i < digits.size(); i++) {
            sum += digits.get(i) * weights.get(i);
        }
        return sum;
    }

    public static boolean isCpfOrCnpj(String input) {
        return Cpf.isCpf(input) || Cnpj.isCnpj(input);
    }

    private CpfCnpjUtils() {} // prevents instantiation

}
