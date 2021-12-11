package kata.supermarket;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class Product {
    String name;
    BigDecimal pricePerUnit;
    ProductUnit productUnit;
}
