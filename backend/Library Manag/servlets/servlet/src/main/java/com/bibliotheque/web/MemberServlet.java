package com.bibliotheque.web;

import com.bibliotheque.model.Member;
import com.bibliotheque.service.MemberService;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/api/members")
public class MemberServlet extends HttpServlet {
    private MemberService memberService = new MemberService();
    private Gson gson = new Gson();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String role = request.getParameter("role");
        List<Member> members;

        if (role != null && !role.equals("Tous")) {
            members = memberService.listStudents();
        } else {
            members = memberService.listAllMembers();
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(gson.toJson(members));
    }
}