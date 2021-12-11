package kata.supermarket.offer;

import kata.supermarket.Item;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Map;

public class ProductPercentOffer extends Offer {
    BigDecimal deductionMultiplier;

    public ProductPercentOffer(String offerName, @NonNull Map<String, BigDecimal> requiredQuantities,
                               @NonNull BigDecimal deductionMultiplier) {
        super(offerName, requiredQuantities);
        this.deductionMultiplier = deductionMultiplier;
    }

    @Override
    protected BigDecimal calculateItemDeduction(Item item) {
        BigDecimal requiredQuantity = getRequiredQuantity(item);
        BigDecimal eligibleQuantityMultiplier = getEligibleQuantityMultiplier(item);

        return eligibleQuantityMultiplier
                .multiply(requiredQuantity)
                .multiply(deductionMultiplier)
                .multiply(item.unitPrice());
    }

}
