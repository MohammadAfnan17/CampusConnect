package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/ViewStudentsByYear")
public class ViewStudentsByYear extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/campus_connect";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String year = request.getParameter("year");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Start HTML output with improved styling
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Batch " + year + " Students</title>");
        out.println("<link href='https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap' rel='stylesheet'>");
        out.println("<style>");
        out.println("body {");
        out.println("    font-family: 'Inter', sans-serif;");
        out.println("    background-color: #f4f7f6;");
        out.println("    margin: 0;");
        out.println("    padding: 20px;");
        out.println("    color: #333;");
        out.println("    display: flex;");
        out.println("    flex-direction: column;");
        out.println("    align-items: center;");
        out.println("}");
        out.println("h2 {");
        out.println("    color: #2c3e50;");
        out.println("    margin-bottom: 25px;");
        out.println("    text-align: center;");
        out.println("}");
        out.println("table {");
        out.println("    width: 90%;");
        out.println("    max-width: 1200px;");
        out.println("    border-collapse: collapse;");
        out.println("    margin-bottom: 30px;");
        out.println("    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);");
        out.println("    border-radius: 8px;");
        out.println("    overflow: hidden; /* Ensures rounded corners apply to table content */");
        out.println("}");
        out.println("th, td {");
        out.println("    padding: 15px;");
        out.println("    text-align: left;");
        out.println("    border-bottom: 1px solid #ddd;");
        out.println("}");
        out.println("th {");
        out.println("    background-color: #4CAF50;");
        out.println("    color: white;");
        out.println("    font-weight: 600;");
        out.println("    text-transform: uppercase;");
        out.println("}");
        out.println("tr:nth-child(even) {");
        out.println("    background-color: #f9f9f9;");
        out.println("}");
        out.println("tr:hover {");
        out.println("    background-color: #eef;");
        out.println("    transition: background-color 0.3s ease;");
        out.println("}");
        out.println("td {");
        out.println("    color: #555;");
        out.println("}");
        out.println(".download-button {");
        out.println("    background-color: #007bff;");
        out.println("    color: white;");
        out.println("    padding: 12px 25px;");
        out.println("    border: none;");
        out.println("    border-radius: 5px;");
        out.println("    cursor: pointer;");
        out.println("    font-size: 16px;");
        out.println("    transition: background-color 0.3s ease, transform 0.2s ease;");
        out.println("    text-decoration: none;");
        out.println("    display: inline-block;");
        out.println("    margin-top: 20px;");
        out.println("    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);");
        out.println("}");
        out.println(".download-button:hover {");
        out.println("    background-color: #0056b3;");
        out.println("    transform: translateY(-2px);");
        out.println("}");
        out.println(".message {");
        out.println("    text-align: center;");
        out.println("    padding: 20px;");
        out.println("    font-size: 1.1em;");
        out.println("    color: #555;");
        out.println("    background-color: #fff;");
        out.println("    border-radius: 8px;");
        out.println("    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);");
        out.println("    width: 90%;");
        out.println("    max-width: 600px;");
        out.println("    margin-top: 20px;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Registered Students - Batch " + year + "</h2>");


        out.println("<table border='0' cellpadding='10'>"); // Remove border attribute as it's handled by CSS
        out.println("<tr>" +
                "<th>Name</th><th>Email</th><th>Mobile</th><th>Gender</th>" +
                "<th>Branch</th><th>B.Tech %</th><th>Inter %</th><th>SSC %</th>" +
                "<th>Passing Year</th></tr>");

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String query = "SELECT name, domain_email, mobile, gender, btech_branch, btech_cgpa, inter_marks, ssc_marks, passing_year FROM tp_registration WHERE passing_year = ?";
            pst = con.prepareStatement(query);
            pst.setString(1, year);
            rs = pst.executeQuery();

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                out.println("<tr>");
                out.println("<td>" + rs.getString("name") + "</td>");
                out.println("<td>" + rs.getString("domain_email") + "</td>");
                out.println("<td>" + rs.getString("mobile") + "</td>");
                out.println("<td>" + rs.getString("gender") + "</td>");
                out.println("<td>" + rs.getString("btech_branch") + "</td>");
                out.println("<td>" + rs.getString("btech_cgpa") + "</td>");
                out.println("<td>" + rs.getString("inter_marks") + "</td>");
                out.println("<td>" + rs.getString("ssc_marks") + "</td>");
                out.println("<td>" + rs.getString("passing_year") + "</td>");
                out.println("</tr>");
            }

            if (!hasData) {
                out.println("<tr><td colspan='9'><div class='message'>No students found for batch " + year + "</div></td></tr>");
            }

        } catch (Exception e) {
            out.println("<tr><td colspan='9'><div class='message' style='color: red;'>Error: " + e.getMessage() + "</div></td></tr>");
            System.err.println("Database error: " + e.getMessage()); // Log error on server side
        } finally {
            // Close resources in reverse order of opening
            try { if (rs != null) rs.close(); } catch (SQLException e) { System.err.println("Error closing ResultSet: " + e.getMessage()); }
            try { if (pst != null) pst.close(); } catch (SQLException e) { System.err.println("Error closing PreparedStatement: " + e.getMessage()); }
            try { if (con != null) con.close(); } catch (SQLException e) { System.err.println("Error closing Connection: " + e.getMessage()); }
        }

        out.println("</table>");

        // Move the download button to the bottom of the table
        out.println("<form action='DownloadStudentsPDF' method='post'>");
        out.println("<input type='hidden' name='year' value='" + year + "'>");
        out.println("<button type='submit' class='download-button'>Download </button>");
        out.println("</form>");

        out.println("</body></html>");
    }
}
