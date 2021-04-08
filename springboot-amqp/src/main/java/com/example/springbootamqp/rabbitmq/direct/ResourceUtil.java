package com.example.springbootamqp.rabbitmq.direct;

import java.util.ResourceBundle;

/**
 * @Author: qingshan
 * @Description: 咕泡学院，只为更好的你
 */
public class ResourceUtil {
    private static final ResourceBundle resourceBundle;

    static{
        resourceBundle = ResourceBundle.getBundle("gupaomq");
    }

    public static String getKey(String key){
        return resourceBundle.getString(key);
    }

}
