package com.bibliotheque.model;
public class MemberStats {
    public int total;
    public int students;
    public int professors;

    public MemberStats(int total, int students, int professors) {
        this.total = total;
        this.students = students;
        this.professors = professors;
    }
}