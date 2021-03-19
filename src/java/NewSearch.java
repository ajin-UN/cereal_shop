
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SearchSlt extends HttpServlet 
{
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private Properties props;
    private String dbLocal;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        DBconnection connection = new DBconnection();
        con = connection.DBCon();
        try {
            st = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SearchSlt.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter out = response.getWriter()) 
        {
            
            Object value = request.getSession().getAttribute("login");
//            String value = "a";
//          
            if(value.equals("a")){
                string brand = request.getParameter("brand");
                string name = request.getParameter("name");
                string expiration = request.getParameter("expiration");
                float sugar  = Float.parseFloat(request.getParameter("sugar"));
                string sugarequal = request.getParameter("");
                string brand = request.getParameter("brand");
                string brand = request.getParameter("brand");

                float fiber  = Float.parseFloat(request.getParameter("fiber"));
                float price  = Float.parseFloat(request.getParameter("price"));

                String detail = request.getParameter("detail");
                int range = 0;
                if(request.getParameter("range") != ""){
                    
                    range  = Integer.parseInt(request.getParameter("range"));

                }
                                
                if(choice == 1)
                {
                    
                    out.println("This is search for brand: " + detail);
                    ArrayList<Integer> results = searchBrand(detail);
                    
                    if(results == null){
                        out.println("There is no matches.");
                    }else
                    {
                        for(int i = 0; i < results.size(); i++)
                        {
                            out.println(print(results.get(i)));
                        }
                    }

                }
                else if(choice == 2)
                {
                    out.println("This is search for name: " + detail);
                    ArrayList<Integer> results = searchName(detail);
                    if(results == null){
                        out.println("There is no natches.");
                    }else
                    {
                        for(int i = 0; i < results.size(); i++)
                        {
                            out.println(print(results.get(i)));
                        }
                    }              
                }
                else if(choice ==3)
                {
                    int detailint = Integer.parseInt(detail);
                    if(range == 1){                       
                        out.println("This is search for sugar is equal to: " + detail);
                    }else if(range ==2){
                        out.println("This is search for sugar is greater and equal than: " + detail);
                    }else if(range ==3){
                        out.println("This is search for sugar is less and equal than: " + detail);
                    }
                    ArrayList<Integer> results = searchSugar(detailint, range);
                    if(results == null){
                        out.println("There is no natches.");
                    }else
                    {
                        for(int i = 0; i < results.size(); i++)
                        {
                            out.println(print(results.get(i)));
                        }
                    }
                }
                else if(choice == 4)
                {
                    int detailint = Integer.parseInt(detail);
                    if(range == 1){                       
                        out.println("This is search for fiber is equal to: " + detail);
                    }else if(range ==2){
                        out.println("This is search for fiber is greater and equal than: " + detail);
                    }else if(range ==3){
                        out.println("This is search for fiber is less and equal than: " + detail);
                    }
                    ArrayList<Integer> results = searchFiber(detailint, range);
                    if(results == null){
                        out.println("There is no natches.");
                    }else
                    {
                        for(int i = 0; i < results.size(); i++)
                        {
                            out.println(print(results.get(i)));
                        }
                    }
                }
                else if(choice == 5)
                {
                    out.println("This is search for expire date: " + detail);
                    ArrayList<Integer> results = searchExpriation(detail);
                    if(results == null){
                        out.println("There is no natches.");
                    }else
                    {
                        for(int i = 0; i < results.size(); i++)
                        {
                            out.println(print(results.get(i)));
                        }
                    } 
                }
                else if(choice ==6)
                {
                    float detailint = Float.parseFloat(detail);
                    if(range == 1){                       
                        out.println("This is search for price is equal to: " + detail);
                    }else if(range ==2){
                        out.println("This is search for price is greater and equal than: " + detail);
                    }else if(range ==3){
                        out.println("This is search for price is less and equal than: " + detail);
                    }
                    ArrayList<Integer> results = searchPrice(detailint, range);
                    if(results == null){
                        out.println("There is no natches.");
                    }else
                    {
                        for(int i = 0; i < results.size(); i++)
                        {
                            out.println(print(results.get(i)));
                        }
                    }
                }else
                {
                    
                    out.println("Please input the correct number from 1-6.");
                }    
           
            }else{
                
//                out.println("You should login first");
                System.out.println("Log in first.");
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SearchSlt</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }
   
    public ArrayList<Integer> searchBrand(String brand)
    {
        ArrayList<Integer> sets = new ArrayList<>();        
        
        try
        {
//            Connection con = (Connection)session.getAttribute("dbcon");

            PreparedStatement pst=con.prepareStatement("select * from cereal where brand = ?");
            pst.setString(1,brand);
            ResultSet rs=pst.executeQuery();            
            while(rs.next())
            {
                int id = rs.getInt("idcereal");
                sets.add(id);          
            }
            return sets;
        }catch(Exception ex)
        {
            System.out.println("searchBrand:  " + ex);

        }
        return sets;
    }
    
    public ArrayList<Integer> searchName(String name)
    {
        ArrayList<Integer> sets = new ArrayList<>();        

        try
        {
            PreparedStatement pst=con.prepareStatement("select * from cereal where name = ? ");
            pst.setString(1,name);
            ResultSet rs=pst.executeQuery();  
            while(rs.next())
            {
                int id = rs.getInt("idcereal");
                sets.add(id);   
            }
        
        }catch(Exception ex)
        {
            System.out.println("searchName:  " +ex);
        } 
        return sets;
    }
    
    public ArrayList<Integer> searchSugar(int sugar, int range)
    {
        ArrayList<Integer> sets = new ArrayList();
        if(range == 1)
        {
            try{
                PreparedStatement pst=null;
                ResultSet rs=null;
                pst=con.prepareStatement("select * from cereal where sugargram =? ");
                pst.setInt(1,sugar);
                rs=pst.executeQuery();
                while(rs.next())
                {
                    int id = rs.getInt("idcereal");
                    sets.add(id);
                }
            }catch(Exception ex)
            {
              System.out.println("searchSugar = :  " +ex);
            } 
            return sets;
        }
        else if(range == 2)
        {
            try{
                PreparedStatement pst=null;
                ResultSet rs=null;
                pst=con.prepareStatement("select * from cereal where sugargram >=? ");
                pst.setInt(1,sugar);
                rs=pst.executeQuery();
                while(rs.next())
                {
                    int id = rs.getInt("idcereal");
                    sets.add(id);
                }
            }catch(Exception ex)
            {
              System.out.println("searchSugar>= :  " +ex);
            } 
            return sets;
        }
        else if(range ==3){
            try{
                PreparedStatement pst=null;
                ResultSet rs=null;
                pst=con.prepareStatement("select * from cereal where sugargram <=? ");
                pst.setInt(1,sugar);
                rs=pst.executeQuery();
                while(rs.next())
                {
                    int id = rs.getInt("idcereal");
                    sets.add(id);
                }
            }catch(Exception ex)
            {
              System.out.println("searchSugar<= :  " +ex);
            } 
            return sets;
        }else
        {
            System.out.println("Please select a valid number from 1-3.");
        }
        return sets;
    }
    public ArrayList<Integer> searchFiber(int fiber, int range)
    {
        ArrayList<Integer> sets = new ArrayList();
        if(range == 1)
        {
            try
            {
                PreparedStatement pst=con.prepareStatement("select * from cereal where fibergram =? ");
                pst.setInt(1,fiber);
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    int id = rs.getInt("idcereal");
                    sets.add(id);
                }
            }catch(Exception ex)
            {
              System.out.println("searchFiber = :  " +ex);
            }     
        }
        else if(range == 2)
        {
            try
            {
                
                PreparedStatement pst=con.prepareStatement("select * from cereal where fibergram >=? ");
                pst.setInt(1,fiber);
                ResultSet rs=pst.executeQuery();
                while(rs.next())
                {
                    int id = rs.getInt("idcereal");
                    sets.add(id);
                }
            }catch(Exception ex)
            {
              System.out.println("searchFiber>= :  " +ex);
            } 
        }
        else if(range ==3)
        {
            try
            {
               
                PreparedStatement pst=con.prepareStatement("select * from cereal where fibergram <=? ");
                pst.setInt(1,fiber);
                ResultSet rs=pst.executeQuery();
                while(rs.next())
                {
                    int id = rs.getInt("idcereal");
                    sets.add(id);
                }
            }catch(Exception ex)
            {
              System.out.println("searchFiber<= :  " +ex);
            } 
        }else
        {
            System.out.println("Please select a valid number from 1-3.");
        } 
        return sets;
    }
    public ArrayList<Integer> searchExpriation(String date)
    {
        ArrayList<Integer> sets = new ArrayList();
        try
        {
            PreparedStatement pst=con.prepareStatement("select * from cereal where expiredate = ? ");
            pst.setString(1,date);
            ResultSet rs=pst.executeQuery(); 
            while(rs.next())
            {
                int id = rs.getInt("idcereal");
                sets.add(id);
                
            }
        
        }catch(Exception ex)
        {
            System.out.println("searchExpiration:  " +ex);
        }  
        return sets;
    }
    public ArrayList<Integer> searchPrice(float price, int range)
    {
        ArrayList<Integer> sets = new ArrayList();
        if(range == 1)
        {
            try
            {
                PreparedStatement pst=con.prepareStatement("select * from cereal where price =? ");
                pst.setFloat(1,price);
                ResultSet rs=pst.executeQuery();
                while(rs.next())
                {
                    int id = rs.getInt("idcereal");
                    sets.add(id);
                }
            }catch(Exception ex)
            {
              System.out.println("searchPrice = :  " +ex);
            }     
        }
        else if(range == 2)
        {
            try
            {
                PreparedStatement pst=con.prepareStatement("select * from cereal where price >=? ");
                pst.setFloat(1,price);
                ResultSet rs=pst.executeQuery();
                while(rs.next())
                {
                    int id = rs.getInt("idcereal");
                    sets.add(id);
                }
            }catch(Exception ex)
            {
              System.out.println("searchPrice>= :  " +ex);
            } 
        }
        else if(range ==3)
        {
            try{
                PreparedStatement pst=con.prepareStatement("select * from cereal where price <=? ");
                pst.setFloat(1,price);
                ResultSet rs=pst.executeQuery();
                while(rs.next())
                {
                    int id = rs.getInt("idcereal");
                    sets.add(id);
                }
            }catch(Exception ex)
            {
              System.out.println("searchPrice<= :  " +ex);
            } 
        }else
        {
            System.out.println("Please select a valid number from 1-3.");
        }
        return sets;
        
    }
    public String print(int id)
    {
        String result = "";
        try
        {           
            String query = "select * from cereal";
            rs = st.executeQuery(query);
            while(rs.next())
            {
                int DBid = rs.getInt("idcereal");
                if(DBid == id)
                {
                    String Brand = rs.getString("brand");
                    String Name = rs.getString("name");
                    int Sugar = rs.getInt("sugargram");
                    int Fiber = rs.getInt("fibergram");
                    String Expriation = rs.getString("expiredate");
                    float Price = rs.getFloat("price");
                    String Description = rs.getString("productdescription");
                    String Inventory = rs.getString("inventory");
                    result ="<pre>ID: " + DBid +"    Brand: " + Brand + "   Name: " + Name+ "   Sugar:  " + Sugar+"   Fiber: " + Fiber +"   Exprie Date:  " + Expriation + "   Price: " + Price + "   Description: " + Description + "   Inventory: " + Inventory + "</pre>";                   
                }   
            }
            return result;
        
        }catch(Exception ex)
        {
            System.out.println("Print:  " +ex);
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
