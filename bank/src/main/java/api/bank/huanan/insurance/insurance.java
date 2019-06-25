package api.bank.huanan.insurance;


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
import api.modules.TypeHandler;

@Path("huanan/insurance")
public class insurance
{
    
    @GET
    @Path("/record")
    public String record(@QueryParam("user_id") int id, @QueryParam("type") String type,
            @QueryParam("api_key") String token, @Context HttpServletRequest request)
    {
        LogHandler.log(token, request);
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
        
        if (id != 0 && token != null && !token.equals(""))
        {
            if (type != null && !type.equals(""))
            {
                try
                {
                    
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    
                    if (conn != null)
                    {
                        String sql = "select * from insurance_record where user_id =" + id;
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
                                dataJson.put("policy_no", rs.getString("policy_no"));
                                dataJson.put("insurance_category", rs.getString(
                                        "insurance_category"));
                                dataJson.put("insurance_name", rs.getString("insurance_name"));
                                dataJson.put("insurance_premiums", rs.getInt("insurance_premiums"));
                                dataJson.put("insurance_date", rs.getString("insurance_date"));
                                dataJson.put("insurance_expiration_date", rs.getString(
                                        "insurance_expiration_date"));
                                
                                if (type == "1")
                                {
                                    dataJson.put("car_type", rs.getString("car_type"));
                                }
                                
                                jsonArray.put(dataJson);
                                
                                
                            } while (rs.next());
                            
                            jsonObject.put("user_id", id);
                            jsonObject.put("insurance_record", jsonArray);
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
                jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
                jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM + ", type is required");
                return jsonObject.toString();
            }
            
        }
        
        
        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM);
        return jsonObject.toString();
        
        
    }
    
    @GET
    @Path("/claim")
    public String claim(@QueryParam("policy_no") String policy_no,
            @QueryParam("api_key") String token, @Context HttpServletRequest request)
    {
        LogHandler.log(token, request);
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
        
        if (policy_no != null && !policy_no.equals("") && token != null && !token.equals(""))
        {
            
            try
            {
                
                SqliteHandler sqliteHandler = new SqliteHandler();
                Connection conn = sqliteHandler.getConnection("database/huanan.db");
                
                if (conn != null)
                {
                    String sql = "select * from claim_record where policy_no ='" + policy_no + "'";
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
                            dataJson.put("claim_no", rs.getString("claim_no"));
                            dataJson.put("claim_date", rs.getString("claim_date"));
                            dataJson.put("claim_amount", rs.getInt("claim_amount"));
                            dataJson.put("claim_descript", rs.getString("claim_descript"));
                            dataJson.put("create_date", rs.getString("create_date"));
                            
                            jsonArray.put(dataJson);
                            
                            
                        } while (rs.next());
                        
                        jsonObject.put("policy_no", policy_no);
                        jsonObject.put("claim_record", jsonArray);
                        return jsonObject.toString();
                        
                    }
                    else
                    {
                        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_NO_RECORD_CODE);
                        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_NO_RECORD);
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
    
    @GET
    @Path("/expiration")
    public String expiration(@QueryParam("identity_id") String identity_id,
            @QueryParam("api_key") String token, @Context HttpServletRequest request)
    {
        LogHandler.log(token, request);
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
        
        if (identity_id != null && !identity_id.equals("") && token != null && !token.equals(""))
        {
            
            try
            {
                
                SqliteHandler sqliteHandler = new SqliteHandler();
                Connection conn = sqliteHandler.getConnection("database/huanan.db");
                
                if (conn != null)
                {
                    String sql =
                            "select * from insurance_exp_date where identity_id ='" + identity_id + "'";
                    Statement stat = null;
                    ResultSet rs = null;
                    stat = conn.createStatement();
                    rs = stat.executeQuery(sql);
                    jsonArray = new JSONArray();
                    int active = 0;
                    
                    
                    if (rs.next())
                    {
                        do
                        {
                            dataJson = new JSONObject();
                            dataJson.put("id", rs.getString("id"));
                            dataJson.put("policy_no", rs.getString("policy_no"));
                            dataJson.put("exp_date", rs.getString("exp_date"));
                            dataJson.put("is_active", rs.getInt("is_active"));
                            
                            jsonArray.put(dataJson);
                            
                            if (rs.getInt("is_active") == 1)
                            {
                                active = 1;
                            }
                            
                            
                        } while (rs.next());
                        
                        jsonObject.put("identity_id", identity_id);
                        jsonObject.put("is_active_user", active);
                        jsonObject.put("exp_record", jsonArray);
                        return jsonObject.toString();
                        
                    }
                    else
                    {
                        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_NO_RECORD_CODE);
                        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_NO_RECORD);
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
    
    @GET
    @Path("/blacklist")
    public String blacklist(@QueryParam("number") String number, @QueryParam("type") String type,
            @QueryParam("api_key") String token, @Context HttpServletRequest request)
    {
        LogHandler.log(token, request);
        JSONObject jsonObject;
        jsonObject = new JSONObject();
        String para = "";
        if (number != null && !number.equals("") && token != null && !token.equals(""))
        {
            if (type != null && !type.equals(""))
            {
                try
                {
                    
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    para = TypeHandler.TypeHandler(number, type);
                    
                    if (conn != null)
                    {
                        if (para != null)
                        {
                            String Sql = String.format("select * from blacklist where %s = '%s'",
                                    para, number);
                            System.out.println(Sql);
                            Statement stat = null;
                            ResultSet rs = null;
                            stat = conn.createStatement();
                            rs = stat.executeQuery(Sql);
                            
                            if (rs.next())
                            {
                                do
                                {
                                    jsonObject.put("id", rs.getString("id"));
                                    jsonObject.put(para, number);
                                    jsonObject.put("is_blacklist", 1);
                                    
                                } while (rs.next());
                                
                                
                                return jsonObject.toString();
                                
                            }
                            else
                            {
                                jsonObject.put(para, number);
                                jsonObject.put("is_blacklist", 0);
                                return jsonObject.toString();
                            }
                        }
                        else
                        {
                            jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_TYPE_CODE);
                            jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_TYPE);
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
                jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
                jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM + ", type is required");
                return jsonObject.toString();
            }
            
        }
        
        
        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM);
        return jsonObject.toString();
        
        
    }
    
    @GET
    @Path("/payments")
    public String payments(@QueryParam("user_id") int id, @QueryParam("api_key") String token,
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
                    String sql = "select * from payment_method where user_id =" + id;
                    Statement stat = null;
                    ResultSet rs = null;
                    stat = conn.createStatement();
                    rs = stat.executeQuery(sql);
                    jsonArray = new JSONArray();
                    int active = 0;
                    
                    
                    if (rs.next())
                    {
                        do
                        {
                            dataJson = new JSONObject();
                            dataJson.put("id", rs.getString("id"));
                            dataJson.put("policy_no", rs.getString("policy_no"));
                            dataJson.put("pay_status", rs.getInt("pay_status"));
                            dataJson.put("pay_date", rs.getString("pay_date"));
                            dataJson.put("pay_category", rs.getString("pay_category"));
                            dataJson.put("payments_period", rs.getString("payments_period"));
                            dataJson.put("create_date", rs.getString("create_date"));
                            
                            jsonArray.put(dataJson);
                            
                        } while (rs.next());
                        
                        jsonObject.put("user_id", id);
                        jsonObject.put("payments_record", jsonArray);
                        return jsonObject.toString();
                        
                    }
                    else
                    {
                        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_NO_RECORD_CODE);
                        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_NO_RECORD);
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
    
    @GET
    @Path("/reports")
    public String reports(@QueryParam("number") String number, @QueryParam("type") String type,
            @QueryParam("api_key") String token, @Context HttpServletRequest request)
    {
        LogHandler.log(token, request);
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();
        String para = "";
        
        if (number != null && !number.equals("") && token != null && !token.equals(""))
        {
            if (type != null && !type.equals(""))
            {
                try
                {
                    
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    para = TypeHandler.TypeHandler(number, type);
                    if (conn != null)
                    {
                        if (para != null)
                        {
                            String Sql = String.format("select * from online_report where %s = " +
                                    "'%s'", para, number);
                            Statement stat = null;
                            ResultSet rs = null;
                            stat = conn.createStatement();
                            rs = stat.executeQuery(Sql);
                            jsonArray = new JSONArray();
                            
                            
                            if (rs.next())
                            {
                                do
                                {
                                    dataJson = new JSONObject();
                                    dataJson.put("id", rs.getString("id"));
                                    dataJson.put("policy_no", rs.getString("policy_no"));
                                    dataJson.put("claim_date", rs.getString("claim_date"));
                                    dataJson.put("create_date", rs.getString("create_date"));
                                    
                                    
                                    jsonArray.put(dataJson);
                                    
                                    
                                } while (rs.next());
                                
                                jsonObject.put(para, number);
                                jsonObject.put("records", jsonArray);
                                
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
                            jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_TYPE_CODE);
                            jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_TYPE);
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
                jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
                jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM + ", type is required");
                return jsonObject.toString();
            }
            
        }
        
        
        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM);
        return jsonObject.toString();
        
        
    }
    
}
