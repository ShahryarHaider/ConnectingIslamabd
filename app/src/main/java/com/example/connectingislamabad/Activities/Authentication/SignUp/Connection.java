package com.example.connectingislamabad.Activities.Authentication.SignUp;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {

    //establish connection
    private static final String URL="http://192.168.10.63/adduser.php/";

    private static Retrofit retrofit=null;

    //public method that returns connection

    public static  Retrofit getConnection()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
