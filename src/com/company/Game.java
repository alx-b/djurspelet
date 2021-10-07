package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    ArrayList<Player> players = new ArrayList<Player>();
    Store store = new Store();

    public boolean numberIsFromMinToMax(int number, int min, int max) {
        return (number >= min && number <= max);
    }

    public int selectAmountOfRounds() {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many rounds do you want to play?");
        System.out.print("Enter a number from 5-30: ");
        while (true){
            try {
                int selection = Integer.parseInt(scan.nextLine());
                if (numberIsFromMinToMax(selection, 5, 30)){
                    return selection;
                }
            } catch (Exception e) {
                System.out.println("Needs to be a number from 5 to 30");
            }
        }
    }

    public int selectAmountOfPlayers() {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many players?");
        System.out.print("Enter a number from 1-4: ");
        while (true){
            try {
                int selection = Integer.parseInt(scan.nextLine());
                if (numberIsFromMinToMax(selection, 1, 4)){
                    return selection;
                }
            } catch (Exception e) {
                System.out.println("Needs to be a number from 1 to 4");
            }
        }
    }

    public void addPlayers(int amount){
        Scanner scan = new Scanner(System.in);
        for (int x=0; x < amount; x++){
            System.out.print("Player " + (x+1) + " enter your name: ");
            String name = scan.nextLine();
            Player player = new Player(name);
            this.players.add(player);
        }
    }


    public void displayActions(){
        String[] actions = new String[]{"1. Buy animals", "2. Buy food", "3. Sell animals", "4. Feed animals", "5. Breed animals"};
        System.out.println("==== Action ====");
        for (String s : actions) {
            System.out.println(s);
        }
    }

    public int selectAction() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a number: ");
            try {
                int value = Integer.parseInt(scan.nextLine());
                if (numberIsFromMinToMax(value, 1, 5)) {
                    return value;
                }
            } catch (Exception e) {
                System.out.print("Number should be from 1 to 5");
            }
        }
    }

    public int selectOneAnimal(Player player){
        Scanner scan = new Scanner(System.in);
        int selection = 0;
        if (!(player.getAnimals().isEmpty())){
            while (true) {
                player.getAnimals().forEach(System.out::println);
                System.out.println("Enter a number: ");
                try {
                    selection = Integer.parseInt(scan.nextLine());
                    if (numberIsFromMinToMax(selection, 1, player.getAnimals().size())) {
                        return selection;
                    }
                } catch (Exception e) {
                    System.out.print("Number should be from 1 to " + player.getAnimals().size());
                }
            }
        } else {
            System.out.println("No animals to choose!");
            return selection;
        }
    }

    public int selectOneFood(Player player){
        Scanner scan = new Scanner(System.in);
        int selection = 0;
        if (!(player.getFoods().isEmpty())) {
            while (true) {
                player.getFoods().forEach(System.out::println);
                System.out.println("Enter a number: ");
                try {
                    selection = Integer.parseInt(scan.nextLine());
                    if (numberIsFromMinToMax(selection, 1, player.getFoods().size())) {
                        return selection;
                    }
                } catch (Exception e) {
                    System.out.print("Number should be from 1 to " + player.getFoods().size());
                }
            }
        } else {
            System.out.println("No food to choose!");
            return selection;
        }
    }

    public void feedAnimals(Player player){
        Scanner scan = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            try {
                Animal animalSelection = player.getAnimals().get(selectOneAnimal(player)-1);
                Food foodSelection = player.getFoods().get(selectOneFood(player)-1);
                animalSelection.eatFood(foodSelection);
                player.removeFood(foodSelection);
                System.out.println("1. Give more food\n2. Done");
            } catch (Exception e) {
                System.out.println("No animals or Foods, turn pass");
                loop = false;
            }
            try {
                int selection = Integer.parseInt(scan.nextLine());
                if (selection == 2) {
                    loop = false;
                }
            } catch (Exception e) {
                System.out.println("Enter a number");
            }
        }
    }

    public String nameAnimal(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter name for your animal: ");
        return scan.nextLine();
    }

    public String randomAnimalSex(){
        int sexResult = (int)(Math.random() * 2) + 1;
        if (sexResult == 1){
            return "M";
        } else {
            return "F";
        }
    }

    public Animal createAnimalBasedOnType(String type, String name, String sex){
        switch (type){
            case "Sheep": return new Sheep(name, sex, 100, 40);
            case "Pig": return new Pig(name, sex, 100, 60);
            case "Cattle": return new Cattle(name, sex, 100, 80);
            case "Horse": return new Horse(name, sex, 100, 100);
            default: return new Goat(name, sex, 100, 20);
        }
    }

    public void breedAnimals(Player player){
        Animal animalSelection = player.getAnimals().get(selectOneAnimal(player)-1);
        Animal animalSelection2 = player.getAnimals().get(selectOneAnimal(player)-1);

        if (animalSelection.getType().equals(animalSelection2.getType()) && !animalSelection.getSex().equals(animalSelection2.getSex())){
            int result = (int)(Math.random() * 2) + 1;
            if (result == 1){
                System.out.println("A baby was born!");
                String sex = randomAnimalSex();
                String name = nameAnimal();
                Animal newAnimal = createAnimalBasedOnType(animalSelection.getType(), name, sex);
                player.addAnimal(newAnimal);
            } else {
                System.out.println("No baby");
            }
        } else {
            System.out.println("Need to be of same type and different sex.");
        }
    }

    public void doAction(int selection, Player player) {
        switch (selection){
            case 1: this.store.sellAnimal(player); break;
            case 2: this.store.sellFood(player); break;
            case 3: this.store.buyAnimalFromPlayer(player); break;
            case 4: feedAnimals(player); break;
            case 5: breedAnimals(player); break;
        }
    }

    public void allAnimalsLoseHealth(){
        for (Player player : this.players) {
            for (Animal animal : player.getAnimals()) {
                animal.randomLowerHealth();
            }
            player.removeDeadAnimals();
        }
    }

    public void ifNeededRemovePlayerFromGame(){
        ArrayList<Player>playersToRemove = new ArrayList<Player>();
        for (Player player : this.players){
            if (player.getMoney() <= 0 && player.getAnimals().isEmpty()) {
                playersToRemove.add(player);
            }
       }
        for (Player player : playersToRemove){
            System.out.println(player.getName() + " has been eliminated.");
            this.players.remove(player);
        }
    }

    public void sellAndRemoveAllAnimals(Player player){
        for (Animal animal : player.getAnimals()){
            player.addMoney(animal.getPrice() * animal.getHealth()/100);
        }
        player.removeAllAnimals();
    }

    public void getWinner(){
        int mostMoney = 0;
        for (Player player : this.players){
            if (player.getMoney() > mostMoney){
                mostMoney = player.getMoney();
            }
        }
        for (Player player : this.players){
            if (player.getMoney() == mostMoney){
                System.out.println(player.getName() + " has won with " + player.getMoney() + "!");
            }
        }
    }

    public void gameLoop(){
        int amountRounds = selectAmountOfRounds();
        addPlayers(selectAmountOfPlayers());

        for (int x=0; x < amountRounds; x++){
            for (Player player : this.players){
                player.displayInfo();
                displayActions();
                doAction(selectAction(), player);
            }
            allAnimalsLoseHealth();
            ifNeededRemovePlayerFromGame();
        }
        for (Player player : this.players){
            sellAndRemoveAllAnimals(player);
        }
        getWinner();
    }
}
