package api.modules;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Jugo on 2019-07-12
 */

public abstract class TransUUID
{
    /**
     * UUID 轉 UserId
     *
     * @param uuid
     * @return
     */
    public static Integer UUIDHandler(String uuid)
    {
        
        SqliteHandler sqliteHandler = new SqliteHandler();
        try
        {
            Connection conn = sqliteHandler.getConnection("database/huanan.db");
            
            if (conn != null)
            {
                String sql = "select * from bank_account where uuid ='" + uuid + "'";
                Statement stat = null;
                ResultSet rs = null;
                stat = conn.createStatement();
                rs = stat.executeQuery(sql);
                
                if (rs.next())
                {
                    return rs.getInt("id");
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        
        return 0;
    }
    
    /**
     * UUID 轉 Serial number
     * @param uuid
     * @return
     */
    
    public static Integer serialHandler(String uuid)
    {
        
        SqliteHandler sqliteHandler = new SqliteHandler();
        try
        {
            Connection conn = sqliteHandler.getConnection("database/huanan.db");
            
            if (conn != null)
            {
                String sql = "select * from bank_account where uuid ='" + uuid + "'";
                Statement stat = null;
                ResultSet rs = null;
                stat = conn.createStatement();
                rs = stat.executeQuery(sql);
                
                if (rs.next())
                {
                    return rs.getInt("serial");
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        return 0;
    }
}
