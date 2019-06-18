/**
 * @author Louis Ju
 * @date 2015-07-30
 * @description common log module
 */

package api.modules;

import java.lang.Throwable;

public abstract class Logs
{
	private static final int	mnTraceLevel	= 1;

	private static class LogInfo
	{
		String	strFile			= "";
		String	strClassPath	= "";
		String	strClassName	= "";
		String	strMethod		= "";
		int		nLine			= -1;
	}

	private static LogInfo logInfo = new LogInfo();

	@SuppressWarnings("unused")
	public static void showTrace(String msg)
	{
		if (null != msg && msg.length() > 0)
		{
			Throwable throwable = new Throwable();
			logInfo.strFile = throwable.getStackTrace()[1].getFileName();
			logInfo.strClassPath = throwable.getStackTrace()[1].getClassName();
			logInfo.strClassName = extractSimpleClassName(logInfo.strClassPath);
			logInfo.strMethod = throwable.getStackTrace()[1].getMethodName();
			logInfo.nLine = throwable.getStackTrace()[1].getLineNumber();
			throwable = null;
			String strLog = null;
			switch(mnTraceLevel)
			{
			case 1:
				strLog = "[TRACE] " + msg;
				break;
			case 2:
				strLog = "[TRACE] " + " class: " + logInfo.strClassPath + " line: " + logInfo.nLine + " Msg: " + msg;
				break;
			case 3:
				strLog = "[TRACE] " + "file: " + logInfo.strFile + " class: " + logInfo.strClassPath + " method: "
						+ logInfo.strMethod + " line: " + logInfo.nLine + " Msg: " + msg;
				break;
			default:
				return;
			}

			System.out.println(logInfo.strClassName +" "+ strLog);
		}
	}

	public static void showError(String msg)
	{
		if (msg.length() > 0)
		{
			Throwable throwable = new Throwable();
			logInfo.strFile = throwable.getStackTrace()[1].getFileName();
			logInfo.strClassPath = throwable.getStackTrace()[1].getClassName();
			logInfo.strClassName = extractSimpleClassName(logInfo.strClassPath);
			logInfo.strMethod = throwable.getStackTrace()[1].getMethodName();
			logInfo.nLine = throwable.getStackTrace()[1].getLineNumber();
			throwable = null;
			String strLog = null;

			strLog = "[TRACE] " + "file: " + logInfo.strFile + " class: " + logInfo.strClassPath + " method: "
					+ logInfo.strMethod + " line: " + logInfo.nLine + " Msg: " + msg;
			
			System.out.println(logInfo.strClassName + " " + strLog);
		}
	}

	public static String extractSimpleClassName(String fullClassName)
	{
		if ((null == fullClassName) || ("".equals(fullClassName)))
		{
			return "";
		}
		int lastDot = fullClassName.lastIndexOf('.');
		if (0 > lastDot)
			return fullClassName;
		return fullClassName.substring(++lastDot);
	}
	
}
