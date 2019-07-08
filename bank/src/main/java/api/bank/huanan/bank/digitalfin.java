package api.bank.huanan.bank;

import api.modules.ErrorHandler;
import api.modules.LogHandler;
import api.modules.SqliteHandler;
import api.modules.TokenHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


@Path("/huanan/digitalfin")
public class digitalfin
{
    
    @GET
    @Produces("application/json;charset=utf8")
    @Path("/account_records")
//    @Consumes(MediaType.APPLICATION_JSON)
    public String accounts(@QueryParam("user_id") int id, @QueryParam("api_key") String token,
            @Context HttpServletRequest request)
    {
        LogHandler.log(token, request);
        String strResponse = "{\"message\":\"parameter error!!\"}";
        String strRecord;
//        int id = 0;
//        String token = null;
//        JSONObject jsonRequest = new JSONObject(json);
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
        boolean t = TokenHandler.TokenHandler(token);


//        if(!jsonRequest.isEmpty()){
//            System.out.println("request:" + json);
//            id = jsonRequest.getInt("account_id");
//            token = jsonRequest.getString("token");
//        }
        
        if (id != 0 && token != null && !token.equals(""))
        {
            
            if (t)
            {
                try
                {
                    
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    
                    if (conn != null)
                    {
                        
                        String sql = "select * from trans_record where user_id = " + id;
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
                                dataJson.put("id", rs.getInt("trans_id"));
                                dataJson.put("account_num", rs.getString("account_num"));
                                dataJson.put("trans_type", rs.getString("trans_type"));
                                dataJson.put("trans_channel", rs.getString("trans_channel"));
                                dataJson.put("trans_date", rs.getString("trans_date"));
                                dataJson.put("amount", rs.getInt("amount"));
                                dataJson.put("balance", rs.getInt("balance"));


//                        int pay = rs.getInt("trans_pay");
//                        int deposit = rs.getInt("trans_deposit");
//
//                        payTotal = payTotal+pay;
//                        depositTotal = depositTotal+deposit;
//
                                jsonArray.put(dataJson);
                                
                            } while (rs.next());
                            
                            jsonObject.put("user_id", id);
                            jsonObject.put("trans_record", jsonArray);
                            return jsonObject.toString();
                        }
                        else
                        {
                            jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_NO_ACCOUNT_CODE);
                            jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_NO_ACCOUNT);
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
            else
            {
                jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_TOKEN_CODE);
                jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_TOKEN);
                return jsonObject.toString();
            }


//            return "account id is " + id + " token is " + token;
        }
        
        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM);
        return jsonObject.toString();
    }
    
    @GET
    @Produces("application/json;charset=utf8")
    @Path("/customers")
    public String customers(@QueryParam("user_id") int id, @QueryParam("api_key") String token,
            @Context HttpServletRequest request)
    {
        LogHandler.log(token, request);
        String strResponse = "{\"message\":\"parameter error!!\"}";
        String strRecord;
        
        JSONObject jsonObject;
        jsonObject = new JSONObject();
        boolean t = TokenHandler.TokenHandler(token);
        
        if (id != 0 && token != null && !token.equals(""))
        {
            
            if (t == true)
            {
                try
                {
                    
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    
                    if (conn != null)
                    {
                        
                        String sql = "select * from bank_account where id = " + id;
                        Statement stat = null;
                        ResultSet rs = null;
                        stat = conn.createStatement();
                        rs = stat.executeQuery(sql);
                        
                        if (rs.next())
                        {
                            
                            jsonObject.put("id", rs.getInt("id"));
                            jsonObject.put("birthday", rs.getString("birthday"));
                            jsonObject.put("gender", rs.getInt("gender"));
                            jsonObject.put("career", rs.getString("career"));
                            jsonObject.put("residence", rs.getString("residence"));
                            jsonObject.put("income", rs.getInt("income"));
                            jsonObject.put("service_units", rs.getString("service_units"));
                            jsonObject.put("marital", rs.getString("marital"));
                            jsonObject.put("education", rs.getString("education"));
                            jsonObject.put("dependents", rs.getInt("dependents"));
                            jsonObject.put("is_SNY", rs.getInt("is_SNY"));
                            jsonObject.put("is_register_web_bank", rs.getInt(
                                    "is_register_web_bank"));
                            jsonObject.put("is_app_bank", rs.getInt("is_app_bank"));
                            jsonObject.put("is_register_mobile_pay", rs.getInt(
                                    "is_register_mobile_pay"));
                            jsonObject.put("create_date", rs.getString("create_date"));
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
//
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
            else
            {
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
