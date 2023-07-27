package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCartService {

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart, int amountOfItemsToDiscount, BigDecimal discountPercentage) {
        // Calculate total quantity of products for each category
        Map<String, Integer> categoryCountMap = shoppingCart.getProducts()
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.summingInt(Product::getQuantity)));

        // Calculate total price of products for each category
        Map<String, BigDecimal> categoryTotalPriceMap = shoppingCart.getProducts()
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.mapping(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())),
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        // Apply discount for each category if the total quantity exceeds value of given 'amountOfItemsToDiscount'
        categoryTotalPriceMap.forEach((category, categoryTotalPrice) -> {
            int categoryItemCount = categoryCountMap.get(category);
            if (categoryItemCount > amountOfItemsToDiscount) {
                BigDecimal discountAmount = categoryTotalPrice.multiply(discountPercentage);
                categoryTotalPriceMap.put(category, categoryTotalPrice.subtract(discountAmount));
            }
        });

        // Calculate total price
        BigDecimal totalPrice = categoryTotalPriceMap.values()
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalPrice;
    }

}
