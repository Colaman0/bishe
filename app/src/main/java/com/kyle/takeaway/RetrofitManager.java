package com.kyle.takeaway;

import android.util.Log;

import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.entity.LoginEntity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by kyle on 2019/2/23
 * Function :
 */
public class RetrofitManager {
    private static RetrofitManager mRetrofitManager = new RetrofitManager();
    RetrofitService service;

    private RetrofitManager() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog", "retrofitBack = " + message);
            }
        });
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(httpClient)
                .build();

        service = retrofit.create(RetrofitService.class);
    }

    public static RetrofitManager getInstance() {
        return mRetrofitManager;
    }

    public Observable<LoginEntity> userLogin(String phone, String psw) {
        return service.login(phone, psw)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<LoginEntity> storeLogin(String phone, String psw) {
        return service.storeLogin(phone, psw)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable getRegisterCode(String phone, int type) {
        return service.getRegisterCode(phone, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable register(String nickName, String mobile_phone, String password, String verify_code, int type) {
        return service.register(nickName, mobile_phone, password, verify_code, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}