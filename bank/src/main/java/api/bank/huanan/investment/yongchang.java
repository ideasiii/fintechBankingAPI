package api.bank.huanan.investment;


import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import api.modules.ErrorHandler;
import api.modules.LogHandler;
import api.modules.SqliteHandler;
import api.modules.TokenHandler;
import api.modules.TransUUID;

@Path("huanan/yongchang/investing")
public class yongchang
{
    @GET
    @Path("/fund/information")
    public String fund_info(@QueryParam("uuid") String uuid, @QueryParam("api_key") String token,
            @Context HttpServletRequest request)
    {
        
        LogHandler.log(token, request);
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
        boolean t = TokenHandler.TokenHandler(token);
        
        if (uuid != null && !uuid.equals("") && token != null && !token.equals(""))
        {
            int id = TransUUID.UUIDHandler(uuid);
            if (t)
            {
                try
                {
                    
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    
                    if (conn != null)
                    {
                        String sql = "select * from fund_information where user_id =" + id;
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
                                dataJson.put("fund_code", rs.getString("fund_code"));
                                dataJson.put("fund_name", rs.getString("fund_name"));
                                dataJson.put("price_currency", rs.getString("price_currency"));
                                dataJson.put("dividend_category", rs.getString("dividend_category"
                                ));
                                dataJson.put("create_date", rs.getString("create_date"));
                                
                                jsonArray.put(dataJson);
                                
                                
                            } while (rs.next());
                            
                            jsonObject.put("uuid", uuid);
                            jsonObject.put("fund_information", jsonArray);
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
    
    @GET
    @Path("/fund/net")
    public String net(@QueryParam("uuid") String uuid, @QueryParam("api_key") String token,
            @Context HttpServletRequest request)
    {
        
        LogHandler.log(token, request);
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
        boolean t = TokenHandler.TokenHandler(token);
        
        if (uuid != null && !uuid.equals("") && token != null && !token.equals(""))
        {
            int id = TransUUID.UUIDHandler(uuid);
            if (t)
            {
                try
                {
                    
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    
                    if (conn != null)
                    {
                        String sql = "select * from fund_information where user_id =" + id;
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
                                dataJson.put("fund_code", rs.getString("fund_code"));
                                dataJson.put("net_datetime", rs.getString("net_datetime"));
                                dataJson.put("net", rs.getFloat("net"));
                                
                                
                                jsonArray.put(dataJson);
                                
                                
                            } while (rs.next());
                            
                            jsonObject.put("uuid", uuid);
                            jsonObject.put("net_inf_records", jsonArray);
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
    
    @GET
    @Path("/beneficiary/information")
    public String benef_info(@QueryParam("uuid") String uuid,
            @QueryParam("api_key") String token,
            @Context HttpServletRequest request)
    {
        
        LogHandler.log(token, request);
        JSONObject jsonObject;
        jsonObject = new JSONObject();
        boolean t = TokenHandler.TokenHandler(token);
        
        if (uuid != null && !uuid.equals("") && token != null && !token.equals(""))
        {
            int id = TransUUID.UUIDHandler(uuid);
            if (t)
            {
                try
                {
                    
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    
                    if (conn != null)
                    {
                        String sql = "select * from beneficiary where user_id =" + id;
                        Statement stat = null;
                        ResultSet rs = null;
                        stat = conn.createStatement();
                        rs = stat.executeQuery(sql);
                        
                        
                        if (rs.next())
                        {
                            do
                            {
                                
                                jsonObject.put("id", rs.getString("id"));
                                jsonObject.put("benefit_id", rs.getString("benefit_id"));
                                jsonObject.put("user_name", rs.getString("user_name"));
                                jsonObject.put("birth", rs.getString("birth"));
                                jsonObject.put("risk", rs.getInt("risk"));
                                jsonObject.put("risk_exp_date", rs.getString("risk_exp_date"));
                                
                            } while (rs.next());
                            
                            
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
    
    @GET
    @Path("/beneficiary/account")
    public String benef_account(@QueryParam("uuid") String uuid,
            @QueryParam("api_key") String token, @Context HttpServletRequest request)
    {
        
        LogHandler.log(token, request);
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
        boolean t = TokenHandler.TokenHandler(token);
        
        if (uuid != null && !uuid.equals("") && token != null && !token.equals(""))
        {
            int id = TransUUID.UUIDHandler(uuid);
            if (t)
            {
                try
                {
                    
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    
                    if (conn != null)
                    {
                        String sql = "select * from fund_account where user_id =" + id;
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
                                dataJson.put("account_category", rs.getString("account_category"));
                                dataJson.put("capital", rs.getInt("capital"));
                                dataJson.put("bank_code", rs.getString("bank_code"));
                                dataJson.put("account_number", rs.getString("account_number"));
                                dataJson.put("create_date", rs.getString("create_date"));
                                
                                
                                jsonArray.put(dataJson);
                                
                                
                            } while (rs.next());
                            
                            jsonObject.put("uuid", uuid);
                            jsonObject.put("account_records", jsonArray);
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
    
    @GET
    @Path("/beneficiary/inventory")
    public String benef_inventory(@QueryParam("uuid") String uuid,
            @QueryParam("api_key") String token, @Context HttpServletRequest request)
    {
        
        LogHandler.log(token, request);
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
        boolean t = TokenHandler.TokenHandler(token);
        
        if (uuid != null && !uuid.equals("") && token != null && !token.equals(""))
        {
            int id = TransUUID.UUIDHandler(uuid);
            if (t)
            {
                try
                {
                    
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    
                    if (conn != null)
                    {
                        String sql = "select * from fund_inventory where user_id =" + id;
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
                                dataJson.put("fund_code", rs.getString("fund_code"));
                                dataJson.put("fund_name", rs.getString("fund_name"));
                                dataJson.put("price_currency", rs.getString("price_currency"));
                                dataJson.put("inventory_unit", rs.getInt("inventory_unit"));
                                dataJson.put("investment_cost", rs.getInt("investment_cost"));
                                
                                
                                jsonArray.put(dataJson);
                                
                                
                            } while (rs.next());
                            
                            jsonObject.put("uuid", uuid);
                            jsonObject.put("inventory_records", jsonArray);
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
