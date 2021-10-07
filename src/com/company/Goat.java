package com.company;

public class Goat extends Animal {
    public Goat(String name, String sex, int health, int price){
        super(name, sex, health, price);
    }

    @Override
    public void eatFood(Food food) {
        if (food.getType().equals("Grain")) {
            super.eatFood(food);
        } else {
            System.out.println("Goat can only eat grains!");
        }
    }
}
