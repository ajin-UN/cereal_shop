
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 *
 * @author apple
 */
public class RegisterSlt extends HttpServlet {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private Properties props;
    private String dbLocal;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        try (PrintWriter out = response.getWriter()) {
            DBconnection connection = new DBconnection();
            con = connection.DBCon();
            HttpSession session=request.getSession();  
            session.setAttribute("dbcon", con);
            
            try{
                String query = "insert into users values(0,?,?,?,?,?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                String name = request.getParameter("name");
                String addr = request.getParameter("address");
                String email = request.getParameter("email");
                int cc = Integer.parseInt(request.getParameter("cc"));
                String password = request.getParameter("password");
                preparedStmt.setString(1, name);
                preparedStmt.setString(2, addr);
                preparedStmt.setString(3, email);
                preparedStmt.setInt(4, cc);
                preparedStmt.setString(5, password);
                preparedStmt.executeUpdate();
                out.println("Register sucessfully, now you can login.");

            }catch(Exception ex){
                
                System.out.println("WriteData:  "+ex);
            }
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet registerSlt</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
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
