package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartServiceTest {
    private final ShoppingCartService service = new ShoppingCartService();

    // Task 1
    /*@Test
    public void calculatesPrice()  {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 2),
                new Product("Coffee", BigDecimal.valueOf(6.5), 1)
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        assertEquals(BigDecimal.valueOf(16.5), result);
    }

    // Task 2
    @Test
    public void calcluatesPriceWithBulkDiscount() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 5),
                new Product("Coffee", BigDecimal.valueOf(3.5), 3)
        ));

        BigDecimal result = service.calculateTotalPrice(cart);
        System.out.println(result);

        assertEquals(BigDecimal.valueOf(33.0), result);
    }

    // Task 3
    @Test
    public void calculatesPriceWithBulkDiscountByCategory() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2, "drinks"),
                new Product("Cheese", BigDecimal.valueOf(8.0), 2, "food")
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        assertEquals(BigDecimal.valueOf(31.84), result);
    }*/

    // Task 4
    @Test
    public void calculatesPriceWithBulkDiscountByCategoryConfigurable_WithDiscount() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2, "drinks"),
                new Product("Cheese", BigDecimal.valueOf(8.0), 2, "food")
        ));

        BigDecimal result = service.calculateTotalPrice(cart, 3, BigDecimal.valueOf(0.1));

        assertEquals(BigDecimal.valueOf(31.84), result);
    }

    @Test
    public void calculatesPriceWithBulkDiscountByCategoryConfigurable_NoDiscount() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.3), 1, "drinks"),
                new Product("Coffee", BigDecimal.valueOf(3.5), 1, "drinks"),
                new Product("Cheese", BigDecimal.valueOf(8.0), 2, "food")
        ));

        BigDecimal result = service.calculateTotalPrice(cart, 3, BigDecimal.valueOf(0.1));

        assertEquals(BigDecimal.valueOf(24.80), result);
    }

    @Test
    public void calculatesPriceWithBulkDiscountByCategoryConfigurable_EmptyCart() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(

        ));

        BigDecimal result = service.calculateTotalPrice(cart, 3, BigDecimal.valueOf(0.1));

        assertEquals(BigDecimal.ZERO, result);
    }
}
