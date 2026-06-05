package com.bibliotheque.service;

import com.bibliotheque.dao.MemberDAO;
import com.bibliotheque.model.Member;

import java.util.List;

public class MemberService {
    private MemberDAO memberDAO = new MemberDAO();

    public List<Member> listAllMembers() {
        return memberDAO.getAllMembers();
    }

    public List<Member> listStudents() {
        return memberDAO.getMembersByRole("Étudiant");
    }

    public boolean toggleMemberStatus(int id, String currentStatus) {
        String nextStatus = currentStatus.equals("Actif") ? "Bloqué" : "Actif";
        return memberDAO.updateMemberStatus(id, nextStatus);
    }
}
