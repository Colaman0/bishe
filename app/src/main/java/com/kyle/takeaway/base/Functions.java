package com.kyle.takeaway.base;


import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.functions.Consumer;

/**
 * Create by kyle on 2019/2/25
 * Function :
 */
public class Functions {
    public static Consumer<Throwable> throwables() {
        return throwable -> ToastUtils.showShort(throwable.getMessage());
    }

    public static Consumer empty() {
        return o -> {
        };
    }
}
