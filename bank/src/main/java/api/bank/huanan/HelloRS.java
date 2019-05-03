package api.bank.huanan;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

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
}
