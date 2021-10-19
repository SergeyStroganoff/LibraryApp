package org.stroganov.entities;

public class Book {
   private String numberISBN;
   private String name;
   private Author authorName;
   private int yearPublishing;
   private int pagesNumber;

   public Book(String numberISBN, String name, Author authorName, int yearPublishing, int pagesNumber) {
      this.numberISBN = numberISBN;
      this.name = name;
      this.authorName = authorName;
      this.yearPublishing = yearPublishing;
      this.pagesNumber = pagesNumber;
   }
}
