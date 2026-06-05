package com.bibliotheque.service;

import com.bibliotheque.dao.LoanDAO;
import com.bibliotheque.dao.MemberDAO;

import java.util.Date;

public class LoanService {
    private LoanDAO loanDAO = new LoanDAO();
    private MemberDAO memberDAO = new MemberDAO();

    public String processNewLoan(int bookId, int memberId, Date dueDate) {

        Date today = new Date(System.currentTimeMillis());
        if (dueDate.before(today)) {
            return "Erreur : La date de retour doit être dans le futur.";
        }


        boolean success = loanDAO.createLoan(bookId, memberId, (java.sql.Date) dueDate);

        if (success) {
            return "Succès : Prêt enregistré.";
        } else {
            return "Erreur : Problème technique lors de l'enregistrement.";
        }
    }
}
