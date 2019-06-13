package api.bank.huanan.utility;


import com.mongodb.MongoClient;

/**
 * Created by Jugo on 2019/6/13
 */

public class LogHandler
{

    public LogHandler()
    {
    
    }
    
    public int connect(String strIP, int nPort)
    {
        MongoClient mongoClient = new MongoClient(strIP, nPort);
        
        return 0;
    }
    
}
