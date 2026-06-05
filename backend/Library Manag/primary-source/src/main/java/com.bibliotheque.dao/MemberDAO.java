package com.bibliotheque.dao;

import com.bibliotheque.model.Member;
import com.bibliotheque.model.MemberStats;
import com.bibliotheque.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO implements IMemberDAO{


    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();


        String query = "SELECT m.*, COUNT(l.id) as active_loans " +
                "FROM members m " +
                "LEFT JOIN loans l ON m.id = l.member_id AND l.status = 'En cours' " +
                "GROUP BY m.id";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("matricule"),
                        rs.getString("status"),
                        rs.getInt("active_loans")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public MemberStats getMemberStats() {
        String query = "SELECT " +
                "COUNT(*) as total, " +
                "SUM(CASE WHEN role = 'Étudiant' THEN 1 ELSE 0 END) as total_students, " +
                "SUM(CASE WHEN role = 'Professeur' THEN 1 ELSE 0 END) as total_profs " +
                "FROM members";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                return new MemberStats(
                        rs.getInt("total"),
                        rs.getInt("total_students"),
                        rs.getInt("total_profs")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Member> getMembersByRole(String role) {
        List<Member> members = new ArrayList<>();


        String query = "SELECT m.*, COUNT(l.id) as active_loans " +
                "FROM members m " +
                "LEFT JOIN loans l ON m.id = l.member_id AND l.status = 'En cours' " +
                "WHERE m.role = ? " +
                "GROUP BY m.id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, role);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                members.add(new Member(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("matricule"),
                        rs.getString("status"),
                        rs.getInt("active_loans")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }


    @Override
    public Member getMemberById(int id) {
        String query = "SELECT m.*, COUNT(l.id) as active_loans FROM members m " +
                "LEFT JOIN loans l ON m.id = l.member_id AND l.status = 'En cours' " +
                "WHERE m.id = ? GROUP BY m.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Member(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                            rs.getString("role"), rs.getString("matricule"), rs.getString("status"),
                            rs.getInt("active_loans"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Member> searchMembers(String keyword) {
        List<Member> members = new ArrayList<>();
        String query = "SELECT m.*, COUNT(l.id) as active_loans FROM members m " +
                "LEFT JOIN loans l ON m.id = l.member_id AND l.status = 'En cours' " +
                "WHERE m.name LIKE ? OR m.email LIKE ? OR m.matricule LIKE ? GROUP BY m.id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                members.add(new Member(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                        rs.getString("role"), rs.getString("matricule"), rs.getString("status"),
                        rs.getInt("active_loans")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return members;
    }
    @Override
    public boolean addMember(Member member) {
        String query = "INSERT INTO members (name, email, role, matricule, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getRole());
            pstmt.setString(4, member.getMatricule());
            pstmt.setString(5, member.getStatus());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    @Override
    public boolean updateMember(Member member) {
        String query = "UPDATE members SET name = ?, email = ?, role = ?, matricule = ?, status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getEmail());
            pstmt.setString(3, member.getRole());
            pstmt.setString(4, member.getMatricule());
            pstmt.setString(5, member.getStatus());
            pstmt.setInt(6, member.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean updateMemberStatus(int id, String newStatus) {

        String query = "UPDATE members SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, newStatus);
            pstmt.setInt(2, id);

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteMember(int id) {
        String query = "DELETE FROM members WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    @Override
    public int countTotalMembers() {
        String query = "SELECT COUNT(*) FROM members";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }
}