package lv6;

import java.util.*;

public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems(){
        return Collections.unmodifiableList(cartItems);
    }

    public void addCartItem(MenuItem menuItem){
        Optional<CartItem> targetItem = findCartItem(menuItem);
        if(targetItem.isPresent()) targetItem.get().addMenuItem();
        else cartItems.add(new CartItem(menuItem));
    }

    public void subCartItem(MenuItem menuItem){
        Optional<CartItem> targetItem = findCartItem(menuItem);
        targetItem.ifPresent(cartItem -> {
            if(cartItem.getCount() > 1) cartItem.subtractMenuItem();
            else cartItems.remove(cartItem);
        });
    }

    private Optional<CartItem> findCartItem(MenuItem menuItem){
        return cartItems.stream()
                .filter(cartItem -> cartItem.getMenuItem().equals(menuItem))
                .findFirst();
    }

    public void emptyCart(){
        cartItems.clear();
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getPrice();
        }
        return totalPrice;
    }

    public boolean isEmpty(){
        return cartItems.isEmpty();
    }

    public void printSummary(){
        System.out.println("\n아래와 같이 주문 하시겠습니까?");
        System.out.println("\n[ Orders ]");
        for(CartItem cartItem : cartItems){
            System.out.println(cartItem.toString());
        }
        System.out.println("\n[ Total ]");
        System.out.printf("W %.1f\n", ((double) getTotalPrice() / 1000));
    }

    public void confirmOrder(){
        System.out.printf("주문이 완료되었습니다. 금액은 W %.1f 입니다.\n", (double) getTotalPrice() / 1000);
        emptyCart();
    }
}