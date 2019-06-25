package api.modules;


/**
 * Created by Jugo on 2019-06-25
 */

public abstract class TypeHandler
{
    /***
     *
     * @param number 身分證ID (長度10) or 車牌號碼 (長度小於8)
     * @param type 0表示為身分證ID, 1表示為車牌號碼
     * @return
     */
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
