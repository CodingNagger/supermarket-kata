package kata.supermarket;

import kata.supermarket.offer.Offer;
import kata.supermarket.offer.OfferService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static kata.supermarket.TestFixture.aPackOfDigestives;
import static kata.supermarket.TestFixture.aPintOfMilk;
import static kata.supermarket.TestFixture.getSeventyFivePercentOffMilkAndDigestivesOffer;
import static kata.supermarket.TestFixture.twoFiftyGramsOfAmericanSweets;
import static kata.supermarket.TestFixture.twoHundredGramsOfPickAndMix;
import static kata.supermarket.TestFixture.twoPintsOfMilkForOneOffer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {
    static Stream<Arguments> basketProvidesTotalValueForMilkAndDigestivesOfferScenario() {
        return Stream.of(
                Arguments.arguments(
                        "no offer available",
                        "4.68",
                        Collections.emptyList()),
                Arguments.arguments(
                        "milk and cookies offer available",
                        "1.62",
                        Collections.singletonList(getSeventyFivePercentOffMilkAndDigestivesOffer())),
                Arguments.arguments(
                        "multiple milk related offers available",
                        "1.62",
                        Arrays.asList(
                                getSeventyFivePercentOffMilkAndDigestivesOffer(),
                                twoPintsOfMilkForOneOffer())),
                Arguments.arguments(
                        "two pints of milk for one offer only",
                        "4.19",
                        Collections.singletonList(twoPintsOfMilkForOneOffer()))
        );
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    @DisplayName("basket provides its total value when containing...")
    @MethodSource("basketProvidesTotalValue")
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    @DisplayName("basket provides its total value when containing...")
    @MethodSource("basketProvidesTotalValueForMilkAndDigestivesOfferScenario")
    @ParameterizedTest(name = "{0}")
    void total_shouldReturnExpectedAmount_forGivenOffers(String description, String expectedTotal, List<Offer> offers) {
        final Basket basket = new Basket(new OfferService(offers));

        basket.add(aPackOfDigestives().withQuantity(BigDecimal.valueOf(2)));
        basket.add(aPintOfMilk().withQuantity(BigDecimal.valueOf(2)));
        basket.add(twoHundredGramsOfPickAndMix());

        assertThat(basket.total()).isEqualByComparingTo(expectedTotal);
    }
}