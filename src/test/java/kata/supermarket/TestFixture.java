package kata.supermarket;

import kata.supermarket.offer.FixedPriceOffer;
import kata.supermarket.offer.ProductPercentOffer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static kata.supermarket.ProductNames.KILO_OF_SWEETS;
import static kata.supermarket.ProductNames.PACK_OF_DIGESTIVES;
import static kata.supermarket.ProductNames.PICK_N_MIX;
import static kata.supermarket.ProductNames.PINT_OF_MILK;
import static kata.supermarket.ProductNames.TWINKIE;

public class TestFixture {
    public static Item aPintOfMilk() {
        return new Item(new Product(PINT_OF_MILK, new BigDecimal("0.49"), ProductUnit.COUNT), BigDecimal.ONE);
    }

    public static Item aPackOfDigestives() {
        return new Item(new Product(PACK_OF_DIGESTIVES, new BigDecimal("1.55"), ProductUnit.COUNT), BigDecimal.ONE);
    }

    public static Item twoFiftyGramsOfAmericanSweets() {
        return new Item(new Product(KILO_OF_SWEETS, new BigDecimal("4.99"), ProductUnit.WEIGHT_IN_KG), new BigDecimal(".25"));
    }

    public static Item twoHundredGramsOfPickAndMix() {
        return new Item(new Product(PICK_N_MIX, new BigDecimal("2.99"), ProductUnit.WEIGHT_IN_KG), new BigDecimal(".2"));
    }

    public static Item aSingleTwinkie() {
        return new Item(new Product(TWINKIE, new BigDecimal("0.69"), ProductUnit.COUNT), BigDecimal.ONE);
    }

    public static ProductPercentOffer getSeventyFivePercentOffMilkAndDigestivesOffer() {
        Map<String, BigDecimal> requiredQuantities = new HashMap<>();
        requiredQuantities.put(aPintOfMilk().getProductName(), BigDecimal.ONE);
        requiredQuantities.put(aPackOfDigestives().getProductName(), BigDecimal.ONE);

        return new ProductPercentOffer(
                "75% off milk and digestives",
                requiredQuantities,
                BigDecimal.valueOf(0.75));
    }

    public static ProductPercentOffer twoPintsOfMilkForOneOffer() {
        return new ProductPercentOffer(
                "Buy two pints of milk for the price of one",
                Collections.singletonMap(aPintOfMilk().getProductName(), BigDecimal.valueOf(2)),
                BigDecimal.valueOf(0.5));
    }

    public static FixedPriceOffer twoTwinkiesForOnePoundOffer() {
        return new FixedPriceOffer(
                "Buy two twinkies for £1",
                Collections.singletonMap(aSingleTwinkie().getProductName(), BigDecimal.valueOf(2)),
                BigDecimal.ONE);
    }
}
