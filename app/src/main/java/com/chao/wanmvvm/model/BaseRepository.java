package com.chao.wanmvvm.model;

import com.chao.mvvm.base.AbsRepository;
import com.chao.mvvm.event.LiveBus;
import com.chao.mvvm.http.HttpHelper;
import com.chao.wanmvvm.network.ApiService;

/**
 * @author：tqzhang on 18/7/26 16:15
 */
public class BaseRepository extends AbsRepository {

    protected ApiService apiService;

    public BaseRepository() {
        if (null == apiService) {
            apiService = HttpHelper.getInstance().create(ApiService.class);
        }
    }


    protected void sendData(Object eventKey, Object t) {
        sendData(eventKey, null, t);
    }

    protected void sendData(Object eventKey, String tag, Object t) {
        LiveBus.getDefault().postEvent(eventKey, tag, t);
    }

    protected void showPageState(Object eventKey, String state) {
        sendData(eventKey, state);
    }

    protected void showPageState(Object eventKey, String tag, String state) {
        sendData(eventKey, tag, state);
    }


}
