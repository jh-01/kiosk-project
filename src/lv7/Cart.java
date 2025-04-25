package lv7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems(){
        return Collections.unmodifiableList(cartItems);
    }

    public void addCartItem(MenuItem menuItem){
        Optional<CartItem> targetItem = cartItems.stream().filter(cartItem -> menuItem.equals(cartItem.getMenuItem())).findFirst();
        if(targetItem.isPresent()) targetItem.get().addMenuItem();
        else cartItems.add(new CartItem(menuItem));
    }

    public void subCartItem(MenuItem menuItem){
        Optional<CartItem> targetItem = cartItems.stream().filter(cartItem -> menuItem.equals(cartItem.getMenuItem())).findFirst();
        targetItem.ifPresent(cartItem -> {
            if(cartItem.getCount() > 1) cartItem.subtractMenuItem();
            else cartItems.remove(cartItem);
        });
    }

    public void emptyCart(){
        cartItems.clear();
    }

    public int getTotalPrice(){
        AtomicInteger totalPrice = new AtomicInteger(0);
        cartItems.forEach(cartItem -> totalPrice.addAndGet(cartItem.getPrice()));
        return totalPrice.get();
    }

    public boolean isEmpty(){
        return cartItems.isEmpty();
    }
}