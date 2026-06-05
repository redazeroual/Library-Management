package com.bibliotheque.dao;

import com.bibliotheque.model.Member;

import java.util.List;

public interface IMemberDAO {
    List<Member> getAllMembers();
    List<Member> getMembersByRole(String role);
    Member getMemberById(int id);
    List<Member> searchMembers(String keyword);

    boolean addMember(Member member);
    boolean updateMember(Member member);
    boolean updateMemberStatus(int id, String status);
    boolean deleteMember(int id);


    int countTotalMembers();
}
