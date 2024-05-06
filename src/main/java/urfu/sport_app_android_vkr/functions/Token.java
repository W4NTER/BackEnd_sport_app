package urfu.sport_app_android_vkr.functions;

import java.util.UUID;

public class Token {

    public static String generateToken(){
        return UUID.randomUUID().toString();
    }
}
