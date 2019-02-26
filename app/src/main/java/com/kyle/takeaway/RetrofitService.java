package com.kyle.takeaway;

import com.kyle.takeaway.entity.BaseResponse;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.entity.LoginEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Create by kyle on 2019/2/23
 * Function :
 */
public interface RetrofitService {

    @POST(Constants.LOGIN_USER)
    @FormUrlEncoded
    public Observable<BaseResponse<LoginEntity>> login(@Field("mobile_phone") String phone, @Field("password") String psw);

    @POST(Constants.LOGIN_STORE)
    @FormUrlEncoded
    public Observable<BaseResponse<LoginEntity>> storeLogin(@Field("mobile_phone") String phone, @Field("password") String psw);

    @POST(Constants.GET_REGISTER_CODE)
    @FormUrlEncoded
    public Observable<BaseResponse> getRegisterCode(@Field("mobile_phone") String phone, @Field("type") int type);

    @POST(Constants.GET_RESETPSW_CODE)
    @FormUrlEncoded
    public Observable<BaseResponse> getResetCode(@Field("mobile_phone") String phone, @Field("type") int type);

    @POST(Constants.REGISTER)
    @FormUrlEncoded
    public Observable<BaseResponse> register(@Field("nickname") String nickname, @Field("mobile_phone") String mobile_phone,
                                             @Field("password") String password, @Field("verify_code") String verify_code,
                                             @Field("type") int type);


    @POST(Constants.REGISTER)
    @FormUrlEncoded
    public Observable<BaseResponse> resetPassword(@Field("mobile_phone") String mobile_phone,
                                                  @Field("password") String password, @Field("verify_code") String verify_code,
                                                  @Field("type") int type);
}
