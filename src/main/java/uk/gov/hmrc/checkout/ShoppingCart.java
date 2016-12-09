package uk.gov.hmrc.checkout;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Stream.of;

public class ShoppingCart {
    public static String APPLE = "Apple";
    public static String ORANGE = "Orange";

    private String[] items;

    private final Map<String, Integer> prices = new HashMap<>();

    {
        prices.put(APPLE, 60);
        prices.put(ORANGE, 25);
    }

    public ShoppingCart(String[] items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        Map<String, Long> counters = of(items).collect(groupingBy(identity(), counting()));
        long priceInPence = counters.entrySet().stream()
                .map(entry -> {
                    return applyOffer(entry.getKey(), entry.getValue()) * prices.get(entry.getKey());
                })
                .reduce(0L, (s, p) -> s + p);
        return new BigDecimal(priceInPence / 100.0);
    }

    private long applyOffer(String item, long count) {
        if (APPLE.equals(item)) return applyApplesOffer(count);
        else return applyOrangesOffer(count);
    }

    private long applyApplesOffer(long count) {
        return count / 2 + count % 2;
    }

    private long applyOrangesOffer(long count) {
        return count / 3 * 2 + count % 3;
    }
}
