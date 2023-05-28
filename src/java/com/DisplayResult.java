package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayResult extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id;

        HttpSession hs = request.getSession();
        id = (String) hs.getAttribute("ID");

        String query = "select * from DHRUV.\"RESULT\" where id=? ";

        try (PrintWriter out = response.getWriter()) {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Exp4", "dhruv", "dhruv");
                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    int total = rs.getInt(2) + rs.getInt(3) + rs.getInt(4);
                    double per = (double) total / 180;
                    out.println("<h1>Your Result</h1>");
                    out.println("<br>");
                    out.println("<h2>Your Id is " + rs.getString(1) + "</h2>");
                    out.println("<h2>Your AJT marks is " + rs.getInt(2) + "</h2>");
                    out.println("<h2>Your DPAF marks is " + rs.getInt(3) + "</h2>");
                    out.println("<h2>Your AOS marks is " + rs.getInt(4) + "</h2>");
                    out.println("<h2>Your Total marks is " + total + "</h2>");
                    out.println("<h3>Your Percentage is " + String.format("%.2f", per * 100) + "%" + "</h3>");
                }
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
