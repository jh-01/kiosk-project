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
        for(int i = 0; i <= menuItemList.size(); i++){
            MenuItem item = menuItemList.get(i);
            System.out.println((i + 1) + ". " + item.toString());
        }
        System.out.println("0. 뒤로가기");
    }
}
