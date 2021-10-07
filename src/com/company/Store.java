package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    String[] storeChoices;
    Animal[] animalForSellChoices;
    Food[] foodForSellChoices;

    public Store(){
        storeChoices = new String[]{"1. Buy animals", "2. Buy food", "3. Sell animals", "4. Leave store"};
        animalForSellChoices = new Animal[]{
            new Goat("bob", "M", 100, 20),
            new Goat("maggie", "F", 100, 20),
            new Sheep("rob", "M", 100, 40),
            new Sheep("mary", "F", 100, 40),
            new Pig("gertrude", "F", 100, 60),
            new Pig("garry", "M", 100, 60),
            new Cattle("sally", "F", 100, 80),
            new Cattle("barry", "M", 100, 80),
            new Horse("millie", "F", 100, 100),
            new Horse("alan", "M", 100, 100),
        };
        foodForSellChoices = new Food[]{
                new Grain(10, 15),
                new Apple(10, 20),
                new Carrot(10, 25),
        };
    }

/*
    public void displayStore(Player player){
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("==== Store ====");
            for (String choice : this.storeChoices) {
                System.out.println(choice);
            }
            System.out.print("Enter a number: ");
            String selection = scan.nextLine();
            switch (selection){
                case "1": sellAnimal(player); break;
                case "2": sellFood(player); break;
                case "3": buyAnimalFromPlayer(player); break;
                case "4": loop = false; break;
                default:
                    System.out.println("Enter a number from 1 to 4.");
                    break;
            }
        }
    }
*/

    public void displayAnimalForSell(){
        System.out.println("==== Animals for sell ====");
        for (int i = 0; i < this.animalForSellChoices.length; i++) {
            System.out.println(i+1 + ". " + this.animalForSellChoices[i]);
        }
    }


    public void displayFoodToSell(){
        System.out.println("==== Food for sell ====");
        for (int i = 0; i < this.foodForSellChoices.length; i++) {
            System.out.println(i+1 + ". " + this.foodForSellChoices[i]);
        }
    }


    public int displayPlayerAnimalToBuy(ArrayList<Animal> userAnimalList){
        System.out.println("==== Player's animals for sell ====");
        int idx = 0;
        for (int i = 0; i < userAnimalList.size(); i++) {
            System.out.println(i+1 + ". " + userAnimalList.get(i));
            idx = i+1;
        }
        return idx;
    }

    public boolean playerHasEnoughMoney(int playerMoney, int price){
        if (!(playerMoney >= price)){
            System.out.println("Not enough money!");
        }
        return playerMoney >= price;
    }

    public String nameAnimal(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter name for your animal: ");
        return scan.nextLine();
    }

    public void transaction(Player player, String selection){
        Animal animal = animalForSellChoices[Integer.parseInt(selection)-1];
        Animal newAnimal = (Animal) animal.clone();
        if (playerHasEnoughMoney(player.getMoney(), newAnimal.getPrice())){
            newAnimal.setName(nameAnimal());
            player.removeMoney(newAnimal.getPrice());
            player.addAnimal(newAnimal);
        }
    }


    public void transactionFood(Player player, String selection){
        Food food = foodForSellChoices[Integer.parseInt(selection)-1];
        Food newFood = (Food) food.clone();
        if (playerHasEnoughMoney(player.getMoney(), food.getPrice())){
            player.removeMoney(food.getPrice());
            player.addFood(food);
        }
    }

    public void transactionBuyingFromPlayer(Player player, String selection){
        int idx = Integer.parseInt(selection) - 1;
        int money = player.getAnimals().get(idx).getPrice() * (player.getAnimals().get(idx).getHealth() / 100);
        player.addMoney(money);
        // remove animal from players arraylist
        player.removeAnimal(Integer.parseInt(selection) - 1);
    }

    public void buyAnimalFromPlayer(Player player){
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            int idx_quit = (displayPlayerAnimalToBuy(player.getAnimals()) + 1);
            System.out.println(idx_quit + ". Done");
            System.out.print("Enter a number: ");
            String selection = scan.nextLine();
            if (Integer.parseInt(selection) < idx_quit){
                transactionBuyingFromPlayer(player, selection);
            } else if (Integer.parseInt(selection) == idx_quit){
                loop = false;
            } else {
                System.out.println("Enter a number from 1 to " + idx_quit + ".");
            }
        }
    }

    public void sellAnimal(Player player){
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            int idx_quit = (this.animalForSellChoices.length + 1);
            displayAnimalForSell();
            System.out.println(idx_quit + ". Done");
            System.out.print("Enter a number: ");
            String selection = scan.nextLine();
            if (Integer.parseInt(selection) < idx_quit) {
                transaction(player, selection);
            } else if (Integer.parseInt(selection) == idx_quit) {
                loop = false;
            } else {
                System.out.println("Enter a number from 1 to " + idx_quit + ".");
            }
        }
    }

    public void sellFood(Player player){
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            displayFoodToSell();
            System.out.println("4. Done");
            System.out.print("Enter a number: ");
            String selection = scan.nextLine();
            switch (selection){
                case "1":
                case "2":
                case "3": transactionFood(player, selection); break;
                case "4": loop = false; break;
                default:
                    System.out.println("Enter a number from 1 to 4.");
                    break;
            }
        }
    }
}