package edu.thapar.newindialms;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by kamalshree on 2/15/2018.
 */

public interface APIService {

    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAPC2czeg:APA91bEnxFE1JajAd8chnMqqWDZrpbaRWYqGD-XuXc85ynIg9-S201HSKfwML-V1XvM4BoD-NRS3gMuEQ8tU3Nu7RtefYTCgTD1CE6WMnMG1k6qvr-ZiwcAYlWQWWMdrzc6sSycvHmQY"
    })
    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
