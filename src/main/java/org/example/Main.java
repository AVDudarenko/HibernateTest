package org.example;

public class Main {
    public static void main(String[] args) {
        PetsDAO petsDAO = new PetsDAO();

        PetsInfo barsik = new PetsInfo("barsik", 12, "white", "barmaley");
        petsDAO.save(barsik);

        System.out.println("Hello world!");
    }
}