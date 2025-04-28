package lv2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);
    static List<MenuItem> menuItemList = new ArrayList<>();

    public static void main(String[] args){
        initMenu();

        while(true){ // 0이 입력되기 전까지 반복 수행
            int choice = selectMenu(menuItemList);
            if(choice == 0) {
                System.out.println("주문을 종료합니다!");
                sc.close();
                return;
            }
            handleSelect(choice); // 0 제외한 메뉴 선택 시의 처리를 메서드로 분리
        }
    }

    private static void initMenu(){ // 메뉴 아이템 추가
        addMenu(new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        addMenu(new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        addMenu(new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        addMenu(new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거"));
    }

    private static void addMenu(MenuItem menuItem){
        menuItemList.add(menuItem);
    }

    // 메뉴 출력 후 사용자의 입력을 받는 메서드
    private static int selectMenu(List<MenuItem> menuItemList){
        System.out.println("[ SHAKESHACK MENU ]");
        for(int i = 1; i <= menuItemList.size(); i++){ // menuItemList의 모든 menuItem 출력
            System.out.println(i + ". " + menuItemList.get(i - 1).toString());
        }
        System.out.println("0. 종료      | 종료");

        while (true){ // 숫자 입력을 받을 때까지 반복
            System.out.print("> ");
            try {
                return sc.nextInt();
            }
            catch (InputMismatchException e){ // 예외 처리 : 문자를 입력한 경우
                sc.nextLine();
                System.out.println("숫자를 입력해주세요!");
            }
        }
    }

    private static void handleSelect(int choice){
        switch (choice){
            case 1: System.out.println("ShackBurger 나왔습니다!"); break;
            case 2: System.out.println("SmokeShack 나왔습니다!"); break;
            case 3: System.out.println("Cheeseburger 나왔습니다!"); break;
            case 4: System.out.println("Hamburger 나왔습니다!"); break;
            default: System.out.println("잘못된 번호를 입력했습니다. 다시 입력해주세요"); break;
        }
    }
}
