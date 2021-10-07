package com.company;

public class Sheep extends Animal {
    public Sheep(String name, String sex, int health, int price){
        super(name, sex, health, price);
    }

    @Override
    public void eatFood(Food food) {
        if (food.getType().equals("Grain")) {
            super.eatFood(food);
        } else {
            System.out.println("Sheep can only eat grains!");
        }
    }
}
