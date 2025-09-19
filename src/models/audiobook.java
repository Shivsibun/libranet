package models;

import interfaces.playable;

public class audiobook extends libraryitem implements playable{
    private int durationMinutes;

    public audiobook(int id,String title,String author,int durationMinutes) {
        super(id,title,author);
        this.durationMinutes = durationMinutes;
    }
    public int getDurationMinutes() {
        return durationMinutes;
    }
    public void play() {
        System.out.println("Playing audiobook: " + title + " (" + durationMinutes + " mins)");
    }
    public void displayInfo() {
        System.out.println("Audiobook: " + title + " written by " + author + " [" + durationMinutes + " mins]");
    }
}
