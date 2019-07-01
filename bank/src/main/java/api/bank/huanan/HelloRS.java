package api.bank.huanan;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import api.modules.LogHandler;
import api.modules.Logs;
import api.modules.SqliteHandler;


@Path("/huanan/hello")
@Produces("application/json;charset=utf8")
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
    
    /**
     * For API Gateway test POST method
     *
     * @param id
     * @param token
     * @param request
     * @return
     */
    @POST
    @Path("/apiPOST")
    @Produces("application/json;charset=utf8")
    public String apiPOST(@FormParam("id") String id, @FormParam("token") String token,
            @Context HttpServletRequest request)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("token", token);
        Logs.showTrace(jsonObject.toString());
        return jsonObject.toString();
    }
    
    /**
     * http://127.0.0.1:8080/bank/huanan/hello/token/generate?count=100
     * @param nCount 產生的筆數
     * @return 筆數完成訊息
     */
    @GET
    @Path("/token/generate")
    public String tokenGenerate(@QueryParam("count") int nCount)
    {
        String strResult;
        SqliteHandler sqliteHandler = new SqliteHandler();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        String strSQL;
        int insCount;
        String strToken = null;
        
        try
        {
            conn = sqliteHandler.getConnection("database/huanan.db");
            if (conn != null)
            {
                stat = conn.createStatement();
                insCount = 0;
                while (nCount >= ++insCount)
                {
                    strSQL = String.format("INSERT INTO tokens(token) VALUES('%s')",
                            UUID.randomUUID().toString());
                    stat.executeUpdate(strSQL);
                }
                stat.close();
                conn.close();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        
        strResult = String.format("%d Generate Finish", nCount);
        return strResult;
    }
    
    /**
     * For API Gateway test GET method
     *
     * @param id
     * @param token
     * @param request
     * @return
     */
    @GET
    @Path("/apiGET")
    public String apiGET(@QueryParam("id") String id, @QueryParam("token") String token,
            @Context HttpServletRequest request)
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("token", token);
        Logs.showTrace(jsonObject.toString());
        return jsonObject.toString();
    }
    
    /**
     * Test: http://127.0.0.1:8080/bank/huanan/hello/account?id=1&token=1
     *
     * @param id
     * @param token
     * @param request
     * @return
     */
    @POST
    @Produces("application/json;charset=utf8")
    @Path("/account")
    public String bank_account(@QueryParam("id") String id, @QueryParam("token") String token,
            @Context HttpServletRequest request)
    {
        LogHandler.log(token, request);
        String strResponse = "{\"message\":\"parameter error!!\"}";
        String strRecord;
        JSONObject jsonObject;
        Logs.showTrace("[HelloRS] bank_account id = " + id + " token = " + token);
        if (id != null && !id.equals("") && token != null && !token.equals(""))
        {
            try
            {
                SqliteHandler sqliteHandler = new SqliteHandler();
                Connection conn = sqliteHandler.getConnection("database/huanan.db");
                if (conn != null)
                {
                    String sql = "select * from bank_account where id = " + id;
                    System.out.println(sql);
                    Statement stat = null;
                    ResultSet rs = null;
                    stat = conn.createStatement();
                    rs = stat.executeQuery(sql);
                    jsonObject = new JSONObject();
                    if (rs.next())
                    {
                        jsonObject.put("id", rs.getInt("id"));
                        jsonObject.put("birthday", rs.getString("birthday"));
                        jsonObject.put("career", rs.getString("career"));
                        jsonObject.put("residence", rs.getString("residence"));
                        jsonObject.put("income", rs.getInt("income"));
                        jsonObject.put("service_units", rs.getString("service_units"));
                        jsonObject.put("marital", rs.getString("marital"));
                        jsonObject.put("education", rs.getString("education"));
                        jsonObject.put("dependents", rs.getInt("dependents"));
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
    
    
    @POST
    @Produces("application/json;charset=utf8")
    @Path("/trans_record")
    public String trans_record(@QueryParam("id") String id, @QueryParam("token") String token,
            @Context HttpServletRequest request)
    {
        LogHandler.log(token, request);
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
                    String sql = "select * from trans_record where id = '" + id + "'";
                    Statement stat = null;
                    ResultSet rs = null;
                    stat = conn.createStatement();
                    rs = stat.executeQuery(sql);
                    jsonObject = new JSONObject();
                    if (rs.next())
                    {
                        jsonObject.put("id", rs.getInt("id"));
                        jsonObject.put("account_id", rs.getString("account_id"));
                        jsonObject.put("trans_bank", rs.getString("trans_bank"));
                        jsonObject.put("trans_type", rs.getString("trans_type"));
                        jsonObject.put("trans_date", rs.getString("trans_date"));
                        jsonObject.put("trans_pay", rs.getInt("trans_pay"));
                        jsonObject.put("trans_deposit", rs.getInt("trans_deposit"));
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
    
    /**
     * @param json Incoming request data will be deserialized directly into
     *             this string
     */
    @POST
    @Path("/account/asjson")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json;charset=utf8")
    public String account(String json, @Context HttpServletRequest request)
    {
        
        String strResponse = "{\"message\":\"parameter error!!\"}";
        String strRecord;
        String id = null, token = null;
        JSONObject jsonObject;
        JSONObject jsonRequest = new JSONObject(json);
        if (!jsonRequest.isEmpty())
        {
            System.out.println("request:" + json);
            id = jsonRequest.getString("id");
            token = jsonRequest.getString("token");
            LogHandler.log(token, request);
        }
        
        if (id != null && !id.equals("") && token != null && !token.equals(""))
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
                else
                {
                    System.out.println("Database Connect Fail");
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
    
}
