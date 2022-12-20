package org.example;

public class Main {
    public static void main(String[] args) {
        PetsDAO petsDAO = new PetsDAO();

        PetsInfo barsik = new PetsInfo("window", 22, "black", "gulia");
        petsDAO.save(barsik);

        System.out.println("Hello world!");
    }
}