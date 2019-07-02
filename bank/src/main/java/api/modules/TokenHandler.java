package api.modules;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Jugo on 2019/6/27
 */


public abstract class TokenHandler
{
    /**
     * 驗證token是否有效
     * @param token
     * @return
     */
    public static Boolean TokenHandler(String token)
    {
        SqliteHandler sqliteHandler = new SqliteHandler();
        try
        {
            Connection conn = sqliteHandler.getConnection("database/huanan.db");
            
            if(conn != null){
                String sql = "select * from tokens where token ='" + token + "'";
                Statement stat = null;
                ResultSet rs = null;
                stat = conn.createStatement();
                rs = stat.executeQuery(sql);
    
                if (rs.next())
                {
                    return true;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    
    
        return false;
    }
    
    /**
     * 產生token新增到資料庫
     * @param count 產生的筆數
     */
    public void genericToken(int count)
    {

    }
}
