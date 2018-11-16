package com.chao.mvvm.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.chao.mvvm.util.TUtil;

/**
 * Date: 2018/11/16 12:41
 * Author: hansyang
 * Description:
 */
public class AbsViewModel<T extends AbsRepository> extends AndroidViewModel {

    public T mRepository;

    public AbsViewModel(@NonNull Application application) {
        super(application);
        mRepository = TUtil.getNewInstance(this,0);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mRepository != null){
            mRepository.unDisposable();
        }
    }
}
