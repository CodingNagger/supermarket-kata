package kata.supermarket.offer;

import kata.supermarket.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OfferService {
    private final List<Offer> offers;

    public OfferService(List<Offer> offers) {
        this.offers = Collections.unmodifiableList(offers);
    }

    public BigDecimal calculateOffersDeduction(List<Item> items) {
        List<Item> itemsWithoutOffers = new ArrayList<>(items);
        BigDecimal deduction = BigDecimal.ZERO;

        for (Offer o : offers) {
            if (itemsWithoutOffers.isEmpty()) {
                break;
            }

            List<Item> applicableItems = o.applicableItems(itemsWithoutOffers);

            if (applicableItems.isEmpty()) {
                continue;
            }

            deduction = deduction.add(o.calculateDeduction(applicableItems));
            itemsWithoutOffers.removeAll(applicableItems);
        }

        return deduction;
    }
}
