package lv7;

public enum UserType {
    ADULT("어른"),
    STUDENT("학생"),
    SENIOR("노인"),
    CHILD("어린이"),
    DISABLED("장애우"),
    PREGNANT("임산부"),
    VETERAN("국가유공자");

    private final String type;

    UserType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static UserType getUserTypeByIndex(int index){
        return switch (index) {
            case 1 -> ADULT;
            case 2 -> STUDENT;
            case 3 -> SENIOR;
            case 4 -> CHILD;
            case 5 -> DISABLED;
            case 6 -> PREGNANT;
            case 7 -> VETERAN;
            default -> throw new IllegalArgumentException("잘못된 번호입니다. 다시 값을 입력하세요.");
        };
    }

    public double getDiscountRate(){
        return switch (this) {
            case ADULT -> 0;
            case STUDENT -> 0.05;
            case SENIOR -> 0.2;
            case CHILD -> 0.1;
            case DISABLED, PREGNANT, VETERAN -> 0.5;
        };

    }

    public double doDiscount(int price){
        return price * (1 - getDiscountRate());
    }
}
