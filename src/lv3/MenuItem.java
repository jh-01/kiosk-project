package lv3;

public class MenuItem {
    private String name;
    private int cost;
    private String desc;

    public MenuItem(String name, int cost, String desc){
        this.name = name;
        this.cost = cost;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return String.format("%-15s | W %.1f | %s", name, (double) cost/1000, desc);
    }
}
