package uk.gov.hmrc.checkout;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static uk.gov.hmrc.checkout.ShoppingCart.APPLE;
import static uk.gov.hmrc.checkout.ShoppingCart.ORANGE;

public class ShoppingCartTest {
    private ShoppingCart shoppingCart;

    @Test
    public void getTotalPrice_zeroForEmptyCart() {
        testTotalPrice(BigDecimal.ZERO);
    }

    @Test
    public void getTotalPrice_oneApple() {
        testTotalPrice(new BigDecimal(0.6), APPLE);
    }

    @Test
    public void getTotalPrice_fourApples() {
        testTotalPrice(new BigDecimal(2.4), APPLE, APPLE, APPLE, APPLE);
    }

    @Test
    public void getTotalPrice_oneOrange() {
        testTotalPrice(new BigDecimal(0.25), ORANGE);
    }

    @Test
    public void getTotalPrice_threeOranges() {
        testTotalPrice(new BigDecimal(0.75), ORANGE, ORANGE, ORANGE);
    }

    @Test
    public void getTotalPrice_threeApplesAndOrange() {
        testTotalPrice(new BigDecimal(2.05), APPLE, APPLE, ORANGE, APPLE);
    }

    private void testTotalPrice(BigDecimal expectedTotalPrice, String... items) {
        shoppingCart = new ShoppingCart(items);
        assertEquals(expectedTotalPrice, shoppingCart.getTotalPrice());
    }
}