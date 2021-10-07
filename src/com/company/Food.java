package com.company;

abstract class Food implements Cloneable{
    protected String type;
    protected int healthBonus;
    protected int price;

    public Food(int healthBonus, int price){
        this.type = this.getClass().getSimpleName();
        this.healthBonus = healthBonus;
        this.price = price;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPrice() {
        return price;
    }

    public int getHealthBonus() {return healthBonus;}

    public String getType() {return type;}

    public String toString() {
        return "Type: " + this.type + " - Health Bonus: " + this.healthBonus + " - Price: " + this.price + "";
    }
}
