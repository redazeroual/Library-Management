package com.bibliotheque.model;

import java.util.Date;


public class Loan {
    private int id;
    private int bookId;
    private int memberId;


    private String bookTitle;
    private String bookIsbn;
    private String memberName;
    private String memberMatricule;

    private Date loanDate;
    private Date dueDate;
    private String status; // 'EN COURS', 'RETARD', 'RENDU'


    public Loan(int id, String bookTitle, String bookIsbn, String memberName, Date loanDate, Date dueDate, String status) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookIsbn = bookIsbn;
        this.memberName = memberName;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters    public String getBookTitle() { return bookTitle; }
    public String getMemberName() { return memberName; }
    public String getStatus() { return status; }
    public Date getLoanDate() { return loanDate; }
    public Date getDueDate() { return dueDate; }

    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
    public void setLoanDate(Date loanDate) { this.loanDate = loanDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public void setStatus(String status) { this.status = status; }


}