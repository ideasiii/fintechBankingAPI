package api.modules;

/**
 * Created by Jugo on 2019/6/10
 */

public abstract class ErrorHandler
{
    public final static int ERROR_OK = 0;
    public final static int ERROR_EXCEPTION = -1;
    public final static int ERROR_CODE = 1;
    public final static String ERROR_NO_ACCOUNT = "沒有此帳戶";
}
