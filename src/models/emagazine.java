package models;

public class emagazine extends libraryitem {
    private int issueNumber;

    public emagazine(int id,String title,String author,int issueNumber) {
        super(id, title, author);
        this.issueNumber = issueNumber;
    }
    public void archiveIssue() {
        System.out.println("Archiving issue #" + issueNumber + " of " + title);
    }
    public void displayInfo() {
        System.out.println("E-Magazine: " + title + " by " + author + " [Issue " + issueNumber + "]");
    }
}
