package api.modules;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;


/**
 * Created by Jugo on 2019/6/13
 */

public abstract class LogHandler
{
   public static void log(String strToken, HttpServletRequest request)
   {
       LogThread logThread = new LogThread(strToken,request.getRequestURL().toString(),
               request.getQueryString(),request.getMethod(),request.getRemoteAddr());
       logThread.start();
   }
    
    static class LogThread extends Thread {
       
       private String m_strToken;
       private String m_strURL;
       private String m_strParam;
       private String m_strMethod;
       private String m_strRemove;
       
       LogThread(String strToken, String strURL, String strParam, String strMethod,
               String strRemove)
       {
           m_strToken = strToken;
           m_strURL = strURL;
           m_strParam = strParam;
           m_strMethod = strMethod;
           m_strRemove = strRemove;
       }
        public void run(){
            try
            {
                MongoClient mongoClient = new MongoClient("localhost", 27017);
                MongoDatabase database = mongoClient.getDatabase("trackDB");
                MongoCollection<Document> collection = database.getCollection("track");
                Document document = new Document();
                Document doc_url = new Document();
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                doc_url.put("url", m_strURL);
                doc_url.put("param", m_strParam);
                doc_url.put("method", m_strMethod);
                doc_url.put("remote", m_strRemove);
                document.put("token", m_strToken);
                document.put("request", doc_url);
                document.put("create_date", ft.format(dNow));
                collection.insertOne(document);
        
                doc_url = null;
                document = null;
                mongoClient.close();
                mongoClient = null;
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
        }
    }
   
}
