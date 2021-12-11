package kata.supermarket;

import lombok.Getter;
import lombok.With;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
    private final Product product;

    @Getter
    @With
    private final BigDecimal quantity;

    Item(final Product product, final BigDecimal quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public String getProductName() {
        return product.getName();
    }

    public BigDecimal price() {
        return product.getPricePerUnit().multiply(quantity).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal quantity() {
        return quantity;
    }

    public BigDecimal unitPrice() {
        return product.getPricePerUnit();
    }
}
