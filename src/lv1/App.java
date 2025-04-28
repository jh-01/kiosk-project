package lv1;

import java.util.InputMismatchException;
import java.util.Scanner;

/* Lv 1. 기본적인 키오스크 */
public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        while(true){ // 0이 입력되기 전까지 반복 수행
            int choice = selectMenu();
            switch (choice){
                case 1: System.out.println("ShackBurger 나왔습니다!"); break;
                case 2: System.out.println("SmokeShack 나왔습니다!"); break;
                case 3: System.out.println("Cheeseburger 나왔습니다!"); break;
                case 4: System.out.println("Hamburger 나왔습니다!"); break;
                case 0:
                    System.out.println("주문을 종료합니다!");
                    sc.close();
                    return;
                default: System.out.println("잘못된 번호를 입력했습니다. 다시 입력해주세요"); break;
            }
        }
    }

    // 메뉴 출력 후 사용자의 입력을 받는 메서드
    private static int selectMenu(){
        System.out.println("[ SHAKESHACK MENU ]");
        System.out.println("1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거");
        System.out.println("2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
        System.out.println("3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
        System.out.println("4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거");
        System.out.println("0. 종료      | 종료");

        while (true){
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
}
