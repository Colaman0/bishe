package com.kyle.takeaway;

import android.util.Log;

import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.kyle.takeaway.entity.AddressEntity;
import com.kyle.takeaway.entity.CartItemEntity;
import com.kyle.takeaway.entity.CartParam;
import com.kyle.takeaway.entity.CartProductParam;
import com.kyle.takeaway.entity.CommentEntity;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.entity.LoginEntity;
import com.kyle.takeaway.entity.OrderItemEntity;
import com.kyle.takeaway.entity.PageDTO;
import com.kyle.takeaway.entity.ProductEntity;
import com.kyle.takeaway.entity.SellHistoryEntity;
import com.kyle.takeaway.entity.StoreEntity;
import com.kyle.takeaway.entity.StoreInfoEntity;
import com.kyle.takeaway.entity.UserInfoEntity;
import com.kyle.takeaway.util.UserHelper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
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

    public Observable<Optional<LoginEntity>> userLogin(String phone, String psw) {
        return service.login(phone, psw)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Optional<LoginEntity>> storeLogin(String phone, String psw) {
        return service.storeLogin(phone, psw)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable getRegisterCode(String phone, int type) {
        return service.getRegisterCode(phone, type)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable getResetCode(String phone, int type) {
        return service.getResetCode(phone, type)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable register(String nickName, String mobile_phone, String password, String verify_code, int type) {
        return service.register(nickName, mobile_phone, password, verify_code, type)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable resetPsw(String mobile_phone, String password, String verify_code, int type) {
        return service.resetPassword(mobile_phone, password, verify_code, type)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Optional<UserInfoEntity>> getUserInfo(String id) {
        return service.getUserInfo(id)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Optional<UserInfoEntity>> editUserInfo(String avator) {
        return service.editUserInfo(String.valueOf(UserHelper.getId()), UserHelper.getUserInfo().getNickname(), avator)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<AddressEntity>> getAddressList() {
        return service.getAdddress(String.valueOf(UserHelper.getId()), 1, 200)
                .compose(RxResponse.getData())
                .map((Function<Optional<PageDTO<AddressEntity>>, PageDTO<AddressEntity>>) pageDTOOptional -> pageDTOOptional.get())
                .map(listPageDTO -> listPageDTO.getList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable deleteAddress(String id) {
        return service.delete(id)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<Optional<List<AddressEntity>>> addAddress(String content) {
        return service.addAdddress(String.valueOf(UserHelper.getId()), content)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<StoreEntity>> getStores(int type, String key) {
        return service.getStores(type, key, 1, 200)
                .compose(RxResponse.getData())
                .map(pageDTOOptional -> pageDTOOptional.get().getList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<ProductEntity>> getStoreDetail(int id) {
        return service.getStoreDetail(id, 1, 200)
                .compose(RxResponse.getData())
                .map(pageDTOOptional -> pageDTOOptional.get().getList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable changePsw(String old, String newPassword) {
        Observable observable;
        if (UserHelper.getAccountType() == 1) {
            observable = service.userChangePsw(String.valueOf(UserHelper.getId()), old, newPassword);
        } else {
            observable = service.storeChangePsw(String.valueOf(UserHelper.getmStoreId()), old, newPassword);
        }
        return observable
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<String> uploadPic(String filePath) {
        return service.uploadPic(prepareFilePart("description", new File(filePath)), createPartFromString("pic"))
                .compose(RxResponse.getData())
                .map(uploadEntityOptional -> uploadEntityOptional.get() != null ? uploadEntityOptional.get().getUrls().get(0) : "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable addToCart(int id) {
        return service.handleCart(UserHelper.getId(), new Gson().toJson(Arrays.asList(new CartProductParam(id, 1, 1))))
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable editCart(int id, int num) {
        return service.handleCart(UserHelper.getId(), new Gson().toJson(Arrays.asList(new CartProductParam(id, num, 0))))
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<CartItemEntity>> getCarts() {
        return service.getCarts(UserHelper.getId())
                .compose(RxResponse.getData())
                .map(listOptional -> listOptional.get())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<OrderItemEntity>> getOrders() {
        return service.getOrders(UserHelper.getId(), 0, 1, 200)
                .compose(RxResponse.getData())
                .map(pageDTOOptional -> pageDTOOptional.get().getList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<OrderItemEntity>> getStoreOrders() {
        return service.getStoreOrders(UserHelper.getmStoreId(), 0, 1, 200)
                .compose(RxResponse.getData())
                .map(pageDTOOptional -> pageDTOOptional.get().getList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable commitComment(int orderId, String content) {
        return service.commitComment(orderId, UserHelper.getId(), content)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<StoreInfoEntity> getStoreInfo() {
        return service.getStoreInfo(UserHelper.getmStoreId())
                .compose(RxResponse.getData())
                .map(storeInfoEntityOptional -> storeInfoEntityOptional.get())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable editStoreInfo(String avatar, String fee, String name) {
        return service.editStoreInfo(UserHelper.getmStoreId(), avatar, name, fee)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable deleteProduct(int id) {
        return service.deleteProduct(id)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable addProduct(int id, String pic, String decs, String price, String name) {
        return service.addProduct(UserHelper.getmStoreId(), pic, decs, price, name)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable editProduct(int id, String pic, String decs, String price, String name) {
        return service.editProduct(id, pic, decs, price, name)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable sendOrder(int id) {
        return service.sendOrder(id)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<List<SellHistoryEntity>> getSellHistory(int id) {
        return service.getSellHistory(id)
                .compose(RxResponse.getData())
                .map(pageDTOOptional -> pageDTOOptional.get())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<OrderItemEntity>> preCommitOrder(String json) {
        return service.preCommitOrder(UserHelper.getId(),json)
                .compose(RxResponse.getData())
                .map(Optional::get)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable commitOrder(String json,int addressId) {
        return service.commitOrder(UserHelper.getId(),json,addressId)
                .compose(RxResponse.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<CommentEntity>> getComments() {
        return service.getComments(UserHelper.getId(),1,200)
                .compose(RxResponse.getData())
                .map(pageDTOOptional -> pageDTOOptional.get().getList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static RequestBody createPartFromString(String descriptionString) {
        if (descriptionString == null) {
            descriptionString = "";
        }
        return RequestBody.create(
                MediaType.parse(Constants.MULTIPART_FORM_DATA), descriptionString);
    }

    public MultipartBody.Part prepareFilePart(String partName, File file) {
        if (file != null) {
            // 为file建立RequestBody实例
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse(Constants.MULTIPART_FORM_DATA), file);
            // MultipartBody.Part借助文件名完成最终的上传
            return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
        }
        return null;
    }
}