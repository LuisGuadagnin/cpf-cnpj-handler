package luisguadagnin.cpfcnpj;

import luisguadagnin.cpfcnpj.exceptions.InvalidCpfException;
import org.junit.Assert;
import org.junit.Test;

public class CpfTest {

    @Test
    public void isCpfShouldReturnTrueForRawCpf() {
        // given
        String cpf = "23500580009";

        // when
        boolean isCpf = Cpf.isCpf(cpf);

        // then
        Assert.assertTrue(isCpf);
    }

    @Test
    public void isCpfShouldReturnTrueForFormattedCpf() {
        // given
        String cpf = "235.005.800-09";

        // when
        boolean isCpf = Cpf.isCpf(cpf);

        // then
        Assert.assertTrue(isCpf);
    }

    @Test
    public void isCpfShouldReturnFalseForBadCpf() {
        // given
        String cpf = "23500580009X";

        // when
        boolean isCpf = Cpf.isCpf(cpf);

        // then
        Assert.assertFalse(isCpf);
    }

    @Test
    public void isValidCpfShouldReturnTrueForValidCpf() {
        // given
        String cpf = "23500580009";

        // when
        boolean isValidCpf = Cpf.isValidCpf(cpf);

        // then
        Assert.assertTrue(isValidCpf);
    }

    @Test
    public void isValidCpfShouldReturnFalseForInvalidCpf() {
        // given
        String cpf = "23500580022";

        // when
        boolean isValidCpf = Cpf.isValidCpf(cpf);

        // then
        Assert.assertFalse(isValidCpf);
    }

    @Test
    public void isValidCpfShouldReturnFalseForBadCpf() {
        // given
        String cpf = "23500580009X";

        // when
        boolean isValidCpf = Cpf.isValidCpf(cpf);

        // then
        Assert.assertFalse(isValidCpf);
    }

    // then no exception is thrown
    @Test
    public void shouldBuildSuccesfullyForRawCpf() {
        // given
        String cpf = "23500580009";

        // when
        new Cpf(cpf);
    }

    // then no exception is thrown
    @Test
    public void shouldBuildSuccesfullyForFormattedCpf() {
        // given
        String cpf = "235.005.800-09";

        // when
        new Cpf(cpf);
    }

    //then
    @Test(expected = InvalidCpfException.class)
    public void buildShouldFailForBadCpf() {
        // given
        String cpf = "23500580009X";

        // when
        new Cpf(cpf);
    }

    @Test
    public void getCheckDigitsShouldReturnCheckDigits() {
        // given
        Cpf cpf1 = new Cpf("917.400.510-33");
        Cpf cpf2 = new Cpf("23500580009");

        // when
        String checkDigitsCpf1 = cpf1.getCheckDigits();
        String checkDigitsCpf2 = cpf2.getCheckDigits();

        // then
        Assert.assertEquals("33", checkDigitsCpf1);
        Assert.assertEquals("09", checkDigitsCpf2);
    }

    @Test
    public void toFormattedStringShouldReturnFormattedString() {
        // given
        Cpf cpf = new Cpf("23500580009");

        // when
        String formattedCpf = cpf.toFormattedString();

        // then
        Assert.assertEquals("235.005.800-09", formattedCpf);
    }

    @Test
    public void isValidShouldReturnTrueForValidCpf() {
        // given
        Cpf cpf = new Cpf("23500580009");

        // when
        boolean isValid = cpf.isValid();

        // then
        Assert.assertTrue(isValid);
    }

    @Test
    public void isValidShouldReturnFalseForInvalidCpf() {
        // given
        Cpf cpf = new Cpf("23500580022");

        // when
        boolean isValid = cpf.isValid();

        // then
        Assert.assertFalse(isValid);
    }

    @Test
    public void shouldBeEqualsForRawAndFormattedCpf() {
        // given
        Cpf cpf1 = new Cpf("23500580009");
        Cpf cpf2 = new Cpf("235.005.800-09");

        // when
        boolean areEqual = cpf1.equals(cpf2);

        // then
        Assert.assertTrue(areEqual);
    }

}
