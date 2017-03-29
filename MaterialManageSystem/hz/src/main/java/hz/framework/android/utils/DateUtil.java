package hz.framework.android.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhenghou on 2016/7/4.
 */
public class DateUtil {
    private static SimpleDateFormat sdf ;
    public static String getDateString(String pattern){
        sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }
}
