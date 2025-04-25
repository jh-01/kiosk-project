package lv6;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Kiosk {
    private final Scanner sc = new Scanner(System.in);
    private final List<Menu> menus = new ArrayList<>();
    private final Cart cart = new Cart();

    public void startKiosk(){
        initMenu();
        while(true){
            int userChoice = printMenuAndSelect();
            if(!isValidMenuchoice(userChoice)) System.out.println("잘못된 메뉴 선택입니다. 다시 입력해주세요!");
            else if(userChoice == 0) {
                System.out.println("주문을 종료합니다! 안녕히 가세요!");
                break;
            }
            else if(userChoice < 4) browseCategoryItems(userChoice);
            else if(!cart.isEmpty()) processCartOption(userChoice);
        }
        sc.close();
    }

    private boolean isValidMenuchoice(int choice){
        return choice >= 0 && choice <= menus.size() || (!cart.isEmpty() && (choice == 4 || choice == 5));
    }

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

    private void addMenuItem(String category, MenuItem menuItem){
        Optional<Menu> targetMenu = menus.stream().filter(menu -> menu.getCategory().equals(category)).findFirst();
        targetMenu.ifPresent(menu -> menu.addMenuItem(menuItem));
    }

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
        String input = sc.next();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요!!!");
            return -1;
        }
    }

    private void browseCategoryItems(int menuChoice){
        Menu selectedMenu = menus.get(menuChoice - 1);
        while(true){
            selectedMenu.printItems();
            int itemChoice = getUserInput();
            if(itemChoice == 0) break;
            if(itemChoice > selectedMenu.getMenuItemListSize() || itemChoice < 1) System.out.println("올바른 메뉴를 선택해주세요!");
            else {
                System.out.println("선택한 메뉴: " + selectedMenu.getMenuItemByIndex(itemChoice - 1).toString());
                System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                confirmAddToCart(selectedMenu, itemChoice);
            }
        }
    }

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

    private void processCartOption(int cartChoice){
        if(cartChoice == 4){
            showOrderSummary();
            handleOrderSummary();
        }
        else if(cartChoice == 5){
            cart.emptyCart();
            System.out.println("장바구니가 비워졌습니다.");
        }
    }

    private void showOrderSummary(){
        List<CartItem> cartItemList = cart.getCartItems();
        System.out.println("\n아래와 같이 주문 하시겠습니까?");
        System.out.println("\n[ Orders ]");
        for(CartItem cartItem : cartItemList){
            System.out.println(cartItem.toString());
        }
        System.out.println("\n[ Total ]");
        System.out.printf("W %.1f\n", ((double) cart.getTotalPrice() / 1000));
    }

    private void handleOrderSummary(){
        while (true){
            System.out.println("\n1. 주문      2. 메뉴판");
            int orderChoice = getUserInput();
            if (orderChoice == 1) {
                System.out.printf("주문이 완료되었습니다. 금액은 W %.1f 입니다.\n", (double) cart.getTotalPrice() / 1000);
                cart.emptyCart();
                return;
            }
            else if (orderChoice == 2) return;
            else System.out.println("잘못된 입력입니다. 다시 입력해주세요!");
        }
    }
}
