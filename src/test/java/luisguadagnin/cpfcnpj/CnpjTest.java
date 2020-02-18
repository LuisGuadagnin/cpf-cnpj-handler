package luisguadagnin.cpfcnpj;

import luisguadagnin.cpfcnpj.exceptions.InvalidCnpjException;
import org.junit.Assert;
import org.junit.Test;

public class CnpjTest {

    @Test
    public void isCnpjShouldReturnTrueForRawCnpj() {
        // given
        String cnpj = "82304249000155";

        // when
        boolean isCnpj = Cnpj.isCnpj(cnpj);

        // then
        Assert.assertTrue(isCnpj);
    }

    @Test
    public void isCnpjShouldReturnTrueForFormattedCnpj() {
        // given
        String cnpj = "82.304.249/0001-55";

        // when
        boolean isCnpj = Cnpj.isCnpj(cnpj);

        // then
        Assert.assertTrue(isCnpj);
    }

    @Test
    public void isCnpjShouldReturnFalseForBadCnpj() {
        // given
        String cnpj = "82304249000155X";

        // when
        boolean isCnpj = Cnpj.isCnpj(cnpj);

        // then
        Assert.assertFalse(isCnpj);
    }

    @Test
    public void isValidCnpjShouldReturnTrueForValidCnpj() {
        // given
        String cnpj = "82304249000155";

        // when
        boolean isValidCnpj = Cnpj.isValidCnpj(cnpj);

        // then
        Assert.assertTrue(isValidCnpj);
    }

    @Test
    public void isValidCnpjShouldReturnFalseForInvalidCnpj() {
        // given
        String cnpj = "82304249000100";

        // when
        boolean isValidCnpj = Cnpj.isValidCnpj(cnpj);

        // then
        Assert.assertFalse(isValidCnpj);
    }

    @Test
    public void isValidCnpjShouldReturnFalseForBadCnpj() {
        // given
        String cnpj = "82304249000155X";

        // when
        boolean isValidCnpj = Cnpj.isValidCnpj(cnpj);

        // then
        Assert.assertFalse(isValidCnpj);
    }

    // then no exception is thrown
    @Test
    public void shouldBuildSuccesfullyForRawCnpj() {
        // given
        String cnpj = "82304249000100";

        // when
        new Cnpj(cnpj);
    }

    // then no exception is thrown
    @Test
    public void shouldBuildSuccesfullyForFormattedCnpj() {
        // given
        String cnpj = "82.304.249/0001-55";

        // when
        new Cnpj(cnpj);
    }

    //then
    @Test(expected = InvalidCnpjException.class)
    public void buildShouldFailForBadCnpj() {
        // given
        String cnpj = "82304249000155X";

        // when
        new Cnpj(cnpj);
    }

    @Test
    public void getCheckDigitsShouldReturnCheckDigits() {
        // given
        Cnpj cnpj1 = new Cnpj("82.304.249/0001-55");
        Cnpj cnpj2 = new Cnpj("07111412000148");

        // when
        String checkDigitsCnpj1 = cnpj1.getCheckDigits();
        String checkDigitsCnpj2 = cnpj2.getCheckDigits();

        // then
        Assert.assertEquals("55", checkDigitsCnpj1);
        Assert.assertEquals("48", checkDigitsCnpj2);
    }

    @Test
    public void toFormattedStringShouldReturnFormattedString() {
        // given
        Cnpj cnpj = new Cnpj("07111412000148");

        // when
        String formattedCnpj = cnpj.toFormattedString();

        // then
        Assert.assertEquals("07.111.412/0001-48", formattedCnpj);
    }

    @Test
    public void isValidShouldReturnTrueForValidCnpj() {
        // given
        Cnpj cnpj = new Cnpj("82304249000155");

        // when
        boolean isValid = cnpj.isValid();

        // then
        Assert.assertTrue(isValid);
    }

    @Test
    public void isValidShouldReturnFalseForInvalidCnpj() {
        // given
        Cnpj cnpj = new Cnpj("82304249000100");

        // when
        boolean isValid = cnpj.isValid();

        // then
        Assert.assertFalse(isValid);
    }

    @Test
    public void shouldBeEqualsForRawAndFormattedCnpj() {
        // given
        Cnpj cnpj1 = new Cnpj("82304249000155");
        Cnpj cnpj2 = new Cnpj("82.304.249/0001-55");

        // when
        boolean areEqual = cnpj1.equals(cnpj2);

        // then
        Assert.assertTrue(areEqual);
    }

}
