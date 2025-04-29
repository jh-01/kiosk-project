package lv3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final Scanner sc = new Scanner(System.in);
    private final List<MenuItem> menuItemList = new ArrayList<>();

    public void startKiosk(){
        System.out.println("[SHAKESHACK 키오스크를 실행합니다!!]");
        System.out.println("----------------------------------------------------------");
        initMenu();
        while(true){
            int choice = selectMenu();
            if(choice == 0) {
                System.out.println("주문을 종료합니다! 안녕히 가세요!");
                break;
            }
            handleSelect(choice);  // 0 제외한 메뉴 선택 시의 처리를 메서드로 분리
        }
        sc.close();
    }

    private void initMenu(){ // 메뉴 아이템 추가
        addMenuItem(new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        addMenuItem(new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        addMenuItem(new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        addMenuItem(new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거"));
    }

    private void addMenuItem(MenuItem menuItem){
        menuItemList.add(menuItem);
    }

    // 메뉴 출력 후 사용자의 입력을 받는 메서드
    private int selectMenu(){
        System.out.println("\n[ SHAKESHACK MENU ]");
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

    private void handleSelect(int choice){
        switch (choice){
            case 1: System.out.println(menuItemList.get(0).getName() + " 나왔습니다!"); break;
            case 2: System.out.println(menuItemList.get(1).getName() + " 나왔습니다!"); break;
            case 3: System.out.println(menuItemList.get(2).getName() + " 나왔습니다!"); break;
            case 4: System.out.println(menuItemList.get(3).getName() + " 나왔습니다!"); break;
            default: System.out.println("잘못된 번호를 입력했습니다. 다시 입력해주세요"); break;
        }
    }
}
