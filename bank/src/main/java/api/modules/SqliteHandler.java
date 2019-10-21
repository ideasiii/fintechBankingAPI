package api.modules;

/**
 * Created by Jugo on 2019/5/3
 */

import java.sql.*;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

public class SqliteHandler
{
    static private Connection connection = null;
    public SqliteHandler()
    {
    }
    
    public Connection getConnection(String strDbPath) throws SQLException
    {
        if(null == connection || connection.isClosed())
        {
            SQLiteConfig config = new SQLiteConfig();
            config.setSharedCache(true);
            config.enableRecursiveTriggers(true);
            SQLiteDataSource ds = new SQLiteDataSource(config);
            ds.setUrl("jdbc:sqlite:" + strDbPath);
            connection = ds.getConnection();
            Logs.showTrace("[SqliteHandler] getConnection Sqlite Connected");
        }
        return connection;
    }
    
}
