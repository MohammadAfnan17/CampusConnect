package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
//import java.nio.file.Paths;
import java.sql.*;
import java.util.Enumeration;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1, // 1MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB per file
    maxRequestSize = 1024 * 1024 * 50    // 50MB total
)
@WebServlet("/TpRegister")
public class TpRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final String DB_URL = "jdbc:mysql://localhost:3306/campus_connect";
    private final String DB_USER = "root";
    private final String DB_PASS = "123456";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Debug: Print all received parameters
        System.out.println("------ Form Parameters ------");
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String param = paramNames.nextElement();
            System.out.println(param + " = " + request.getParameter(param));
        }
        System.out.println("-----------------------------");

        // Extract form fields
        String name = request.getParameter("name");
        String rollno = request.getParameter("rollno");
        String gender = request.getParameter("gender");
        String mobile = request.getParameter("mobile");
        String dob = request.getParameter("dob");
        String aadhar = request.getParameter("aadhar");
        String domain_email = request.getParameter("domain_email");
        String personal_email = request.getParameter("personal_email");
        String current_address = request.getParameter("current_address");
        String permanent_address = request.getParameter("permanent_address");
        String payment_date = request.getParameter("payment_date");
        String payment_reference = request.getParameter("payment_reference");
        String amount_paid = request.getParameter("amount_paid");
        String btech_branch = request.getParameter("btech_branch");
        String btech_cgpa = request.getParameter("btech_cgpa");
        String btech_backlogs = request.getParameter("btech_backlogs");
        String btech_gaps = request.getParameter("btech_gaps");
        String ssc_marks = request.getParameter("ssc_marks");
        String inter_marks = request.getParameter("inter_marks");
        String option_selected = request.getParameter("option_selected");
        String first_priority = request.getParameter("first_priority");
        String second_priority = request.getParameter("second_priority");
        String father_name = request.getParameter("father_name");
        String father_mobile = request.getParameter("father_mobile");
        String mother_name = request.getParameter("mother_name");
        String mother_mobile = request.getParameter("mother_mobile");

        // Parse passing year safely
        String passingYearStr = request.getParameter("passing_year");
        System.out.println("passing_year = " + passingYearStr); // Debug

        String passing_year = request.getParameter("passing_year");
        if (passing_year == null || passing_year.trim().isEmpty()) {
            response.getWriter().println("<h3 style='color:red;'>Missing passing year.</h3>");
            return;
        }


        // File uploads
//        Part sscMemoPart = request.getPart("ssc_memo");
//        Part interMemoPart = request.getPart("inter_memo");

//        String uploadPath = "C:/CampusConnect/uploads/";
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) uploadDir.mkdirs();
//
//        String sscFileName = Paths.get(sscMemoPart.getSubmittedFileName()).getFileName().toString();
//        String interFileName = Paths.get(interMemoPart.getSubmittedFileName()).getFileName().toString();

//        sscMemoPart.write(uploadPath + sscFileName);
//        interMemoPart.write(uploadPath + interFileName);

        // DB insert
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String sql = "INSERT INTO tp_registration (name, rollno, gender, mobile, dob, aadhar, domain_email, personal_email," +
                    "current_address, permanent_address, payment_date, payment_reference, amount_paid, btech_branch, btech_cgpa," +
                    "btech_backlogs, btech_gaps, ssc_marks, inter_marks, option_selected, first_priority, second_priority," +
                    "father_name, father_mobile, mother_name, mother_mobile, passing_year) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, rollno);
            stmt.setString(3, gender);
            stmt.setString(4, mobile);
            stmt.setString(5, dob);
            stmt.setString(6, aadhar);
            stmt.setString(7, domain_email);
            stmt.setString(8, personal_email);
            stmt.setString(9, current_address);
            stmt.setString(10, permanent_address);
            stmt.setString(11, payment_date);
            stmt.setString(12, payment_reference);
            stmt.setString(13, amount_paid);
            stmt.setString(14, btech_branch);
            stmt.setString(15, btech_cgpa);
            stmt.setString(16, btech_backlogs);
            stmt.setString(17, btech_gaps);
            stmt.setString(18, ssc_marks);
            stmt.setString(19, inter_marks);
            stmt.setString(20, option_selected);
            stmt.setString(21, first_priority);
            stmt.setString(22, second_priority);
            stmt.setString(23, father_name);
            stmt.setString(24, father_mobile);
            stmt.setString(25, mother_name);
            stmt.setString(26, mother_mobile);
            stmt.setString(27, passing_year);

            stmt.executeUpdate();
            conn.close();

            response.sendRedirect("success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("<h3 style='color:red;'>Database Error: " + e.getMessage() + "</h3>");
        }
    }
}
