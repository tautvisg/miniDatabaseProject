import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.DocPrintJob;

public class TaguApp {

    /********************************************************/
    public static void loadDriver()
    {
       try {
         Class.forName("org.postgresql.Driver");
       }
       catch (ClassNotFoundException cnfe) {
         System.out.println("Couldn't find driver class!");
         cnfe.printStackTrace();
         System.exit(1);
       }
    }
    /********************************************************/
    public static Connection getConnection() {
       Connection postGresConn = null;
       try {
         /*  postGresConn = DriverManager.getConnection("") ; */
       }
       catch (SQLException sqle) {
         System.out.println("Couldn't connect to database!");
         sqle.printStackTrace();
         return null ;
       }
       System.out.println("Successfully connected to Postgres Database");

       return postGresConn ;
    }
    /********************************************************/
    public static int countProducts(Connection connection)
    {
      int nofProducts = -1 ;

      if(connection == null){
        System.out.println("We should never get here.");
        return nofProducts;
      }

      Statement stmt = null;
      ResultSet rs = null;

      try{

        stmt = connection.createStatement();
        rs = stmt.executeQuery("SELECT COUNT(*) from tagu8226.produktas");
        rs.next();
        nofProducts = rs.getInt(1);
      } catch (SQLException e) {
        System.out.println("SQL Error!");
        e.printStackTrace();
      } finally {
        try {
          if (null != rs)
            rs.close();
          if (null != stmt)
            stmt.close();
        } catch (SQLException exp) {
          System.out.println("SQL Error!");
          exp.printStackTrace();
        }
      }

      return nofProducts;
    }
    public static boolean productExistsBetter(String ProductName, Connection connection){

      int nofProducts = -1;

      if(connection == null){
        System.out.println("Unexpected error");
      }

      PreparedStatement stmt = null;
      ResultSet rs = null;

      try{

          stmt = connection.prepareStatement("SELECT COUNT(*) from tagu8226.produktas WHERE pavadinimas = ?");
          stmt.setString(1, ProductName);
          rs = stmt.executeQuery();
          rs.next();
          nofProducts = rs.getInt(1);

        } catch(SQLException e){
        System.out.println("SQL Error!");
        e.printStackTrace();
      } finally{
        try{
          if(null != rs)
            rs.close();
          if(null != stmt)
            stmt.close();
        } catch (SQLException exp){
          System.out.println("Unexpected error!");
          exp.printStackTrace();
        }
      }

      return nofProducts > 0;
    }
    // -- CRUD --

    // -- SELECT --------------

