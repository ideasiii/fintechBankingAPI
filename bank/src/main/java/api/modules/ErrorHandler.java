package api.modules;

/**
 * Created by Jugo on 2019/6/10
 */

public abstract class ErrorHandler
{
    public final static int ERROR_OK = 0;
    public final static int ERROR_EXCEPTION = -1;
    public final static int ERROR_CONNECT_DB_CODE = 1;
    public final static String ERROR_CONNECT_DB = "Database Connect Fail";
    public final static int ERROR_TOKEN_CODE = 2;
    public final static String ERROR_TOKEN = "Token Error";
    public final static int ERROR_PARM_CODE = 3;
    public final static String ERROR_PARM = "Parameter Error";
    public final static int ERROR_NO_ACCOUNT_CODE = 4;
    public final static String ERROR_NO_ACCOUNT = "Can't find this account";
    public final static int ERROR_NO_USER_CODE = 5;
    public final static String ERROR_NO_USER = "Can't find this user";
    public final static int ERROR_NO_RECORD_CODE = 6;
    public final static String ERROR_NO_RECORD = "No Record";
    public final static int ERROR_TYPE_CODE = 7;
    public final static String ERROR_TYPE = "Wrong type";
}
