package com.company;

public class Pig extends Animal {
    public Pig(String name, String sex, int health, int price){
        super(name, sex, health, price);
    }

    @Override
    public void eatFood(Food food) {
        if (food.getType().equals("Apple")) {
            super.eatFood(food);
        } else {
            System.out.println("Pig can only eat apples!");
        }
    }
}
