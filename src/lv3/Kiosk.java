package lv3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final Scanner sc = new Scanner(System.in);
    private final List<MenuItem> menuItemList = new ArrayList<>();

    public void startKiosk(){
        initMenu();
        while(true){
            int choice = selectMenu(menuItemList);
            if(choice == 0) {
                System.out.println("주문을 종료합니다! 안녕히 가세요!");
                break;
            }
            handleSelect(choice);
        }
        sc.close();
    }

    private void initMenu(){
        addMenu(new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        addMenu(new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        addMenu(new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        addMenu(new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거"));
    }

    private void addMenu(MenuItem menuItem){
        menuItemList.add(menuItem);
    }

    private int selectMenu(List<MenuItem> menuItemList){
        System.out.println("\n[ SHAKESHACK MENU ]");
        for(int i = 1; i <= menuItemList.size(); i++){
            System.out.println(i + ". " + menuItemList.get(i - 1).toString());
        }
        System.out.println("0. 종료      | 종료");

        String input = sc.next();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요!!!");
            return -1;
        }
    }

    private void handleSelect(int choice){
        switch (choice){
            case 1: System.out.println("ShackBurger 나왔습니다!"); break;
            case 2: System.out.println("SmokeShack 나왔습니다!"); break;
            case 3: System.out.println("Cheeseburger 나왔습니다!"); break;
            case 4: System.out.println("Hamburger 나왔습니다!"); break;
            default: System.out.println("잘못된 번호를 입력했습니다. 다시 입력해주세요"); break;
        }
    }
}
