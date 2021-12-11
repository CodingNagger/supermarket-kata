package kata.supermarket;


import kata.supermarket.offer.ProductPercentOffer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static kata.supermarket.TestFixture.aPackOfDigestives;
import static kata.supermarket.TestFixture.aPintOfMilk;
import static kata.supermarket.TestFixture.getSeventyFivePercentOffMilkAndDigestivesOffer;
import static kata.supermarket.TestFixture.twoHundredGramsOfPickAndMix;
import static kata.supermarket.TestFixture.twoPintsOfMilkForOneOffer;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductPercentOfferTest {

    @Test
    void applicableItems_shouldReturnExpectedOfferItems_whenAllItemsMatch() {
        Item milk = aPintOfMilk();
        Item digestives = aPackOfDigestives();

        ProductPercentOffer offer = getSeventyFivePercentOffMilkAndDigestivesOffer();

        List<Item> expectedItems = Arrays.asList(
                milk,
                digestives);

        List<Item> items = Arrays.asList(milk, digestives, twoHundredGramsOfPickAndMix());

        List<Item> result = offer.applicableItems(items);

        assertThat(result)
                .isNotNull()
                .containsExactlyInAnyOrderElementsOf(expectedItems);
    }

    @Test
    void applicableItems_shouldReturnNothing_whenNotAllItemsAllMatch() {
        Item milk = aPintOfMilk();

        ProductPercentOffer offer = getSeventyFivePercentOffMilkAndDigestivesOffer();

        List<Item> items = Arrays.asList(milk, twoHundredGramsOfPickAndMix());

        List<Item> result = offer.applicableItems(items);

        assertThat(result)
                .isNotNull()
                .isEmpty();
    }

    @Test
    void calculateDeduction_shouldReturnExpectedDeduction_whenAllItemsMatch() {
        Item milk = aPintOfMilk();
        Item digestives = aPackOfDigestives();

        ProductPercentOffer offer = getSeventyFivePercentOffMilkAndDigestivesOffer();

        List<Item> items = Arrays.asList(milk, digestives, twoHundredGramsOfPickAndMix());

        BigDecimal result = offer.calculateDeduction(items);

        assertThat(result)
                .isNotNull()
                .isEqualByComparingTo("1.53");
    }

    @Test
    void calculateDeduction_shouldReturnExpectedDeduction_forTwoPintsOfMilkBoughtForPriceOfOne() {
        Item twoPintsOfMilk = aPintOfMilk().withQuantity(BigDecimal.valueOf(2));

        ProductPercentOffer offer = twoPintsOfMilkForOneOffer();

        List<Item> items = Arrays.asList(twoPintsOfMilk, twoHundredGramsOfPickAndMix());

        BigDecimal result = offer.calculateDeduction(items);

        assertThat(result)
                .isNotNull()
                .isEqualByComparingTo("0.49");
    }

    @Test
    void calculateDeduction_shouldReturnExpectedDeduction_forTwoPintsOfMilkBoughtForPriceOfOne_whenGettingFourPints() {
        Item fourPintsOfMilk = aPintOfMilk().withQuantity(BigDecimal.valueOf(4));

        ProductPercentOffer offer = twoPintsOfMilkForOneOffer();

        List<Item> items = Arrays.asList(fourPintsOfMilk, twoHundredGramsOfPickAndMix());

        BigDecimal result = offer.calculateDeduction(items);

        assertThat(result)
                .isNotNull()
                .isEqualByComparingTo("0.98");
    }

    @Test
    void calculateDeduction_shouldReturnExpectedDeduction_forTwoPintsOfMilkBoughtForPriceOfOne_whenGettingThreePints() {
        Item fourPintsOfMilk = aPintOfMilk().withQuantity(BigDecimal.valueOf(3));

        ProductPercentOffer offer = twoPintsOfMilkForOneOffer();

        List<Item> items = Arrays.asList(fourPintsOfMilk, twoHundredGramsOfPickAndMix());

        BigDecimal result = offer.calculateDeduction(items);

        assertThat(result)
                .isNotNull()
                .isEqualByComparingTo("0.49");
    }

    @Test
    void calculateDeduction_shouldReturnNoDeduction_whenNotAllItemsMatch() {
        Item milk = aPintOfMilk();

        ProductPercentOffer offer = getSeventyFivePercentOffMilkAndDigestivesOffer();

        List<Item> items = Arrays.asList(milk, twoHundredGramsOfPickAndMix());

        BigDecimal result = offer.calculateDeduction(items);

        assertThat(result)
                .isNotNull()
                .isEqualByComparingTo("0");
    }
}
