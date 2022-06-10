//jdbc:sqlite:C:\Users\v-gnoori\OneDrive - Microsoft\Desktop\sqlite\holidays.db
import java.sql.*;
import java.util.Scanner;

public class UsHolidays {
    public static void main( String args[] ) {
        Connection c = null;
        //Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:usholidays.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            Boolean option = true;
            System.out.println("Enter date in this format (yyyy-mm-dd):");
            Scanner sc = new Scanner(System.in);
            String userInput = sc.nextLine();
            String[] arrStr = userInput.split("-");
            int y = Integer.parseInt(arrStr[0]);
            int m = Integer.parseInt(arrStr[1]);
            int d = Integer.parseInt(arrStr[2]);

            String sql="SELECT HOLIDAY, DESCRIPTION, TYPE FROM USHOLIDAYS WHERE YEAR == ? and MONTH == ? and DAY == ?";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, y);
            pstmt.setInt(2, m);
            pstmt.setInt(3, d);
            ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    //int id = rs.getInt("ID");
                    //int  month = rs.getInt("MONTH");
                    //int day  = rs.getInt("DAY");
                    //int  year = rs.getInt("YEAR");
                    String holiday = rs.getString("HOLIDAY");
                    String description = rs.getString("DESCRIPTION");
                    String type = rs.getString("TYPE");

                    //System.out.println( "ID = " + id );
                    //System.out.println( "MONTH = " + month );
                    //System.out.println( "DAY = " + day );
                    //System.out.println( "YEAR = " + year );
                    String output = ( "{\n\t\"HOLIDAY\":\"" +holiday + "\",\n" +  "\t\"DESCRIPTION\":\"" +description + "\",\n" + "\t\"TYPE\":\"" +type + "\"\n}");
                    System.out.println(output);
                    //System.out.println( "DESCRIPTION = " + description );
                    //System.out.println( "TYPE = " + type );
                    System.out.println();
                }
                else {
                    System.out.println("It is just a regular day");
                }
            pstmt.close();


            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
