package models;

public class book extends libraryitem {
    private int pageCount;

    public book(int id,String title,String author,int pageCount) {
        super(id,title,author);
        this.pageCount = pageCount;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void displayInfo() {
        System.out.println("Book: " + title + " written by: " + author + " [" + pageCount + " pages]");
    }
}
