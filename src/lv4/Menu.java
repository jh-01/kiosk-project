package lv4;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<MenuItem> menuItemList;
    private final String category;

    public Menu(String category){
        this.menuItemList = new ArrayList<>();
        this.category = category;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public String getCategory() {
        return category;
    }

    public void addMenuItem(MenuItem menuItem){
        menuItemList.add(menuItem);
    }

    public void printItems(){
        System.out.println("\n[ " + this.category + " MENU ]");
        for(int i = 1; i <= menuItemList.size(); i++){
            System.out.println(i + ". " + menuItemList.get(i - 1).toString());
        }
        System.out.println("0. 뒤로가기");
    }
}
