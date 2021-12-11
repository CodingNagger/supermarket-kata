package kata.supermarket;


import kata.supermarket.offer.Offer;
import kata.supermarket.offer.ProductPercentOffer;
import lombok.NonNull;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static kata.supermarket.TestFixture.aPackOfDigestives;
import static kata.supermarket.TestFixture.aPintOfMilk;
import static kata.supermarket.TestFixture.getSeventyFivePercentOffMilkAndDigestivesOffer;
import static kata.supermarket.TestFixture.twoHundredGramsOfPickAndMix;
import static org.assertj.core.api.Assertions.assertThat;

public class OfferTest {

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

    class TestOffer extends Offer {
        public TestOffer(String offerName, @NonNull Map<String, BigDecimal> requiredQuantities) {
            super(offerName, requiredQuantities);
        }

        @Override
        protected BigDecimal calculateItemDeduction(Item item) {
            throw new UnsupportedOperationException();
        }
    }
}
