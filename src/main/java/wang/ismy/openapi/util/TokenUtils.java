package wang.ismy.openapi.util;

import java.util.UUID;

/**
 * @author MY
 * @date 2020/1/29 19:13
 */
public class TokenUtils {

    public static String getToken(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
