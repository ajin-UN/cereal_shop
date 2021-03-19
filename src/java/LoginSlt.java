
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginSlt extends HttpServlet {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private Properties props;
    private String dbLocal;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
           
            String name = request.getParameter("name");
            String password = request.getParameter("password"); 
            DBconnection connection = new DBconnection();
            con = connection.DBCon();
            st = con.createStatement();
            
//            try{
//      
//                Class.forName("com.mysql.jdbc.Driver");
//                props = new Properties();
//                props.put("user", "root");
//                props.put("password", "287593");
//                dbLocal = "jdbc:mysql://localhost:3306/project";
//                con = DriverManager.getConnection(dbLocal,props);
//                st = con.createStatement();
//
//            }catch(Exception ex){
//
//                out.println("ConnectDB:  "+ex);
//            }

            if(checkName(name) == true){
                
                if(checkPW(name, password) == true){
                    HttpSession session=request.getSession();  
                    session.setAttribute("login","a"); 
                    session.setAttribute("dbcon", con);
                    out.println("Login sucessfully!");
                }else{
                    out.println("This is password is not correct.");
                }
  
            }else{
                
                out.println("This is not the correct name!");
            }
//            st = con.createStatement();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginSlt</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(LoginSlt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkName(String name){
        boolean result = false;
        String DBname;
        try{
            String query = "select * from users";
            rs = st.executeQuery(query);
            while(rs.next()){
                DBname = rs.getString("name");
                System.out.println("The name from DB: "+DBname);
                if(DBname.equals(name)){
                    result = true;
                    return result;
                }
            }
        
        }catch(Exception ex){
            System.out.println("CheckName:  " +ex);
        }
        
        return result;
    }
    
    public boolean checkPW(String name, String password){
        boolean result = false;

        try{
            PreparedStatement pst=null;
            ResultSet rs=null;
            String userClass;
            pst=con.prepareStatement("select * from users where name=? ");
            pst.setString(1,name);
            Statement stm = con.createStatement();
            rs=pst.executeQuery();
            while(rs.next()){
                String pw = rs.getString("password");
                System.out.println("The DBpassword: " + pw);
                if(pw.equals(password)){
                    result = true;
                    return result;
                }
            }
        }catch(Exception ex){
          System.out.println("checkPW = :  " +ex);
        }  
        return result;
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
