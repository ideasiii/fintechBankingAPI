package api.modules;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

/**
 * Created by Jugo on 2019/8/2
 */

public class MongoHandler
{
    public MongoHandler()
    {
    }
    
    public int getRecords(String strStartDate, String strEndDate)
    {
        int nResult = 0;
        try
        {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase database = mongoClient.getDatabase("trackDB");
            MongoCollection<Document> collection = database.getCollection("track");
            
            BasicDBObject gtQuery = new BasicDBObject();
            gtQuery.put("create_date", new BasicDBObject("$gte", strStartDate).append("$lte",
                    strEndDate));
            FindIterable<Document> cursor = collection.find(gtQuery);
            for (Document doc : cursor)
            {
                Logs.showTrace(doc.getString("url"));
            }
            mongoClient.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            nResult = -1;
        }
        return nResult;
    }
}
