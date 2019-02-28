package com.kyle.takeaway;

import com.kyle.takeaway.entity.AddressEntity;
import com.kyle.takeaway.entity.BaseResponse;
import com.kyle.takeaway.entity.CartItemEntity;
import com.kyle.takeaway.entity.CartParam;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.entity.LoginEntity;
import com.kyle.takeaway.entity.OrderItemEntity;
import com.kyle.takeaway.entity.PageDTO;
import com.kyle.takeaway.entity.ProductEntity;
import com.kyle.takeaway.entity.StoreEntity;
import com.kyle.takeaway.entity.UploadEntity;
import com.kyle.takeaway.entity.UserInfoEntity;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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
    public Observable<BaseResponse<Object>> getRegisterCode(@Field("mobile_phone") String phone, @Field("type") int type);

    @POST(Constants.GET_RESETPSW_CODE)
    @FormUrlEncoded
    public Observable<BaseResponse<Object>> getResetCode(@Field("mobile_phone") String phone, @Field("type") int type);

    @POST(Constants.REGISTER)
    @FormUrlEncoded
    public Observable<BaseResponse<Object>> register(@Field("nickname") String nickname, @Field("mobile_phone") String mobile_phone,
                                                     @Field("password") String password, @Field("verify_code") String verify_code,
                                                     @Field("type") int type);


    @POST(Constants.RESET_PSW)
    @FormUrlEncoded
    public Observable<BaseResponse<Object>> resetPassword(@Field("mobile_phone") String mobile_phone,
                                                          @Field("password") String password, @Field("verify_code") String verify_code,
                                                          @Field("type") int type);

    /**
     * 用户修改密码
     *
     * @return
     */
    @POST(Constants.USER_CHANGE_PSW)
    @FormUrlEncoded
    public Observable<BaseResponse<Object>> userChangePsw(@Field("user_id") String id, @Field("oldPassword") String old, @Field("newPassword") String newPsw);


    /**
     * 店铺修改密码
     *
     * @return
     */
    @POST(Constants.STORE_CHANGE_PSW)
    @FormUrlEncoded
    public Observable<BaseResponse<Object>> storeChangePsw(@Field("user_id") String id, @Field("oldPassword") String old, @Field("newPassword") String newPsw);

    /**
     * 用户信息
     *
     * @return
     */
    @POST(Constants.GET_USER_INFO)
    @FormUrlEncoded
    public Observable<BaseResponse<UserInfoEntity>> getUserInfo(@Field("user_id") String id);

    /**
     * 编辑用户信息
     *
     * @return
     */
    @POST(Constants.EDIT_USER_INFO)
    @FormUrlEncoded
    Observable<BaseResponse<UserInfoEntity>> editUserInfo(@Field("user_id") String id, @Field("name") String name, @Field("avatar") String avator);


    @POST(Constants.UPLOAD_PIC)
    @Multipart
    Observable<BaseResponse<UploadEntity>> uploadPic(@Part MultipartBody.Part file, @Part("description") RequestBody description);


    @POST(Constants.GET_ADDRESS_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<PageDTO<AddressEntity>>> getAdddress(@Field("user_id") String id, @Field("page") int page, @Field("page_count") int page_count);


    @POST(Constants.ADD_ADDRESS_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<List<AddressEntity>>> addAdddress(@Field("user_id") String id, @Field("content") String content);


    @POST(Constants.DELETE_ADDRESS_LIST)
    @FormUrlEncoded
    Observable<BaseResponse<Object>> delete(@Field("address_id") String id);

    @GET(Constants.GET_STORE)
    Observable<BaseResponse<PageDTO<StoreEntity>>> getStores(@Query("order_type") int order_type, @Query("store_name") String store_name,
                                                             @Query("page") int page, @Query("page_count") int page_count);

    @GET(Constants.GET_STORE_DETAIL)
    Observable<BaseResponse<PageDTO<ProductEntity>>> getStoreDetail(@Query("store_id") int store_id, @Query("page") int page, @Query("page_count") int page_count);

    @POST(Constants.POST_CART)
    Observable<BaseResponse<PageDTO<ProductEntity>>> handleCart(@Body CartParam param);

    @GET(Constants.GET_CART)
    Observable<BaseResponse<List<CartItemEntity>>> getCarts(@Query("user_id") int userId);

    @GET(Constants.GET_ORDER)
    Observable<BaseResponse<PageDTO<OrderItemEntity>>> getOrders(@Query("user_id") int userId, @Query("status") int status,
                                                                 @Query("page") int page, @Query("page_count") int page_count);

    @POST
    @FormUrlEncoded
    Observable<BaseResponse<Object>> commitComment(@Field("order_id") int orderId, @Field("user_id") int user_id,
                                                   @Field("content") String content);
}
