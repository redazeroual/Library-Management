package com.bibliotheque.model;

public class DashboardStats {
    private int totalBooks;
    private int activeLoans;
    private int overdueLoans;

    public DashboardStats(int totalBooks, int activeLoans, int overdueLoans) {
        this.totalBooks = totalBooks;
        this.activeLoans = activeLoans;
        this.overdueLoans = overdueLoans;
    }

    public int getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(int totalBooks) {
        this.totalBooks = totalBooks;
    }

    public int getActiveLoans() {
        return activeLoans;
    }

    public void setActiveLoans(int activeLoans) {
        this.activeLoans = activeLoans;
    }

    public int getOverdueLoans() {
        return overdueLoans;
    }

    public void setOverdueLoans(int overdueLoans) {
        this.overdueLoans = overdueLoans;
    }
}
