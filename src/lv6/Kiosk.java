package lv6;

import java.util.*;

public class Kiosk {
    private final Scanner sc = new Scanner(System.in);
    private final List<Menu> menus = new ArrayList<>();
    private final Cart cart = new Cart();
    private static final int MENU_ORDER = 4;
    private static final int MENU_CANCEL = 5;

    public void startKiosk(){
        System.out.println("[SHAKESHACK 키오스크를 실행합니다!!]");
        System.out.println("----------------------------------------------------------");
        initMenu(); /* 메뉴 초기화 */
        while(true){
            int userChoice = printMenuAndSelect(); // 메뉴 카테고리 출력 및 선택
            if(!isValidMenuchoice(userChoice))
                System.out.println("잘못된 메뉴 선택입니다. 다시 입력해주세요!");
            else if(userChoice == 0) {
                System.out.println("주문을 종료합니다! 안녕히 가세요!");
                break;
            }
            /* 선택한 메뉴의 아이템을 선택받는 메서드 */
            else if(userChoice < MENU_ORDER) printItemsAndSelect(userChoice);
            /* 카트가 비어있지 않은 경우 카트 메뉴 승인 */
            else if(!cart.isEmpty()) processCartOption(userChoice);
        }
        sc.close();
    }

    /** 메뉴 선택이 올바른 값인지 확인 */
    private boolean isValidMenuchoice(int choice){
        return choice >= 0 && choice <= menus.size()
                || (!cart.isEmpty() && (choice == MENU_ORDER || choice == MENU_CANCEL));
    }

    /** 메뉴 카테고리 및 아이템 초기화 */
    private void initMenu(){
        String[] categories = {"Burgers", "Drinks", "Desserts"};
        for(String category: categories){
            addMenu(category);
        }
        addBurgerItems();
        addDrinkItems();
        addDessertItems();
    }

    private void addBurgerItems(){
        addMenuItem("Burgers",
                new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거")
        );
    }

    private void addDrinkItems(){
        addMenuItem("Drinks", new MenuItem("콜라", 2000, "시원한 탄산음료"),
                new MenuItem("레몬에이드", 2500, "상큼한 레몬 맛 음료"),
                new MenuItem("아이스티", 2500, "달콤한 복숭아 아이스티"),
                new MenuItem("물", 1000, "생수 한 병")
        );
    }

    private void addDessertItems(){
        addMenuItem("Desserts", new MenuItem("초코 아이스크림", 3500, "달콤한 초콜릿 맛 아이스크림"),
                new MenuItem("바닐라 아이스크림", 3500, "부드러운 바닐라 아이스크림"),
                new MenuItem("쿠키", 2000, "갓 구운 초코칩 쿠키"),
                new MenuItem("쉐이크", 4500, "진하고 시원한 쉐이크")
        );
    }

    private void addMenu(String category){
        menus.add(new Menu(category));
    }

    /** 카테고리에 메뉴 추가 */
    private void addMenuItem(String category, MenuItem... menuItems){
        // 선택한 카테고리의 Menu 객체 찾아 아이템 추가하기
        menus.stream()
                .filter(menu -> menu.getCategory().equals(category))
                .findFirst()
                .ifPresent(menu -> Arrays.stream(menuItems).forEach(menu :: addMenuItem));
    }

    /** 생성된 메뉴들의 카테고리 출력 */
    private int printMenuAndSelect(){
        System.out.println("\n[ MAIN MENU ]");
        for(int i = 0; i < menus.size(); i++){
            System.out.println((i + 1) + ". " + menus.get(i).getCategory());
        }
        System.out.println("0. 종료      | 종료");

        if (!cart.isEmpty()){
            System.out.println("\n[ ORDER MENU ]");
            System.out.println("4. Orders       | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. Cancel       | 진행중인 주문을 취소합니다.");
        }
        return getUserInput();
    }

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
        /* 선택한 메뉴 가져오기 */
        Menu selectedMenu = menus.get(menuChoice - 1);
        while(true){
            selectedMenu.printItems();
            int itemChoice = getUserInput();
            if(itemChoice == 0) break;
            /* 선택한 번호의 MenuItem을 찾아오기 */
            MenuItem selectedItem = selectedMenu.getItemByIndex(itemChoice);
            if(selectedItem == null) System.out.println("올바른 메뉴를 선택해주세요!");
            else {
                System.out.println("선택한 메뉴: " + selectedItem);
                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                confirmAddToCart(selectedMenu, itemChoice);
            }
        }
    }

    /* 장바구니에 아이템을 담을 것인지 확인하는 메서드 */
    private void confirmAddToCart(Menu selectedMenu, int itemChoice){
        while (true){
            System.out.println("1. 확인              2. 취소");
            int cartChoice = getUserInput();
            if(cartChoice == 1) {
                cart.addCartItem(selectedMenu.getMenuItemByIndex(itemChoice - 1));
                System.out.println("장바구니에 추가되었습니다!");
                break;
            }
            else if (cartChoice == 2) {
                System.out.println("취소되었습니다!");
                break;
            }
            else if(cartChoice > 2) System.out.println("잘못된 입력입니다! 다시 입력해주세요!");
        }
    }

    /**
     * 장바구니 관련 옵션을 처리
     * - MENU_ORDER: 주문 확인 및 결제 절차 진행
     * - MENU_CANCEL: 장바구니 비우기
     */
    private void processCartOption(int cartChoice){
        if(cartChoice == MENU_ORDER){
            cart.printSummary();
            handleOrder();
        }
        else if(cartChoice == MENU_CANCEL){
            cart.emptyCart();
            System.out.println("장바구니가 비워졌습니다.");
        }
    }

    /* 주문 요약을 기반으로 최종 주문을 확정하거나 메뉴로 돌아감 */
    private void handleOrder(){
        while (true){
            System.out.println("\n1. 주문      2. 메뉴판");
            int orderChoice = getUserInput();
            if (orderChoice == 1) {
                cart.confirmOrder();
                return;
            }
            else if (orderChoice == 2) return;
            else System.out.println("잘못된 입력입니다. 다시 입력해주세요!");
        }
    }
}
