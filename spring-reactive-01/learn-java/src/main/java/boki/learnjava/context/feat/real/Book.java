package boki.learnjava.context.feat.real;

public class Book {
    private String isbn;
    private String bookName;
    private String author;

    public Book(String isbn, String bookName, String author) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
