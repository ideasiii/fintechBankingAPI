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
import api.modules.SqliteHandler;

@Path("huanan/insurance")
public class insurance {

    @GET
    @Path("/record")
    public String record(@QueryParam("user_id") int id, @QueryParam("type") String type, @QueryParam("api_key") String token, @Context HttpServletRequest request) {
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;
        jsonObject = new JSONObject();

        if (id != 0 && token != null && !token.equals("")) {
            if (type != null && !type.equals("")) {
                try {

                    SqliteHandler sqliteHandler = new SqliteHandler();
                    Connection conn = sqliteHandler.getConnection("database/huanan.db");

                    if (conn != null) {
                        String sql = "select * from insurance_record where user_id =" + id;
                        Statement stat = null;
                        ResultSet rs = null;
                        stat = conn.createStatement();
                        rs = stat.executeQuery(sql);
//                        rs = conn.createStatement().executeQuery(sql);
                        jsonArray = new JSONArray();


                        if (rs.next()) {
                            do {
                                dataJson = new JSONObject();
                                dataJson.put("id", rs.getString("id"));
                                dataJson.put("policy_no", rs.getString("policy_no"));
                                dataJson.put("insurance_category", rs.getString("insurance_category"));
                                dataJson.put("insurance_name", rs.getString("insurance_name"));
                                dataJson.put("insurance_premiums", rs.getInt("insurance_premiums"));
                                dataJson.put("insurance_date", rs.getString("insurance_date"));
                                dataJson.put("insurance_expiration_date", rs.getString("insurance_expiration_date"));

                                if (type == "1") {
                                    dataJson.put("car_type", rs.getString("car_type"));
                                }

                                jsonArray.put(dataJson);


                            } while (rs.next());

                            jsonObject.put("user_id", id);
                            jsonObject.put("insurance_record", jsonArray);
                            return jsonObject.toString();

                        } else {
                            jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_NO_USER_CODE);
                            jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_NO_USER);
                            return jsonObject.toString();
                        }

                    } else {
                        System.out.println("Database Connect Fail");
                        jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_CONNECT_DB_CODE);
                        jsonObject.put("ERROR_MESSAGE", ErrorHandler.ERROR_CONNECT_DB);
                        return jsonObject.toString();
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());

                    jsonObject.put("ERROR_CODE", ErrorHandler.ERROR_EXCEPTION);
                    jsonObject.put("ERROR_MESSAGE", e.getMessage());

                    return jsonObject.toString();

                }
            } else {
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
