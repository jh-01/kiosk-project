package lv4;

public class MenuItem {
    private final String name;
    private final int cost;
    private final String desc;

    public MenuItem(String name, int cost, String desc){
        this.name = name;
        this.cost = cost;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return String.format("%-15s | W %.1f | %s", name, (double) cost/1000, desc);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getCost() {
        return cost;
    }
}
