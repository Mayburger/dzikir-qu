package com.nacoda.dzikirqu.base;


/**
 * Created by Mayburger on 02/02/2018.
 */

public class BasePresenter<V> {

    public V mvpView;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    public void detachView() {
        this.mvpView = null;
    }
}