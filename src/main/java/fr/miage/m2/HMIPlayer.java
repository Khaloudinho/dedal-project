package fr.miage.m2;

import java.util.Observable;
import java.util.Observer;

public class HMIPlayer extends Player implements Observer {

    public HMIPlayer() { }

    public HMIPlayer(Player p) {
        super(p.getFirstname(), p.getLastname(), new Dice());
    }

    public String getFirstname() {
        return super.getFirstname();
    }

    public void setFirstname(String firstname) {
         super.setFirstname(firstname);
    }

    public String getLastname() {
        return super.getLastname();
    }

    public void setLastname(String lastname) {
        super.setLastname(lastname);
    }

    public int getPoints() {
        return super.getPoints();
    }

    public String toString() {
        return super.toString();
    }

    public void update(Observable obs, Object obj) {
        if (obs instanceof HMIDice){
            System.out.println(this.getFirstname() + " " + this.getLastname() + " dit que la valeur du dé est : " + ((HMIDice) obs).getValue());
        }
    }
}