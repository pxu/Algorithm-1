package JavaFund;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by zhahua on 3/29/17.
 */
public class PrintException {
  public static void main(String[] args) {
    try {
      int i = 0;
      int j = 10 / i;
    }catch(Exception e) {
      String wt = StringUtils.getStringFromStackTrace(e);
      System.out.print(wt);
    }
  }
}
 class StringUtils
{
  /**
   * Convert an exception to a String with full stackqueue trace
   * @param ex the exception
   * @return a String with the full stacktrace error text
   */
  public static String getStringFromStackTrace(Throwable ex)
  {
    if (ex==null)
    {
      return "";
    }
    StringWriter str = new StringWriter();
    PrintWriter writer = new PrintWriter(str);
    try
    {
      ex.printStackTrace(writer);
      return str.getBuffer().toString();
    }
    finally
    {
      try
      {
        str.close();
        writer.close();
      }
      catch (IOException e)
      {
        //ignore
      }
    }
  }
}
