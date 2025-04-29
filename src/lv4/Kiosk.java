package lv4;

import java.util.*;

public class Kiosk {
    private final Scanner sc = new Scanner(System.in);
    private final List<Menu> menus = new ArrayList<>();

    public void startKiosk(){
        System.out.println("[SHAKESHACK 키오스크를 실행합니다!!]");
        System.out.println("----------------------------------------------------------");
        initMenu(); // 메뉴 초기화
        while(true){
            int menuChoice = printMenuAndSelect(); // 메뉴 카테고리 출력 및 선택
            if(menuChoice == 0) {
                System.out.println("주문을 종료합니다! 안녕히 가세요!");
                break;
            }
            else printItemsAndSelect(menuChoice); // 선택한 메뉴의 아이템을 선택받는 메서드
        }
        sc.close();
    }

    private void initMenu(){ // 메뉴 카테고리 및 아이템 초기화
        String[] categories = {"Burgers", "Drinks", "Desserts"};
        for(String category: categories){
            addMenu(category);
        }
        addBurgerItems();
        addDrinkItems();
        addDessertItems();
    }

    private void addBurgerItems(){
        addMenuItem("Burgers",new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        addMenuItem("Burgers",new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        addMenuItem("Burgers",new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        addMenuItem("Burgers",new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거"));
    }

    private void addDrinkItems(){
        addMenuItem("Drinks", new MenuItem("콜라", 2000, "시원한 탄산음료"));
        addMenuItem("Drinks", new MenuItem("레몬에이드", 2500, "상큼한 레몬 맛 음료"));
        addMenuItem("Drinks", new MenuItem("아이스티", 2500, "달콤한 복숭아 아이스티"));
        addMenuItem("Drinks", new MenuItem("물", 1000, "생수 한 병"));
    }

    private void addDessertItems(){
        addMenuItem("Desserts", new MenuItem("초코 아이스크림", 3500, "달콤한 초콜릿 맛 아이스크림"));
        addMenuItem("Desserts", new MenuItem("바닐라 아이스크림", 3500, "부드러운 바닐라 아이스크림"));
        addMenuItem("Desserts", new MenuItem("쿠키", 2000, "갓 구운 초코칩 쿠키"));
        addMenuItem("Desserts", new MenuItem("쉐이크", 4500, "진하고 시원한 쉐이크"));
    }

    private void addMenu(String category){
        menus.add(new Menu(category));
    }

    // 카테고리에 메뉴 추가
    private void addMenuItem(String category, MenuItem menuItem){
        Optional<Menu> targetMenu = menus.stream().filter(menu -> menu.getCategory().equals(category)).findFirst();
        targetMenu.ifPresent(menu -> menu.addMenuItem(menuItem));
    }

    // 생성된 메뉴들의 카테고리 출력
    private int printMenuAndSelect(){
        System.out.println("\n[ MAIN MENU ]");
        for(int i = 0; i < menus.size(); i++){
            System.out.println((i + 1) + ". " + menus.get(i).getCategory());
        }
        System.out.println("0. 종료      | 종료");
        return getUserInput();
    }

    // 사용자 입력을 받는 코드 메서드로 분리
    private int getUserInput(){
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

    private void printItemsAndSelect(int menuChoice){
        Menu selectedMenu = menus.get(menuChoice - 1);
        while(true){
            selectedMenu.printItems();
            int itemChoice = getUserInput();
            if(itemChoice == 0) break;
            // 입력한 값이 현재의 메뉴아이템 리스트의 사이즈보다 크거나 음수인 경우
            if(itemChoice > selectedMenu.getMenuItemList().size() || itemChoice < 0) System.out.println("올바른 메뉴를 선택해주세요!");
            else System.out.println("선택한 메뉴: " + selectedMenu.getMenuItemList().get(itemChoice - 1).toString());
        }
    }
}
