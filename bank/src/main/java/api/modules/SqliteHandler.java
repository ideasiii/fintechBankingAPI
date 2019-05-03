package api.modules;

/**
 * Created by Jugo on 2019/5/3
 */

import java.sql.*;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

public class SqliteHandler
{
    public SqliteHandler()
    {
    }
    
    public Connection getConnection(String strDbPath) throws SQLException
    {
        SQLiteConfig config = new SQLiteConfig();
        config.setSharedCache(true);
        config.enableRecursiveTriggers(true);
        SQLiteDataSource ds = new SQLiteDataSource(config);
        ds.setUrl("jdbc:sqlite:" + strDbPath);
        return ds.getConnection();
    }
}
