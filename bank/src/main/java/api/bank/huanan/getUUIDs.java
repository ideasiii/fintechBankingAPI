package api.bank.huanan;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import api.modules.ErrorHandler;
import api.modules.LogHandler;
import api.modules.SqliteHandler;
import api.modules.TokenHandler;

/**
 * Created by Jugo on 2019-07-12
 */

@Path("/huanan/getUUIDs")
@Produces("application/json;charset=utf8")
public class getUUIDs
{
    @GET
    public String list(@QueryParam("api_key") String token, @Context HttpServletRequest request)
    {
        LogHandler.log(token, request);
        boolean tokenChecker = TokenHandler.TokenHandler(token);
        JSONObject jsonObject;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
    
        if (token != null && !token.equals("")){
            if (tokenChecker){
                try
                {
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    if(conn != null){
                        String sql = "select * from bank_account order by random() limit 10";
                        Statement stat;
                        ResultSet rs;
                        stat = conn.createStatement();
                        rs = stat.executeQuery(sql);
                        jsonArray = new JSONArray();
    
                        if (rs.next())
                        {
                            do
                            {
                                jsonArray.put(rs.getString("uuid"));
            
                            } while (rs.next());
                            
                            jsonObject.put("uuid_list", jsonArray);
                            return jsonObject.toString();
                        }
                    } else {
                        System.out.println("Database Connect Fail");
                        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_CONNECT_DB_CODE);
                        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_CONNECT_DB);
                        return jsonObject.toString();
                    }
                    
                }catch (Exception e){
                    System.out.println(e.getMessage());
    
                    jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_EXCEPTION);
                    jsonObject.put("ERROR_MESSAGE", e.getMessage());
    
                    return jsonObject.toString();
                }
            } else {
                jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_TOKEN_CODE);
                jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_TOKEN);
                return jsonObject.toString();
            }
            
        }
        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM);
        return jsonObject.toString();
    }
}
