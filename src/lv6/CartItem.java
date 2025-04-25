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

    public void addMenuItem(){
        this.count++;
    }

    public void subtractMenuItem(){
        this.count--;
    }

    @Override
    public String toString(){
        return String.format(menuItem.toString() + " | " + this.count + "개");
    }
}
