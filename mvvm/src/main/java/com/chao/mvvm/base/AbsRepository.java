package com.chao.mvvm.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Date: 2018/11/15 21:53
 * Author: hansyang
 * Description:
 */
public abstract class AbsRepository {

    private CompositeDisposable mCompositeDisposable;

    public AbsRepository(){
    }

    protected void addDisposable(Disposable disposable){
        if (mCompositeDisposable == null){
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void unDisposable(){
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed())
            mCompositeDisposable.clear();
    }
}
