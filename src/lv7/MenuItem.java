package lv7;

import java.util.Objects;

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

    public int getCost() {
        return cost;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        else if(obj == null || getClass() != obj.getClass()) return false;
        MenuItem that = (MenuItem) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.desc, that.desc) &&
                this.cost == that.cost;

    }
}
