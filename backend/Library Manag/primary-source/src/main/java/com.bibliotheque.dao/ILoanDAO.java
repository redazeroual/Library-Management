package com.bibliotheque.dao;

import com.bibliotheque.model.Loan;

import java.util.Date;
import java.util.List;

public interface ILoanDAO {
    List<Loan> getAllLoans();
    List<Loan> getRecentLoans(int limit);


    boolean createLoan(int bookId, int memberId, Date dueDate);
    boolean returnBook(int loanId, int bookId);


    int countActiveLoans();
    int countOverdueLoans();
}
