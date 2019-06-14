package api.bank.huanan.utility;


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
       Date dNow = new Date( );
       SimpleDateFormat ft =
               new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
       
       MongoClient mongoClient = new MongoClient("localhost", 27017);
       MongoDatabase database = mongoClient.getDatabase("trackDB");
       MongoCollection<Document> collection = database.getCollection("track");
       Document document = new Document();
       Document doc_url = new Document();
       doc_url.put("url", request.getRequestURL().toString());
       doc_url.put("param", request.getQueryString());
       doc_url.put("method", request.getMethod());
       doc_url.put("remote", request.getRemoteAddr());
       document.put("token", strToken);
       document.put("request",doc_url);
       document.put("create_date",ft.format(dNow));
       collection.insertOne(document);
    
       doc_url = null;
       document = null;
       mongoClient.close();
       mongoClient = null;
   }
   
    
   
}
