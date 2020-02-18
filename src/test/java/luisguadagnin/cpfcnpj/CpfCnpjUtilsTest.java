package luisguadagnin.cpfcnpj;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CpfCnpjUtilsTest {

    @Test
    public void charToIntShouldTransformCharNumberIntoInt() {
        // given
        int ch0 = '0';
        int ch5 = '5';
        int ch9 = '9';

        // when
        int int0 = CpfCnpjUtils.charToInt(ch0);
        int int5 = CpfCnpjUtils.charToInt(ch5);
        int int9 = CpfCnpjUtils.charToInt(ch9);

        // then
        Assert.assertEquals(0, int0);
        Assert.assertEquals(5, int5);
        Assert.assertEquals(9, int9);
    }

    @Test
    public void isCpfOrCnpjShouldReturnTrueForRawCpf() {
        // given
        String rawCpf = "71993826009";
        
        // when
        boolean isCpfOrCnpj = CpfCnpjUtils.isCpfOrCnpj(rawCpf);
        
        // then
        Assert.assertTrue(isCpfOrCnpj);
    }

    @Test
    public void isCpfOrCnpjShouldReturnTrueForFormattedCpf() {
        // given
        String formattedCpf = "719.938.260-09";

        // when
        boolean isCpfOrCnpj = CpfCnpjUtils.isCpfOrCnpj(formattedCpf);

        // then
        Assert.assertTrue(isCpfOrCnpj);
    }

    @Test
    public void isCpfOrCnpjShouldReturnFalseForBadCpf() {
        // given
        String badCpf = "719-938-260.09";

        // when
        boolean isCpfOrCnpj = CpfCnpjUtils.isCpfOrCnpj(badCpf);

        // then
        Assert.assertFalse(isCpfOrCnpj);
    }

    @Test
    public void isCpfOrCnpjShouldReturnTrueForRawCnpj() {
        // given
        String rawCnpj = "29514372000118";

        // when
        boolean isCpfOrCnpj = CpfCnpjUtils.isCpfOrCnpj(rawCnpj);

        // then
        Assert.assertTrue(isCpfOrCnpj);
    }

    @Test
    public void isCpfOrCnpjShouldReturnTrueForFormattedCnpj() {
        // given
        String formattedCnpj = "39.822.529/0001-26";

        // when
        boolean isCpfOrCnpj = CpfCnpjUtils.isCpfOrCnpj(formattedCnpj);

        // then
        Assert.assertTrue(isCpfOrCnpj);
    }

    @Test
    public void isCpfOrCnpjShouldReturnFalseForBadCnpj() {
        // given
        String badCnpj = "39-822-529.0001/26";

        // when
        boolean isCpfOrCnpj = CpfCnpjUtils.isCpfOrCnpj(badCnpj);

        // then
        Assert.assertFalse(isCpfOrCnpj);
    }

    @Test
    public void sumWeightedShouldReturnTheWeightedSum() {
        // given
        List<Integer> digits = Arrays.asList(4, 7, 9, 2);
        List<Integer> weights = Arrays.asList(1, 2, 3, 4);

        // when
        int sum = CpfCnpjUtils.sumWeighted(digits, weights);

        // then
        Assert.assertEquals(53, sum);
    }

}
