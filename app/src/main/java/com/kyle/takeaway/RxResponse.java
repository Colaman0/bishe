package com.kyle.takeaway;

import com.google.common.base.Optional;
import com.kyle.takeaway.entity.BaseResponse;


import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * Create by kyle on 2019/2/23
 * Function :
 */
public class RxResponse {
    public static <T> ObservableTransformer<BaseResponse<T>, Optional<T>> getData() {
        return response -> response.map(tBaseResponse -> {
            if (tBaseResponse == null || tBaseResponse.getCode() != 0) {
                throw new RuntimeException(tBaseResponse.getMsg());
            }
            return Optional.fromNullable(tBaseResponse.getData());
        });
    }
}
