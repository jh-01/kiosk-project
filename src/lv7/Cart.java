package lv7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();

    // 장바구니 항목 읽기 전용으로 반환
    public List<CartItem> getCartItems(){
        return Collections.unmodifiableList(cartItems);
    }

    // 장바구니에 항목 추가 (이미 있으면 수량 증가)
    public void addCartItem(MenuItem menuItem){
        Optional<CartItem> targetItem = cartItems.stream().filter(cartItem -> menuItem.equals(cartItem.getMenuItem())).findFirst();
        if(targetItem.isPresent()) targetItem.get().incrementCount();
        else cartItems.add(new CartItem(menuItem));
    }

    // 장바구니에서 항목 수량 감소 또는 제거
    public void subCartItem(MenuItem menuItem){
        Optional<CartItem> targetItem = cartItems.stream().filter(cartItem -> menuItem.equals(cartItem.getMenuItem())).findFirst();
        targetItem.ifPresent(cartItem -> {
            if(cartItem.getCount() > 1) cartItem.decrementCount();
            else cartItems.remove(cartItem);
        });
    }

    // 장바구니 비우기
    public void emptyCart(){
        cartItems.clear();
    }

    // 총 가격 계산
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

    // 장바구니 요약 출력
    public void printSummary(){
        System.out.println("\n아래와 같이 주문 하시겠습니까?");
        System.out.println("\n[ Orders ]");
        for(CartItem cartItem : cartItems){
            System.out.println(cartItem.toString());
        }
        System.out.println("\n[ Total ]");
        System.out.printf("W %.1f\n", ((double) getTotalPrice() / 1000));
    }

    // 결과 출력 후 장바구니 비우기
    public void confirmOrder(double price){
        System.out.printf("주문이 완료되었습니다. 금액은 W %.2f 입니다.\n", price / 1000);
        emptyCart();
    }
}