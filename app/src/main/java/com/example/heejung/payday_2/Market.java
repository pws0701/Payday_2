package com.example.heejung.payday_2;

import android.graphics.Bitmap;
/**
 * Created by Heejung on 2015-11-24.
 */
public class Market {

    private String bookId;
    private String title;
    private String titleMinor;
    private String author;
    private String publisher;
    private String edition;
    private String datePub;
    private String price;
    private String countSeller;
    private String minSellPrice;
    private String bookImageUrl;

    public String getBookImageUrl() {
        return bookImageUrl;
    }

    public void setBookImageUrl(String bookImageUrl) {
        this.bookImageUrl = bookImageUrl;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleMinor() {
        return titleMinor;
    }

    public void setTitleMinor(String titleMinor) {
        this.titleMinor = titleMinor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getDatePub() {
        return datePub;
    }

    public void setDatePub(String datePub) {
        this.datePub = datePub;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCountSeller() {
        return countSeller;
    }

    public void setCountSeller(String countSeller) {
        this.countSeller = countSeller;
    }

    public String getMinSellPrice() {
        return minSellPrice;
    }

    public void setMinSellPrice(String minSellPrice) {
        this.minSellPrice = minSellPrice;
    }
}
