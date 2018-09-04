package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Ploeg> ploegen = maakPloegen();
        ArrayList<Uitslag> kalender = maakKalender(ploegen);
        for(Uitslag uitslag: kalender){
            System.out.printf("Wedstrijd %s - %s%n", uitslag.getThuisploeg(), uitslag.getUitploeg());
            System.out.printf("Geef doelpunten %s: ", uitslag.getThuisploeg());
            int doelpuntenThuis = Integer.parseInt(scanner.nextLine());
            System.out.printf("Geef doelpunten %s: ", uitslag.getUitploeg());
            int doelpuntenUit = Integer.parseInt(scanner.nextLine());
            uitslag.setDoelpuntenThuisploeg(doelpuntenThuis);
            uitslag.setDoelpuntenUitPloeg(doelpuntenUit);
        }
        toonKalender(kalender);
        for(Uitslag uitslag: kalender){
            for(Ploeg ploeg: ploegen){
                ploeg.verwerkUitslag(uitslag);
            }
        }
        Collections.sort(ploegen);
        toonPloegen(ploegen);

    }
    public static void toonPloegen(ArrayList<Ploeg> ploegen){
        System.out.printf("   %-20s  %9s %8s %4s %4s %4s %4s %4s %4s%n","Ploeg", "gespeeld", "punten", "W", "V", "G", "+", "-", "+/-");
        for(int i=0; i< ploegen.size();i++){
            System.out.printf("%2d %s%n", i+1, ploegen.get(i).getLijn());
        }
    }
    private static ArrayList<Ploeg> maakPloegen(){
        ArrayList<Ploeg> ploegen = new ArrayList<>();
        ploegen.add(new Ploeg("RSC Anderlecht"));
        ploegen.add(new Ploeg("K. RC. Genk"));
        ploegen.add(new Ploeg("Club Brugge KV"));
        ploegen.add(new Ploeg("R. Standard de Liège"));
        return ploegen;
    }
    private static ArrayList<Uitslag> maakKalender(ArrayList<Ploeg> ploegen){
        ArrayList<Uitslag> kalender = new ArrayList<>();
        LocalDate speeldag = LocalDate.of(2018, 8, 17);
        kalender.add(new Uitslag(speeldag, ploegen.get(0).getNaam(), ploegen.get(1).getNaam()));
        kalender.add(new Uitslag(speeldag, ploegen.get(2).getNaam(), ploegen.get(3).getNaam()));
        speeldag = speeldag.plusDays(7);
        kalender.add(new Uitslag(speeldag, ploegen.get(0).getNaam(),ploegen.get(2).getNaam()));
        kalender.add(new Uitslag(speeldag, ploegen.get(1).getNaam(),ploegen.get(3).getNaam()));
        speeldag = speeldag.plusDays(7);
        kalender.add(new Uitslag(speeldag, ploegen.get(0).getNaam(),ploegen.get(3).getNaam()));
        kalender.add(new Uitslag(speeldag, ploegen.get(1).getNaam(),ploegen.get(2).getNaam()));
        speeldag = speeldag.plusDays(7);
        kalender.add(new Uitslag(speeldag, ploegen.get(1).getNaam(), ploegen.get(0).getNaam()));
        kalender.add(new Uitslag(speeldag, ploegen.get(3).getNaam(), ploegen.get(2).getNaam()));
        speeldag = speeldag.plusDays(7);
        kalender.add(new Uitslag(speeldag, ploegen.get(2).getNaam(),ploegen.get(0).getNaam()));
        kalender.add(new Uitslag(speeldag, ploegen.get(3).getNaam(),ploegen.get(1).getNaam()));
        speeldag = speeldag.plusDays(7);
        kalender.add(new Uitslag(speeldag, ploegen.get(3).getNaam(),ploegen.get(0).getNaam()));
        kalender.add(new Uitslag(speeldag, ploegen.get(2).getNaam(),ploegen.get(1).getNaam()));
        return kalender;
    }

    private static void toonKalender(ArrayList<Uitslag> kalender){
        LocalDate datum = LocalDate.of(2000, 1, 1);
        for(Uitslag uitslag : kalender){
            if (!uitslag.getDatum().equals(datum))
            {
                datum = uitslag.getDatum();
                System.out.println(datum);
            }
            System.out.println(uitslag.getLijn());
        }
    }
}


class Ploeg {
    private String naam;

    public Ploeg(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }
}

class Uitslag {
    private LocalDate speeldag;
    private String thuisploeg;
    private String uitploeg;

    public Uitslag(LocalDate speeldag, String thuisploeg, String uitploeg) {
        this.speeldag = speeldag;
        this.thuisploeg = thuisploeg;
        this.uitploeg = uitploeg;
    }
}