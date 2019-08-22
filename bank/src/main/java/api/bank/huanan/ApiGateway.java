package api.bank.huanan;

import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import api.modules.Restapiclient.Http;
import api.modules.Restapiclient.Response;

import api.modules.Logs;
import api.modules.Restapiclient.HttpConfig;

import java.sql.*;
import java.util.Locale;

/**
 * Created by Jugo on 2019/6/17
 */

@Path("/api")
public class ApiGateway
{
    private final String url = " jdbc:mysql://localhost:3306/apigateway";
    String user = "apim";
    String password = "apim";
    
    private String gateway(HttpServletRequest request)
    {
        String strURL = request.getRequestURL().toString();
        String strParam = request.getQueryString();
        String strMethod = request.getMethod();
        String strRemove = request.getRemoteAddr();
        String strURI = request.getRequestURI();
        String strContentType = request.getContentType();
        String strLog =
                String.format("[Urlgateway] gateway\nURL: " + "%s\nParam=%s\nMethod=%s" +
                        "\nRemove=%s\nURI=%s\nContent-Type=%s", strURL, strParam, strMethod,
                        strRemove, strURI, strContentType);
        Logs.showTrace(strLog);
        return sendRedirect(strURI, strContentType, strMethod);
    }
    
    private String sendRedirect(String strURL, String strContentType, String strMethod)
    {
        HashMap<String, String> parameter = new HashMap<>();
        parameter.put("id", "1");
        parameter.put("token", "1");
        Response response = new Response();
        Http.POST("http://127.0.0.1:8080/bank/huanan/hello/apiPOST",
                HttpConfig.HTTP_DATA_TYPE.X_WWW_FORM, parameter, response);
        if (200 == response.Code)
        {
            return response.Data;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", response.Code);
        jsonObject.put("message", response.Data);
        
        return jsonObject.toString();
    }
    
    private String getURL(String strURI)
    {
        String strURL = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            Logs.showError(e.getMessage());
        }
        
        return strURL;
    }
    
    @GET
    @Path("/{uri1}")
    public String uri1GET(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @POST
    @Path("/{uri1}")
    public String uri1POST(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}")
    public String uri2GET(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @POST
    @Path("/{uri1}/{uri2}")
    public String uri2POST(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}")
    public String uri3GET(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @POST
    @Path("/{uri1}/{uri2}/{uri3}")
    public String uri3POST(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}")
    public String uri4GET(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @POST
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}")
    public String uri4POST(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}")
    public String uri5GET(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @POST
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}")
    public String uri5POST(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}/{uri6}")
    public String uri6GET(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @POST
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}/{uri6}")
    public String uri6POST(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}/{uri6}/{uri7}")
    public String uri7GET(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @POST
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}/{uri6}/{uri7}")
    public String uri7POST(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}/{uri6}/{uri7}/{uri8}")
    public String uri8GET(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @POST
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}/{uri6}/{uri7}/{uri8}")
    public String uri8POST(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}/{uri6}/{uri7}/{uri8}/{uri9}")
    public String uri9GET(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @POST
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}/{uri6}/{uri7}/{uri8}/{uri9}")
    public String uri9POST(@Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    
}
