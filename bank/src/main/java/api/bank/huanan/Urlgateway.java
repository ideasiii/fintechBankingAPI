package api.bank.huanan;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import api.modules.Restapiclient.Http;
import api.modules.Restapiclient.Response;

import api.modules.Logs;
import api.modules.Restapiclient.HttpConfig;
import api.modules.Restapiclient.RestApiClient;


/**
 * Created by Jugo on 2019/6/17
 */

@Path("/api")
public class Urlgateway
{
   
    
    private String gateway(HttpServletRequest request)
    {
        String strURL = request.getRequestURL().toString();
        String strParam = request.getQueryString();
        String strMethod = request.getMethod();
        String strRemove = request.getRemoteAddr();
        String strURI = request.getRequestURI();
        sendRedirect(strURI);
        String strLog = String.format("[Urlgateway] gateway\nURL: " +
                        "%s\nParam=%s\nMethod=%s\nRemove=%s\nContext=%s",strURL
                ,strParam,strMethod,strRemove,strURI);
        Logs.showTrace(strLog);
        return strLog;
    }
    
    private void sendRedirect(String strURI)
    {
        /*
        RestApiClient restApiClient = new RestApiClient();
        restApiClient.setResponseListener(new RestApiClient.ResponseListener()
        {
            @Override
            public void onResponse(JSONObject jsonObject)
            {
                Logs.showTrace("response callback: " + jsonObject.toString());
            }
        });
        HashMap<String,String> parameter = new HashMap<>();
        parameter.put("id","1");
        parameter.put("token","1");
        Response response = new Response();
        restApiClient.HttpPost("http://127.0.0.1:8080/bank/huanan/hello/account",
                HttpConfig.HTTP_DATA_TYPE.X_WWW_FORM,parameter,response);
    */
        HashMap<String,String> parameter = new HashMap<>();
        parameter.put("id","1");
        parameter.put("token","1");
        Response response = new Response();
        Http.POST("http://127.0.0.1:8080/bank/huanan/hello/formparam",
                HttpConfig.HTTP_DATA_TYPE.X_WWW_FORM,parameter,response);
        if(200 == response.Code)
        {
            Logs.showTrace(response.Data);
        }
    }
    
    
    @GET
    @Path("/{uri1}")
    public String uri1( @Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}")
    public String uri2( @Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}")
    public String uri3( @Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}")
    public String uri4( @Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}")
    public String uri5( @Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}/{uri6}")
    public String uri6( @Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    @GET
    @Path("/{uri1}/{uri2}/{uri3}/{uri4}/{uri5}/{uri6}/{uri7}")
    public String uri7( @Context HttpServletRequest request)
    {
        return gateway(request);
    }
    
    
}
