import models.*;
import services.librarymanager;

import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        librarymanager manager = new librarymanager();

        //some items have been already present in the library
        manager.addItem(new book(1, "Java Basics", "James Gosling", 500));
        manager.addItem(new audiobook(2, "Clean Code", "Robert C. Martin", 300));
        manager.addItem(new emagazine(3, "Tech Today", "Various", 45));

        while (true) {
            System.out.println("\n-----LibraryManagement Menu-----");
            System.out.println("1. View All Items");
            System.out.println("2. Borrow Item");
            System.out.println("3. Return Item");
            System.out.println("4. Search by Type");
            System.out.println("5. Donate Item");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    for (libraryitem item : manager.getAllItems()) {
                        item.displayInfo();
                        System.out.println("Available: " + item.checkAvailability());
                    }
                    break;

                case 2:
                    System.out.print("Enter Item ID to borrow: ");
                    int borrowId = sc.nextInt();
                    sc.nextLine();
                    libraryitem itemToBorrow = manager.getItem(borrowId);
                    if (itemToBorrow != null) {
                        System.out.print("Enter borrow date (YYYY-MM-DD): ");
                        String date = sc.nextLine();
                        try {
                            itemToBorrow.borrowItem(date);
                            System.out.println("Item borrowed successfully!");
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Item ID to return: ");
                    int returnId = sc.nextInt();
                    sc.nextLine();
                    libraryitem itemToReturn = manager.getItem(returnId);
                    if (itemToReturn != null) {
                        System.out.print("Enter return date (YYYY-MM-DD): ");
                        String date = sc.nextLine();
                        try {
                            int fine = itemToReturn.returnItem(date, 10); // fine = 10 rs/day
                            System.out.println("Item returned. Fine: " + fine + "rs");
                        } catch (Exception e) {
                            System.out.println("Error has been occurred: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 4:
                    System.out.println("Search Type: 1=Book, 2=Audiobook, 3=E-Magazine");
                    int typeChoice = sc.nextInt();
                    sc.nextLine();

                    Class<?> type = null;
                    if(typeChoice == 1) type = book.class;
                    else if(typeChoice == 2) type = audiobook.class;
                    else if(typeChoice == 3) type = emagazine.class;

                    if (type!= null){
                        for(libraryitem item : manager.searchByType(type)) {
                            item.displayInfo();
                        }
                    } 
                    else{
                        System.out.println("Invalid choice.");
                    }
                    break;
                //if a user want to donate an item to the library
                case 5:
                    System.out.println("Donate: Choose type (1=Book, 2=Audiobook, 3=E-Magazine)");
                    int donateType = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter ID: ");
                    int newId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    switch (donateType) {
                        case 1:
                            System.out.print("Enter Page Count: ");
                            int pages = sc.nextInt();
                            sc.nextLine();
                            manager.addItem(new book(newId, title, author, pages));
                            System.out.println("Book donated successfully!");
                            break;
                        case 2:
                            System.out.print("Enter Duration (minutes): ");
                            int duration = sc.nextInt();
                            sc.nextLine();
                            manager.addItem(new audiobook(newId, title, author, duration));
                            System.out.println("Audiobook donated successfully!");
                            break;
                        case 3:
                            System.out.print("Enter Issue Number: ");
                            int issue = sc.nextInt();
                            sc.nextLine();
                            manager.addItem(new emagazine(newId, title, author, issue));
                            System.out.println("E-Magazine donated successfully!");
                            break;
                        default:
                            System.out.println("Invalid type.");
                    }
                    break;

                case 6:
                    System.out.println("Thank you. Please visit again.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.You can try again.");
            }
        }
    }
}
