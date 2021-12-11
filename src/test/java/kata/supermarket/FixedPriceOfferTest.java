package kata.supermarket;


import kata.supermarket.offer.FixedPriceOffer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static kata.supermarket.TestFixture.aSingleTwinkie;
import static kata.supermarket.TestFixture.twoHundredGramsOfPickAndMix;
import static kata.supermarket.TestFixture.twoTwinkiesForOnePoundOffer;
import static org.assertj.core.api.Assertions.assertThat;

public class FixedPriceOfferTest {
    @Test
    void calculateDeduction_shouldReturnExpectedDeduction_forTwoTwinkiesForOnePoundOffer_whenGettingTwoTwinkies() {
        Item twoTwinkies = aSingleTwinkie().withQuantity(BigDecimal.valueOf(2));

        FixedPriceOffer offer = twoTwinkiesForOnePoundOffer();

        List<Item> items = Arrays.asList(twoTwinkies, twoHundredGramsOfPickAndMix());

        BigDecimal result = offer.calculateDeduction(items);

        assertThat(result)
                .isNotNull()
                .isEqualByComparingTo("0.38");
    }

    @Test
    void calculateDeduction_shouldReturnExpectedDeduction_forTwoTwinkiesForOnePoundOffer_whenGettingThreeTwinkies() {
        Item threeTwinkies = aSingleTwinkie().withQuantity(BigDecimal.valueOf(3));

        FixedPriceOffer offer = twoTwinkiesForOnePoundOffer();

        List<Item> items = Arrays.asList(threeTwinkies, twoHundredGramsOfPickAndMix());

        BigDecimal result = offer.calculateDeduction(items);

        assertThat(result)
                .isNotNull()
                .isEqualByComparingTo("0.38");
    }

    @Test
    void calculateDeduction_shouldReturnExpectedDeduction_forTwoTwinkiesForOnePoundOffer_whenGettingFourTwinkies() {
        Item fourTwinkies = aSingleTwinkie().withQuantity(BigDecimal.valueOf(4));

        FixedPriceOffer offer = twoTwinkiesForOnePoundOffer();

        List<Item> items = Arrays.asList(fourTwinkies, twoHundredGramsOfPickAndMix());

        BigDecimal result = offer.calculateDeduction(items);

        assertThat(result)
                .isNotNull()
                .isEqualByComparingTo("0.76");
    }
}
