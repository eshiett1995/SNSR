package com.seamsnstitches.snsr.helper;


/**
 * Created by Oto-obong on 23/01/2018.
 */

public class Apihelper {

    public String base_url = "xxxx";

    public interface RetrofitCallback<T>{
        void onCompleted(Throwable t, T result, boolean status);
    }

    /**public void Login(UserModel userModel, final RetrofitCallback<LoginModel> callback){

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        TellisClient client = retrofit.create(TellisClient.class);

        Call<LoginModel> call = client.authenticateUser(userModel);

        call.enqueue(new Callback<LoginModel>() {

            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                callback.onCompleted(null,response.body(),true);
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                callback.onCompleted(t,null,false);

            }
        });


    }**/

}
