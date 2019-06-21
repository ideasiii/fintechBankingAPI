package api.modules.Restapiclient;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import api.modules.Logs;

/**
 * Created by Jugo on 2018/3/28
 */
public abstract class Http
{
    private static EventListener.Callback eventListener = null;
    
    public static void setResponseListener(EventListener.Callback listener)
    {
        eventListener = listener;
    }
    
    public static void POST(final String httpsURL, final HttpConfig.HTTP_DATA_TYPE http_data_type,
        final HashMap<String, String> parameters, Response response)
    {
        JSONObject jsonResponse = new JSONObject();
        
        try
        {
            jsonResponse.put("id", response.Id);
            jsonResponse.put("code", -1);
            String strParameter = getPostDataString(parameters);
            Logs.showTrace("[Http] POST : URL=" + httpsURL + " Data Type=" + http_data_type.toString() + " Parameter:" + strParameter);
            URL url = new URL(httpsURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setConnectTimeout(HttpConfig.TIME_OUT_CONNECT);
            con.setReadTimeout(HttpConfig.TIME_OUT_READ);
            con.setRequestProperty("Content-length", String.valueOf(strParameter.length()));
            con.setRequestProperty("Content-Type", http_data_type.toString());
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setDoOutput(true);
            con.setDoInput(true);
            
            DataOutputStream output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(strParameter);
            output.close();
            
            response.Code = con.getResponseCode();
            
            if (response.Code == HttpURLConnection.HTTP_OK)
            {
                response.Data = "";
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                while ((line = br.readLine()) != null)
                {
                    response.Data += line;
                }
            }
            else
            {
                response.Data = con.getResponseMessage();
                Logs.showTrace("[Http] ERROR HTTP Response Code:" + response.Code);
            }
            jsonResponse.put("code", response.Code);
            jsonResponse.put("data", response.Data);
        }
        catch (Exception e)
        {
            Logs.showError("[Http] POST Exception: " + e.getMessage());
            jsonResponse.put("code", -1);
            jsonResponse.put("data", e.getMessage());
        }
        
        if (null != eventListener)
        {
            eventListener.onEvent(jsonResponse);
        }
        
        Logs.showTrace("[Http] POST Response: " + jsonResponse);
    }
    
    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (HashMap.Entry<String, String> entry : params.entrySet())
        {
            if (first)
            {
                first = false;
            }
            else
            {
                result.append('&');
            }
            
            result.append(URLEncoder.encode(entry.getKey(), HttpConfig.ENCODING));
            result.append('=');
            result.append(URLEncoder.encode(entry.getValue(), HttpConfig.ENCODING));
        }
        
        return result.toString();
    }
}