    public static void printProducts(Connection connection){

      if(connection == null){
        System.out.println("Unexpected error");
      }

        System.out.println("+---------+--------+-----------------------------+------------------------------------------+-------+");
        System.out.println("| PROD_ID | GAM_ID |         Pavadinimas         |                Aprasymas                 | Kaina |");
        System.out.println("+---------+--------+-----------------------------+------------------------------------------+-------+");

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = connection.prepareStatement("SELECT * FROM tagu8226.produktas");
            rs = stmt.executeQuery();
            while(rs.next()){
              int prod_id = rs.getInt(1);
              int gam_id = rs.getInt(2);
              String pavadinimas = rs.getString(3);
              String aprasymas = rs.getString(4);
              double kaina = rs.getDouble(5);
              
              System.out.format("| %5d   | %5d  | %25s | %40s | %7.2f |\n", prod_id, gam_id, pavadinimas, aprasymas, kaina);
            }
        } catch(SQLException e) {
          System.out.println("SQL Error!");
          e.printStackTrace();
        } finally {
          try{
            if(null != rs)
              rs.close();
            if(null != stmt)
              stmt.close();
          } catch (SQLException exp){
            System.out.println("Unexpected SQL error!");
            exp.printStackTrace();
          }
        }
        System.out.println("+---------+--------+-----------------------------+------------------------------------------+-------+");

    }
    public static void printFactories(Connection connection){

      if(connection == null){
        System.out.println("Unexpected error");
      }

        System.out.println("+---------+-----------------+-----------------------------------------+");
        System.out.println("| GAM_ID  |   Adresas       |             Pavadinimas                 |");
        System.out.println("+---------+-----------------+-----------------------------------------+");

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = connection.prepareStatement("SELECT * FROM tagu8226.Gamyklos");
            rs = stmt.executeQuery();
            while(rs.next()){
              int gam_id = rs.getInt(1);
              String adresas = rs.getString(2);
              String pavadinimas = rs.getString(3);
              
              System.out.format("| %5d   | %14s | %40s |\n", gam_id, adresas, pavadinimas);
            }
        } catch(SQLException e) {
          System.out.println("SQL Error!");
          e.printStackTrace();
        } finally {
          try{
            if(null != rs)
              rs.close();
            if(null != stmt)
              stmt.close();
          } catch (SQLException exp){
            System.out.println("Unexpected SQL error!");
            exp.printStackTrace();
          }
        }
        System.out.println("+---------+-----------------+-----------------------------------------+");
    }

    // -- DELETE ----------------

    public static void deleteProduct(int prod_id, Connection connection){

      if(connection == null){
        System.out.println("Unexpected error");
      }

      PreparedStatement stmt = null;
      ResultSet rs = null;

      try{
        stmt = connection.prepareStatement("DELETE FROM tagu8226.produktas WHERE prod_id = ?");
        stmt.setInt(1, prod_id);
        stmt.executeUpdate();
      } catch(SQLException e){
        System.out.println("SQL Error!");
        e.printStackTrace();
      } finally {
        try{
          if(null != rs)
            rs.close();
          if(null != stmt)
            stmt.close();
        } catch (SQLException exp){
          System.out.println("Unexpected SQL error!");
          exp.printStackTrace();
        }
      }
    }

    public static void deleteFactory(int gam_id, Connection connection){

      if(connection == null){
        System.out.println("Unexpected error");
      }

      PreparedStatement stmt = null;
      ResultSet rs = null;

      try{
        stmt = connection.prepareStatement("DELETE FROM tagu8226.Gamyklos WHERE gam_id = ?");
        stmt.setInt(1, gam_id);
        stmt.executeUpdate();
      } catch(SQLException e){
        System.out.println("SQL Error!");
        e.printStackTrace();
      } finally {
        try{
          if(null != rs)
            rs.close();
          if(null != stmt)
            stmt.close();
        } catch (SQLException exp){
          System.out.println("Unexpected SQL error!");
          exp.printStackTrace();
        }
      }
    }
    // --- UPDATE--------------
    public static void setProductPrice(int prod_id, double kaina, Connection connection){

      if(connection == null){
        System.out.println("Unexpected error");
      }

      PreparedStatement stmt = null;
      ResultSet rs = null;

      try{
        stmt = connection.prepareStatement("UPDATE tagu8226.produktas SET kaina = ? WHERE prod_id = ?");
        stmt.setDouble(1, kaina);
        stmt.setInt(2, prod_id);
        stmt.executeUpdate();
      } catch(SQLException e){
        System.out.println("SQL Error!");
        e.printStackTrace();
      } finally {
        try{
          if(null != rs)
            rs.close();
          if(null != stmt)
            stmt.close();
        } catch (SQLException exp){
          System.out.println("Unexpected SQL error!");
          exp.printStackTrace();
        }
      }
    }
    public static void changeFactoryTitle(int gam_id, String pavadinimas, Connection connection){

      if(connection == null){
        System.out.println("Unexpected error");
      }

      PreparedStatement stmt = null;
      ResultSet rs = null;

      try{
        stmt = connection.prepareStatement("UPDATE tagu8226.Gamyklos SET pavadinimas = ? WHERE gam_id = ?");
        stmt.setString(1, pavadinimas);
        stmt.setInt(2, gam_id);
        stmt.executeUpdate();
      } catch(SQLException e){
        System.out.println("SQL Error!");
        e.printStackTrace();
      } finally {
        try{
          if(null != rs)
            rs.close();
          if(null != stmt)
            stmt.close();
        } catch (SQLException exp){
          System.out.println("Unexpected SQL error!");
          exp.printStackTrace();
        }
      }
    }
    // --INSERT------------
    public static void addNewProduct(int prod_id, int gam_id, String pavadinimas, String aprasymas, double kaina, Connection connection){

      if(connection == null){
        System.out.println("Unexpected error");
      }

      PreparedStatement stmt = null;
      ResultSet rs = null;

      try{
        stmt = connection.prepareStatement("INSERT INTO tagu8226.produktas VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, prod_id);
        stmt.setInt(2, gam_id);
        stmt.setString(3, pavadinimas);
        stmt.setString(4, aprasymas);
        stmt.setDouble(5, kaina);
        stmt.executeUpdate();
      } catch(SQLException e){
        System.out.println("SQL Error!");
        e.printStackTrace();
      } finally {
        try{
          if(null != rs)
            rs.close();
          if(null != stmt)
            stmt.close();
        } catch (SQLException exp){
          System.out.println("Unexpected SQL error!");
          exp.printStackTrace();
        }
      }
    }
    public static void addNewFactory(int gam_id, String adresas, String pavadinimas, Connection connection){

      if(connection == null){
        System.out.println("Unexpected error");
      }

      PreparedStatement stmt = null;
      ResultSet rs = null;

      try{
        stmt = connection.prepareStatement("INSERT INTO tagu8226.Gamyklos VALUES (?, ?, ?)");
        stmt.setInt(1, gam_id);
        stmt.setString(2, adresas);
        stmt.setString(3, pavadinimas);
        stmt.executeUpdate();
      } catch(SQLException e){
        System.out.println("SQL Error!");
        e.printStackTrace();
      } finally {
        try{
          if(null != rs)
            rs.close();
          if(null != stmt)
            stmt.close();
        } catch (SQLException exp){
          System.out.println("Unexpected SQL error!");
          exp.printStackTrace();
        }
      }
    }
    /********************************************************/

    public static void addNewProductAndFactoy(int prod_id, int gam_id, String pavadinimas, String aprasymas, double kaina, String adresas, String gamPavadinimas, Connection connection)
    {
      if(connection == null){
        System.out.println("Unexpected error");
      }

      PreparedStatement newProductStmt = null;
      PreparedStatement newFactoryStmt = null;

      try{

        connection.setAutoCommit(false);

        newFactoryStmt = connection.prepareStatement("INSERT INTO tagu8226.Gamyklos VALUES (?, ?, ?)");  
        newFactoryStmt.setInt(1, gam_id);
        newFactoryStmt.setString(2, adresas);
        newFactoryStmt.setString(3, gamPavadinimas);
        newFactoryStmt.executeUpdate();

        newProductStmt = connection.prepareStatement("INSERT INTO tagu8226.Produktas VALUES (?, ?, ?, ?, ?)");
        newProductStmt.setInt(1, prod_id);
        newProductStmt.setInt(2, gam_id);
        newProductStmt.setString(3, pavadinimas);
        newProductStmt.setString(4, aprasymas);
        newProductStmt.setDouble(5, kaina);
        newProductStmt.executeUpdate();

        connection.commit();
      } catch (SQLException e) {

        System.out.println("SQL Error! A");
        e.printStackTrace();

        if (connection != null) {
          try {
            System.out.println("Transaction is being rolled back");
            connection.rollback();
          } catch(SQLException exp) {
            System.out.println("Unexpected SQL error!");
            exp.printStackTrace();
          }
        }
      } finally {
        try{
          if(null != newProductStmt)
            newProductStmt.close();
          if(null != newFactoryStmt)
            newFactoryStmt.close();
          connection.setAutoCommit(true);
        } catch (SQLException exp){
          System.out.println("Unexpected SQL error!");
          exp.printStackTrace();
        }
      }

    }
    public static void main(String[] args)
    {
      loadDriver() ;
     
      Connection connection = getConnection() ;
      if( null != connection ) {
        
        //int nofProducts = countProducts(connection);
        
        
        //addNewProduct(71, 1000, "JAVA kompiuteris", "Puikiai veikiantis kompiuteris", 750.00, connection);
        //setProductPrice(71, 700.00, connection);
        //printProducts(connection);
        //deleteProduct(71, connection);
        //printProducts(connection);

        //addNewProductAndFactoy(88, 900, "TEST kompiuteris", "Tikrai veikia", 2500.00, "Ausros vartu g.", "TEST kompiuterija", connection);
        //printProducts(connection);
        //deleteProduct(88, connection);
        //printProducts(connection);
        
             
      }
      if( null != connection ) {
        try {
          connection.close() ;
        }
        catch (SQLException exp) {
          System.out.println("Can not close connection!");
          exp.printStackTrace();
        }
      }
    }
    /********************************************************/
}

