package uk.gov.hmrc.checkout;

public class Checkout {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart(args);

        System.out.println(shoppingCart.getTotalPrice().doubleValue());
    }
}
