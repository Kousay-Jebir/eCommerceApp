package features.trasnactionHistory;

import features.shoppingCart.Cart;
import features.shoppingCart.CartProduct;
import features.utilityClasses.Collection;

public class Order {
    private static int orderCounter = 0;
    private int orderID = 0;
    private String orderUsername;
    private float orderAmount;
    private Collection<CartProduct> order;

    public Collection<CartProduct> getOrder() {
        return order;
    }

    public Order(Collection<CartProduct> cartContent, float totalPrice, String userName) {
        Order.orderCounter++;
        orderID = orderCounter;
        orderAmount = totalPrice;
        orderUsername = userName;

        // Create a new collection and add copies of CartProducts
        order = new Collection<CartProduct>();
        for (CartProduct originalProduct : cartContent.getCollection()) {
            CartProduct copiedProduct = new CartProduct(
                    originalProduct.getAssosiatedProduct(),
                    originalProduct.getQuantityInCart());
            order.addCollectable(copiedProduct);
        }
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order ID: ").append(orderID).append("\n");
        orderDetails.append("Username: ").append(orderUsername).append("\n");
        orderDetails.append("Order Amount: $").append(orderAmount).append("\n");
        orderDetails.append("Order Items:\n");

        for (CartProduct product : order.getCollection()) {
            orderDetails.append(product).append("\n");
        }

        return orderDetails.toString();
    }
}
