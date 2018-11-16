package com.chao.mvvm.event;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.concurrent.ConcurrentHashMap;
import static com.chao.mvvm.util.TUtil.checkNotNull;

/**
 * Date: 2018/11/15 17:08
 * Author: hansyang
 * Description:
 */
public class LiveBus {

    private static volatile LiveBus sInstance;

    private final ConcurrentHashMap<Object,LiveBusData<Object>> mLiveBus;

    private LiveBus(){
        mLiveBus = new ConcurrentHashMap<>();
    }

    public static LiveBus getDefault(){
        if (sInstance == null){
            synchronized (LiveBus.class){
                if (sInstance == null){
                    sInstance = new LiveBus();
                }
            }
        }
        return sInstance;
    }

    public <T> MutableLiveData<T> subscribe(Object eventKey){
        checkNotNull(eventKey);
        return subscribe(eventKey,"");
    }

    public <T> MutableLiveData<T> subscribe(Object eventKey, String tag) {
        checkNotNull(eventKey);
        return (MutableLiveData<T>) subscribe(eventKey,tag,Object.class);
    }

    public <T> MutableLiveData<T> subscribe(Object eventKey, Class<T> tClass) {
        return subscribe(eventKey, null, tClass);
    }

    public <T> MutableLiveData<T> subscribe(Object eventKey, String tag, Class<T> tClass) {
        checkNotNull(eventKey);
        checkNotNull(tClass);
        String key;
        if (!TextUtils.isEmpty(tag))
            key = eventKey + tag;
        else key = (String) eventKey;

        if (!mLiveBus.containsKey(key))
            mLiveBus.put(key,new LiveBusData<>(true));
        else {
            LiveBusData liveBusData = mLiveBus.get(key);
            liveBusData.isFirstSubscribe = false;
        }

        return (MutableLiveData<T>) mLiveBus.get(key);
    }

    public <T> MutableLiveData<T> postEvent(Object eventKey,T value){
        return postEvent(eventKey,null,value);
    }

    public <T> MutableLiveData<T> postEvent(Object eventKey,String tag,T value){
        checkNotNull(eventKey);
        String key;
        if (!TextUtils.isEmpty(tag))
            key = eventKey + tag;
        else {
            key = (String) eventKey;
        }
        MutableLiveData<T> mutableLiveData = subscribe(key);
        mutableLiveData.postValue(value);
        return mutableLiveData;
    }

    public void clear(Object eventKey){
        clear(eventKey,null);
    }

    private void clear(Object eventKey, String tag) {
        if (mLiveBus != null && mLiveBus.size() > 0){
            String clearKey;
            if (!TextUtils.isEmpty(tag))
                clearKey = eventKey + tag;
            else clearKey = (String) eventKey;
            mLiveBus.remove(clearKey);
        }
    }

    public static class LiveBusData<T> extends MutableLiveData<T> {

        private boolean isFirstSubscribe;

        public LiveBusData(boolean isFirstSubscribe) {
            this.isFirstSubscribe = isFirstSubscribe;
        }

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
            super.observe(owner, new ObserverWrapper<>(observer, isFirstSubscribe));
        }
    }

    private static class ObserverWrapper<T> implements Observer<T> {

        private Observer<T> observer;

        private boolean isChanged;

        private ObserverWrapper(Observer<T> observer, boolean isFirstSubscribe) {
            this.observer = observer;
            isChanged = isFirstSubscribe;
        }

        @Override
        public void onChanged(@Nullable T t) {
            if (isChanged) {
                if (observer != null) {
                    observer.onChanged(t);
                }
            } else {
                isChanged = true;
            }
        }
    }
}
