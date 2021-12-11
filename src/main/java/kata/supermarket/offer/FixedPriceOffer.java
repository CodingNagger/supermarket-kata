package kata.supermarket.offer;

import kata.supermarket.Item;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Map;

public class FixedPriceOffer extends Offer {
    private final BigDecimal comboAmount;

    public FixedPriceOffer(String offerName, @NonNull Map<String, BigDecimal> requiredQuantities, BigDecimal comboAmount) {
        super(offerName, requiredQuantities);

        this.comboAmount = comboAmount;
    }

    @Override
    protected BigDecimal calculateItemDeduction(Item item) {
        return item.unitPrice()
                .multiply(getRequiredQuantity(item))
                .subtract(comboAmount)
                .multiply(getEligibleQuantityMultiplier(item));
    }
}
