package edu.thapar.newindialms;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by kamalshree on 2/15/2018.
 */

public class RetrofitClient {

    private static Retrofit retrofit=null;

    public static Retrofit getClient(String baseURL){

        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
