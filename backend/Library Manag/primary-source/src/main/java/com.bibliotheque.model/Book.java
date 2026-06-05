package com.bibliotheque.model;
public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String genre;
    private String status;


    public Book(int id, String title, String author, String isbn, String genre, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.status = status;
    }


    public int getId() {return id;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public String getIsbn() {return isbn;}
    public String getGenre() {return genre;}
    public String getStatus() {return status;}

    public void setId(int id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
    public void setGenre(String genre) {this.genre = genre;}
    public void setStatus(String status) {this.status = status;}

}