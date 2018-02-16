package edu.thapar.newindialms;

/**
 * Created by kamalshree on 2/15/2018.
 */

public class Common {
    public static String currentToken="";

    private static String baseUrl="https://fcm.googleapis.com/";

    public static APIService getFCMClient(){
        return RetrofitClient.getClient(baseUrl).create(APIService.class);
    }
}
