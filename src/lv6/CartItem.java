package lv6;

public class CartItem {
    private MenuItem menuItem;
    private int count;

    public CartItem(MenuItem menuItem){
        this.menuItem = menuItem;
        this.count = 1;
    }

    public MenuItem getMenuItem() {
        return this.menuItem;
    }

    public int getCount(){
        return this.count;
    }

    public int getPrice(){
        return menuItem.getCost() * count;
    }

    public void incrementCount(){
        this.count++;
    }

    public void decrementCount(){
        this.count--;
    }

    @Override
    public String toString(){
        return String.format("%s (W %.1f) | %d개 | 합계: W %.1f",
                menuItem.getName(), (double) menuItem.getCost() / 1000, count, (double) getPrice() / 1000);
    }
}
