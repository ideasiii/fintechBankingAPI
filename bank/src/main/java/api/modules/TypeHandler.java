package api.modules;


/**
 * Created by Jugo on 2019-06-25
 */

public abstract class TypeHandler
{
    public static String TypeHandler(String number, String type){
        
        String para = "";
    
        if (0 == type.compareTo("0")) // 字串的比較方式，compareTo 成立回傳 0，不成立回傳 -1
        {
            para = "identity_id";
            if(number.length() != 10){
                return null;
            }
        }
        else if (0 == type.compareTo("1"))
        {
            para = "license_no";
            if(number.length() > 8){
                return null;
            }
        }
    
        return para;
    }
}
