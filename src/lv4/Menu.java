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

    public void printItemList(){
        System.out.println("\n[ " + this.category + " MENU ]");
        for(int i = 0; i < menuItemList.size(); i++){
            MenuItem item = menuItemList.get(i);
            System.out.println((i + 1) + ". " + item.toString());
        }
        System.out.println("0. 뒤로가기");
    }

    public MenuItem getItemByIndex(int itemChoice){
        // 입력한 값이 현재의 메뉴아이템 리스트의 사이즈보다 크거나 음수인 경우
        if(itemChoice > menuItemList.size() || itemChoice < 0) return null;
        else return menuItemList.get(itemChoice - 1);
    }
}
