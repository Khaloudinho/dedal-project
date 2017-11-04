package fr.miage.m2;

public class Main {

    public static void main(String[] args) {

        Player p = new Player("Guillaume", "Bertrand");
        Player p2 = new Player("Khaled", "Bouguettoucha");
        Player p3 = new Player("Alexandre", "Gadeau");

        HMIPlayer hp = new HMIPlayer(p);
        HMIPlayer hp2 = new HMIPlayer(p2);
        HMIPlayer hp3 = new HMIPlayer(p3);

        HMIDice hd = new HMIDice();
        hd.addObserver(hp);
        hd.addObserver(hp2);
        hd.addObserver(hp3);

        hd.setValue(4);
        hd.setValue(6);
        hd.setValue(2);
    }

}
