package api.bank.huanan;

import org.json.simple.JSONObject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import api.modules.SqliteHandler;


@Path("/huanan/hello")
public class HelloRS
{
    @GET
    public String sayHelloWorld()
    {
        return "Hello world";
    }
    
    @GET
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name)
    {
        if (0 == name.compareTo("sqlite"))
        {
            try
            {
                SqliteHandler sqliteHandler = new SqliteHandler();
                Connection conn = sqliteHandler.getConnection("database/huanan.db");
                if (conn != null)
                {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            
        }
        return "Hello, " + name;
    }
    
    @POST
    @Produces("text/html") // content type to output
    @Path("/account")
    public String bank_account(@QueryParam("id") String id, @QueryParam("token") String token,
            @Context HttpServletRequest request)
    {
        String strResponse = "{\"message\":\"parameter error!!\"}";
        String strRecord;
        JSONObject jsonObject;
        
        if (id != null && !id.equals("") && token != null && !token.equals(""))
        {
            try
            {
                SqliteHandler sqliteHandler = new SqliteHandler();
                Connection conn = sqliteHandler.getConnection("database/huanan.db");
                if (conn != null)
                {
                    String sql = "select * from bank_account where id = '" + id + "'";
                    Statement stat = null;
                    ResultSet rs = null;
                    stat = conn.createStatement();
                    rs = stat.executeQuery(sql);
                    jsonObject = new JSONObject();
                    if (rs.next())
                    {
                        jsonObject.put("id", rs.getInt("id"));
                        jsonObject.put("balance", rs.getInt("balance"));
                        jsonObject.put("birthday", rs.getString("birthday"));
                        jsonObject.put("career", rs.getString("career"));
                        jsonObject.put("residence", rs.getString("residence"));
                        jsonObject.put("income", rs.getInt("income"));
                        jsonObject.put("service_units", rs.getString("service_units"));
                        jsonObject.put("marital", rs.getString("marital"));
                        jsonObject.put("education", rs.getString("education"));
                        jsonObject.put("dependents", rs.getInt("dependents"));
                        jsonObject.put("balance_update_date", rs.getString("balance_update_date"));
                        jsonObject.put("create_date", rs.getString("create_date"));
                        return jsonObject.toString();
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
            return "account id is " + id + " token is " + token;
        }
        
        return strResponse;
    }
    
    //=========================   Table Information ============================================//
    
    /*TABLE "bank_account" (
	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"balance"	INTEGER NOT NULL DEFAULT 0,
	"birthday"	TEXT NOT NULL,
	"career"	TEXT NOT NULL,
	"residence"	TEXT,
	"income"	INTEGER,
	"service_units"	TEXT,
	"marital"	TEXT,
	"education"	TEXT,
	"dependents"	INTEGER,
	"balance_update_date"	TEXT,
	"create_date"	TEXT DEFAULT CURRENT_TIMESTAMP
)*/
    
    @POST
    @Produces("text/html") // content type to output
    @Path("/trans_record")
    public String bank_account(@QueryParam("id") String id, @QueryParam("token") String token,
            @Context HttpServletRequest request)
    {
        String strResponse = "{\"message\":\"parameter error!!\"}";
        String strRecord;
        JSONObject jsonObject;
    }
}
