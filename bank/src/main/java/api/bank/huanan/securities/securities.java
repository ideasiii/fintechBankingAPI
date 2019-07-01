package api.bank.huanan.securities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import api.modules.ErrorHandler;
import api.modules.LogHandler;
import api.modules.SqliteHandler;

@Path("huanan/securities")
public class securities
{
    @GET
    @Path("/history")
    public String history(@QueryParam("user_id") int id, @QueryParam("api_key") String token,
            @Context HttpServletRequest request)
    {
        
        LogHandler.log(token, request);
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
        
        
        if (id != 0 && token != null && !token.equals(""))
        {
            
            
            try
            {
                
                SqliteHandler sqliteHandler = new SqliteHandler();
                Connection conn = sqliteHandler.getConnection("database/huanan.db");
                
                if (conn != null)
                {
                    String sql = "select * from stock_history where user_id =" + id;
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
                            dataJson.put("id", rs.getString("id"));
                            dataJson.put("stock_code", rs.getString("stock_code"));
                            dataJson.put("completion_date", rs.getString("completion_date"));
                            dataJson.put("num_of_shares", rs.getInt("num_of_shares"));
                            dataJson.put("channel_category", rs.getString("channel_category"));
                            dataJson.put("trans_category", rs.getString("trans_category"));
                            dataJson.put("buy_category", rs.getString("buy_category"));
                            dataJson.put("strike_price", rs.getFloat("strike_price"));
                            dataJson.put("trans_price", rs.getInt("trans_price"));
                            
                            jsonArray.put(dataJson);
                            
                            
                        } while (rs.next());
                        
                        jsonObject.put("user_id", id);
                        jsonObject.put("stock_history", jsonArray);
                        return jsonObject.toString();
                        
                    }
                    else
                    {
                        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_NO_USER_CODE);
                        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_NO_USER);
                        return jsonObject.toString();
                    }
                    
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
            
            
        }
        
        
        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM);
        return jsonObject.toString();
        
    }
}
