package com.bibliotheque.dao;

import com.bibliotheque.model.Loan;
import com.bibliotheque.model.Member;
import com.bibliotheque.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class LoanDAO implements ILoanDAO{

    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT l.id, b.title, b.isbn, m.name, l.loan_date, l.due_date, l.status " +
                "FROM loans l " +
                "JOIN books b ON l.book_id = b.id " +
                "JOIN members m ON l.member_id = m.id " +
                "ORDER BY l.loan_date DESC";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                loans.add(new Loan(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getDate("loan_date"),
                        rs.getDate("due_date"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public List<Loan> getRecentLoans(int limit) {
        List<Loan> loans = new ArrayList<>();

        String query = "SELECT l.id, b.title, b.isbn, m.name, l.loan_date, l.due_date, l.status " +
                "FROM loans l " +
                "JOIN books b ON l.book_id = b.id " +
                "JOIN members m ON l.member_id = m.id " +
                "ORDER BY l.loan_date DESC LIMIT ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                loans.add(new Loan(
                        rs.getInt("id"), rs.getString("title"), rs.getString("isbn"),
                        rs.getString("name"), rs.getDate("loan_date"),
                        rs.getDate("due_date"), rs.getString("status")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return loans;
    }
    @Override
    public boolean createLoan(int bookId, int memberId, java.util.Date dueDate) {
        return createLoan(bookId, memberId, new java.sql.Date(dueDate.getTime()));
    }

    public boolean createLoan(int bookId, int memberId, Date dueDate) {
        String sqlLoan = "INSERT INTO loans (book_id, member_id, loan_date, due_date, status) VALUES (?, ?, CURDATE(), ?, 'EN COURS')";
        String sqlBook = "UPDATE books SET status = 'Emprunté' WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psL = conn.prepareStatement(sqlLoan);
                 PreparedStatement psB = conn.prepareStatement(sqlBook)) {

                // Insertion du prêt
                psL.setInt(1, bookId);
                psL.setInt(2, memberId);
                psL.setDate(3, dueDate);
                psL.executeUpdate();

                // Mise à jour du statut du livre
                psB.setInt(1, bookId);
                psB.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean returnBook(int loanId, int bookId) {
        String sqlLoan = "UPDATE loans SET status = 'RENDU' WHERE id = ?";
        String sqlBook = "UPDATE books SET status = 'Disponible' WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Début de la transaction

            try (PreparedStatement psL = conn.prepareStatement(sqlLoan);
                 PreparedStatement psB = conn.prepareStatement(sqlBook)) {


                psL.setInt(1, loanId);
                psL.executeUpdate();


                psB.setInt(1, bookId);
                psB.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int countActiveLoans() {

        String query = "SELECT COUNT(*) FROM loans WHERE status = 'EN COURS'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    @Override
    public int countOverdueLoans() {

        String query = "SELECT COUNT(*) FROM loans WHERE due_date < CURDATE() AND status = 'EN COURS'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }


}
