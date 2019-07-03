package api.bank.huanan;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import api.modules.ErrorHandler;
import api.modules.LogHandler;
import api.modules.SqliteHandler;

/**
 * Created by Jugo on 2019-07-03
 */

@Path("/huanan/token")
@Produces("application/json;charset=utf8")
public class Tokenlist
{
    @GET
    @Path("/list")
    public String list()
    {
        
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
        
        try
        {
            SqliteHandler sqliteHandler = new SqliteHandler();
            Connection conn = sqliteHandler.getConnection("database/huanan.db");
            if (conn != null)
            {
                String sql = "select * from tokens";
                Statement stat = null;
                ResultSet rs = null;
                stat = conn.createStatement();
                rs = stat.executeQuery(sql);
                jsonArray = new JSONArray();
                
                
                if (rs.next())
                {
                    do
                    {
                        dataJson = new JSONObject();
                        dataJson.put("id", rs.getInt("id"));
                        dataJson.put("token", rs.getString("token"));
                        dataJson.put("user", rs.getString("user"));
                        dataJson.put("used", rs.getInt("used"));
                        
                        jsonArray.put(dataJson);
                        
                    } while (rs.next());
                }
                
                jsonObject.put("tokenList", jsonArray);
                
                
            }
            else
            {
                System.out.println("Database Connect Fail");
                jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_CONNECT_DB_CODE);
                jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_CONNECT_DB);
                return jsonObject.toString();
            }
            
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            
            jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_EXCEPTION);
            jsonObject.put("ERROR_MESSAGE", e.getMessage());
            
            return jsonObject.toString();
        }
        return jsonObject.toString();
    }
    
    @PATCH
    @Path("/list/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json;charset=utf8")
    public String modify(@PathParam("id") int id, String json)
    {
        
        JSONObject jsonObject, responseJson = null;
        JSONObject jsonRequest = new JSONObject(json);
        String user = null;
        int used = 0;
        
        if (!jsonRequest.isEmpty())
        {
            System.out.println("request:" + json);
            user = jsonRequest.getString("user");
            used = jsonRequest.getInt("used");
            
        }
        
        if (user != null)
        {
            try
            {
                SqliteHandler sqliteHandler = new SqliteHandler();
                Connection conn = sqliteHandler.getConnection("database/huanan.db");
                if (conn != null)
                {
                    String sql = "update tokens set 'user' = '" + user + "', used = " + used + " " +
                            "where id = " + id;
                    Statement stat = null;
                    ResultSet rs = null;
                    stat = conn.createStatement();
                    stat.executeUpdate(sql);
                    responseJson = new JSONObject();
                }
                else
                {
                    System.out.println("Database Connect Fail");
                    responseJson.put("ERROR_CODE", ErrorHandler.ERROR_CONNECT_DB_CODE);
                    responseJson.put("ERROR_MESSAGE", ErrorHandler.ERROR_CONNECT_DB);
                    return responseJson.toString();
                }
                
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                
                responseJson.put("ERROR_CODE", ErrorHandler.ERROR_EXCEPTION);
                responseJson.put("ERROR_MESSAGE", e.getMessage());
                
                return responseJson.toString();
            }
        }
        
        
        return "Update Success!";
    }
}
