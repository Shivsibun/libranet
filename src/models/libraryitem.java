package models;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public abstract class libraryitem {
    protected int id;
    protected String title;
    protected String author;
    protected boolean available = true;
    protected LocalDate borrowedDate;

    public libraryitem(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isAvailable() {
        return available;
    }
    public void borrowItem(String borrowDateStr) {
        if (!available) {
            throw new IllegalStateException("Item is already borrowed.");
        }
        try {
            borrowedDate = LocalDate.parse(borrowDateStr);
            available = false;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use YYYY-MM-DD.");
        }
    }
    public int returnItem(String returnDateStr, int finePerDay) {
        if (available) {
            throw new IllegalStateException("Item was not borrowed.");
        }
        try {
            LocalDate returnDate = LocalDate.parse(returnDateStr);
            long daysBorrowed = ChronoUnit.DAYS.between(borrowedDate, returnDate);

            available = true;
            borrowedDate = null;
            //we have assumed there will be limit of 30days
            if (daysBorrowed > 30) {
                return (int) ((daysBorrowed-30)* finePerDay);
            }
            return 0;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use YYYY-MM-DD.");
        }
    }
    public boolean checkAvailability() {
        return available;
    }
    public abstract void displayInfo();
}
