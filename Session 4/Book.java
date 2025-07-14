public class Book {
    public String author;
    public String title;
    public int pages;
    Book(String title, int pages, String author){
        this.title = title;
        this.pages = pages;
        this.author = author;
    }
    public void showAuthor(){
        System.out.println("Book: " + title + pages + "pages. Author: " + author);
    //    System.out.println("Author: ", author);
   }
    public void displayinfo(){
        System.out.println("Author: " + author);
        System.out.println("Title: " + title);
        System.out.println("Pages: " + pages);
    }
}
