package kata.supermarket.offer;

import kata.supermarket.Item;
import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Offer {
    @Getter
    String offerName;

    @NonNull
    Map<String, BigDecimal> requiredQuantities;

    protected Offer(String offerName, @NonNull Map<String, BigDecimal> requiredQuantities) {
        this.offerName = offerName;
        this.requiredQuantities = requiredQuantities;
    }

    public List<Item> applicableItems(List<Item> items) {
        List<Item> candidateItems = items.stream()
                .filter(this::isApplicable)
                .collect(Collectors.toList());

        if (requiredQuantities.size() != candidateItems.size()) {
            return Collections.emptyList();
        }

        return candidateItems;
    }

    private boolean isApplicable(Item i) {
        return requiredQuantities.containsKey(i.getProductName()) &&
                requiredQuantities.get(i.getProductName()).compareTo(i.quantity()) <= 0;
    }

    public BigDecimal calculateDeduction(List<Item> items) {
        List<Item> applicableItems = applicableItems(items);

        if (applicableItems.isEmpty()) {
            return BigDecimal.ZERO;
        }

        BigDecimal applicableSum = BigDecimal.ZERO;

        for (Item item : applicableItems) {
            applicableSum = applicableSum.add(calculateItemDeduction(item));
        }

        return applicableSum.setScale(2, RoundingMode.HALF_UP);
    }

    abstract protected BigDecimal calculateItemDeduction(Item item);

    protected BigDecimal getEligibleQuantityMultiplier(Item item) {
        return item.quantity().divide(getRequiredQuantity(item), RoundingMode.FLOOR);
    }

    protected BigDecimal getRequiredQuantity(Item item) {
        return requiredQuantities.get(item.getProductName());
    }
}
