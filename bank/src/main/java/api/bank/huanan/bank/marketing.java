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

@Path("/huanan/marketing")
@Produces("application/json;charset=utf8")
public class marketing {

    @GET
    @Path("/mortgage_customer_info")
    public String mortagageInfo(@QueryParam("user_id") int id, @QueryParam("api_key") String token, @Context HttpServletRequest request){

        LogHandler.log(token, request);
        boolean tokenChecker = TokenHandler.TokenHandler(token);
        JSONObject result = new JSONObject();

        if (id != 0 && token != null && !token.equals("")){
            if (tokenChecker){
                try {
                    JSONObject data;
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    if (conn != null){
                        String sql = "select * from bank_account where id =" + id;
                        Statement stat;
                        ResultSet rs;
                        stat = conn.createStatement();
                        rs = stat.executeQuery(sql);

                        data = new JSONObject();
                        while (rs.next()){
                            data.put("id", rs.getString("id"));
                            data.put("birthday", rs.getString("birthday"));
                            data.put("career", rs.getString("career"));
                            data.put("residence", rs.getString("residence"));
                            data.put("income", rs.getString("income"));
                            data.put("marital", rs.getString("marital"));
                            data.put("education", rs.getString("education"));
                            data.put("dependents", rs.getString("dependents"));
                            data.put("credit_level", rs.getString("credit_level"));
                        }

                        return data.toString();
                    }

                    //return db failed connection result
                    System.out.println("Database Connect Fail. Please check out connection.");
                    result.put("ERROR_CODE", ErrorHandler.ERROR_CONNECT_DB_CODE);
                    result.put("ERROR_MESSAGE", ErrorHandler.ERROR_CONNECT_DB);
                    return result.toString();
                } catch (Exception e) {

                    //return all db failed exception result
                    System.out.println(e.getMessage());
                    result.put("ERROR_CODE", ErrorHandler.ERROR_CONNECT_DB_CODE);
                    result.put("ERROR_MESSAGE", ErrorHandler.ERROR_CONNECT_DB);
                    return result.toString();
                }
            }

            //return token out of expired exception result
            System.out.println("API toke is out of expired date. Please check API token expiration date");
            result.put("ERROR_CODE", ErrorHandler.ERROR_TOKEN_CODE);
            result.put("ERROR_MESSAGE", ErrorHandler.ERROR_TOKEN);
            return result.toString();
        }

        //return id, token invalid result
        result.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
        result.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM);
        return result.toString();
    }

    @GET
    @Path("/loan_conditions_info")
    public String loanConditionsInfo(@QueryParam("user_id") int id, @QueryParam("api_key") String token, @Context HttpServletRequest request){

        LogHandler.log(token, request);
        boolean tokenChecker = TokenHandler.TokenHandler(token);
        JSONObject result = new JSONObject();

        if (id != 0 && token != null && !token.equals("")){
            if (tokenChecker){
                try {
                    JSONObject data;
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    if (conn != null){
                        String sql = "select * from loan_record where user_id =" + id;
                        Statement stat;
                        ResultSet rs;
                        stat = conn.createStatement();
                        rs = stat.executeQuery(sql);

                        data = new JSONObject();
                        JSONArray dataJsonArray = new JSONArray();
                        while (rs.next()){
                            data.put("amount", rs.getString("amount"));
                            data.put("percent", rs.getString("percent"));
                            data.put("usage", rs.getString("usage"));
                            data.put("period", rs.getString("period"));
                            data.put("payment_sources", rs.getString("payment_sources"));
                            data.put("grace_period", rs.getString("grace_period"));
                            data.put("property", rs.getString("property"));
                            data.put("appraisal", rs.getString("appraisal"));
                            data.put("balance", rs.getString("balance"));
                            data.put("value", rs.getString("value"));
                            data.put("situation", rs.getString("situation"));
                            data.put("interest_rate", rs.getString("interest_rate"));
                            dataJsonArray.put(data);
                        }

                        result.put("user_id", id);
                        result.put("data", dataJsonArray);
                        return result.toString();
                    }

                    //return db failed connection result
                    System.out.println("Database Connect Fail. Please check out connection.");
                    result.put("ERROR_CODE", ErrorHandler.ERROR_CONNECT_DB_CODE);
                    result.put("ERROR_MESSAGE", ErrorHandler.ERROR_CONNECT_DB);
                    return result.toString();
                } catch (Exception e) {

                    //return all db failed exception result
                    System.out.println(e.getMessage());
                    result.put("ERROR_CODE", ErrorHandler.ERROR_CONNECT_DB_CODE);
                    result.put("ERROR_MESSAGE", ErrorHandler.ERROR_CONNECT_DB);
                    return result.toString();
                }
            }

            //return token out of expired exception result
            System.out.println("API toke is out of expired date. Please check API token expiration date");
            result.put("ERROR_CODE", ErrorHandler.ERROR_TOKEN_CODE);
            result.put("ERROR_MESSAGE", ErrorHandler.ERROR_TOKEN);
            return result.toString();
        }

        //return id, token invalid result
        result.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
        result.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM);
        return result.toString();
    }

    @GET
    @Path("/construction_info")
    public String constructionInfo(@QueryParam("user_id") int id, @QueryParam("api_key") String token, @Context HttpServletRequest request){

        LogHandler.log(token, request);
        boolean tokenChecker = TokenHandler.TokenHandler(token);
        JSONObject result = new JSONObject();

        if (id != 0 && token != null && !token.equals("")){
            if (tokenChecker){
                try {
                    JSONObject data;
                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");
                    if (conn != null){
                        String sql = "select * from construction_record where user_id =" + id;
                        Statement stat;
                        ResultSet rs;
                        stat = conn.createStatement();
                        rs = stat.executeQuery(sql);

                        data = new JSONObject();
                        JSONArray dataJsonArray = new JSONArray();
                        while (rs.next()){
                            data.put("property", rs.getString("property"));
                            data.put("location", rs.getString("location"));
                            data.put("building_type", rs.getString("building_type"));
                            data.put("proximity_attr", rs.getString("proximity_attr"));
                            data.put("house_age", rs.getString("house_age"));
                            dataJsonArray.put(data);
                        }

                        result.put("user_id", id);
                        result.put("data", dataJsonArray);
                        return result.toString();
                    }

                    //return db failed connection result
                    System.out.println("Database Connect Fail. Please check out connection.");
                    result.put("ERROR_CODE", ErrorHandler.ERROR_CONNECT_DB_CODE);
                    result.put("ERROR_MESSAGE", ErrorHandler.ERROR_CONNECT_DB);
                    return result.toString();
                } catch (Exception e) {

                    //return all db failed exception result
                    System.out.println(e.getMessage());
                    result.put("ERROR_CODE", ErrorHandler.ERROR_CONNECT_DB_CODE);
                    result.put("ERROR_MESSAGE", ErrorHandler.ERROR_CONNECT_DB);
                    return result.toString();
                }
            }

            //return token out of expired exception result
            System.out.println("API toke is out of expired date. Please check API token expiration date");
            result.put("ERROR_CODE", ErrorHandler.ERROR_TOKEN_CODE);
            result.put("ERROR_MESSAGE", ErrorHandler.ERROR_TOKEN);
            return result.toString();
        }

        //return id, token invalid result
        result.put("ERROR_CODE", ErrorHandler.ERROR_PARM_CODE);
        result.put("ERROR_MESSAGE", ErrorHandler.ERROR_PARM);
        return result.toString();
    }
}
