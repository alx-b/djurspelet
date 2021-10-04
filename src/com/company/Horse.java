package com.company;

public class Horse extends Animal{
    public Horse(String name, String sex, int health, int price){
        super(name, sex, health, price);
    }


    @Override
    public void eatFood(Food food) {
        if (food.getType().equals("Carrot")) {
            super.eatFood(food);
        } else {
            System.out.println("Horses can only eat carrots!");
        }
    }
}
