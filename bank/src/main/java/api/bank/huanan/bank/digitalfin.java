package api.bank.huanan.bank;

import api.modules.SqliteHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


@Path("/huanan/digitalfin")
public class digitalfin {


    @POST
    @Path("/accounts")
    @Consumes(MediaType.APPLICATION_JSON)
    public String accounts(String json){
        String strResponse = "{\"message\":\"parameter error!!\"}";
        String strRecord;
        String id = null;
        String token = null;
        JSONObject jsonRequest = new JSONObject(json);
        JSONObject jsonObject, dataJson;
        JSONArray jsonArray;


        if(!jsonRequest.isEmpty()){
            System.out.println("request:" + json);
            id = jsonRequest.getString("account_id");
            token = jsonRequest.getString("token");
        }

        if(id != null && !id.equals("") && token != null && !token.equals("")){
            try {

                SqliteHandler sqliteHandler = new SqliteHandler();
                Connection conn = sqliteHandler.getConnection("database/huanan.db");

                if(conn != null){

                    String sql = "select * from trans_record where account_id = " + id;
                    Statement stat = null;
                    ResultSet rs = null;
                    stat = conn.createStatement();
                    rs = stat.executeQuery(sql);
                    jsonObject = new JSONObject();
                    jsonArray = new JSONArray();
                    int payTotal = 0, depositTotal = 0;


                    while(rs.next()){
                        dataJson = new JSONObject();
                        dataJson.put("id", rs.getInt("id"));
                        dataJson.put("account_id", rs.getString("account_id"));
                        dataJson.put("trans_bank", rs.getString("trans_bank"));
                        dataJson.put("trans_type", rs.getString("trans_type"));
                        dataJson.put("trans_date", rs.getString("trans_date"));
                        dataJson.put("trans_pay", rs.getInt("trans_pay"));
                        dataJson.put("trans_deposit", rs.getInt("trans_deposit"));

                        int pay = rs.getInt("trans_pay");
                        int deposit = rs.getInt("trans_deposit");

                        payTotal = payTotal+pay;
                        depositTotal = depositTotal+deposit;

                        jsonArray.put(dataJson);
                    }

                    jsonObject.put("trans_record", jsonArray);
                    jsonObject.put("balance", depositTotal - payTotal);

                    return jsonObject.toString();

                }else {
                    System.out.println("Database Connect Fail");
                }

            }catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return "account id is " + id + " token is " + token;
        }

        return strResponse;
    }

    @POST
    @Path("/customers")
    @Consumes(MediaType.APPLICATION_JSON)
    public String customers(String json){

        String strResponse = "{\"message\":\"parameter error!!\"}";
        String strRecord;
        int id = 0;
        String token = null;
        JSONObject jsonObject;
        JSONObject jsonRequest = new JSONObject(json);

        if(!jsonRequest.isEmpty()){

            System.out.println("resquest:" + json);
            id = jsonRequest.getInt("id");
            token = jsonRequest.getString("token");
        }

        if(id != 0 && token != null && !token.equals("")){

            try{

                SqliteHandler sqliteHandler = new SqliteHandler();
                Connection conn = sqliteHandler.getConnection("database/huanan.db");

                if(conn != null){

                    String sql = "select * from bank_account where id = " + id;
                    Statement stat = null;
                    ResultSet rs = null;
                    stat = conn.createStatement();
                    rs = stat.executeQuery(sql);
                    jsonObject = new JSONObject();

                    if(rs.next()){

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

                    } else {
                        System.out.println("Database Connect Fail");
                    }
                }


            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            return "customer id is " + id + " token is " + token;
        }

        return strResponse;
    }

}
