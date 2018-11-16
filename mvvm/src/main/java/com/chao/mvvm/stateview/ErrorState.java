package com.chao.mvvm.stateview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chao.mvvm.R;
import com.tqzhang.stateview.stateview.BaseStateControl;

import static com.chao.mvvm.stateview.StateConstants.ERROR_STATE;
import static com.chao.mvvm.stateview.StateConstants.NET_WORK_STATE;

/**
 * Date: 2018/11/15 16:31
 * Author: hansyang
 * Description:
 */
public class ErrorState extends BaseStateControl {
    @Override
    protected int onCreateView() {
        return R.layout.common_empty_view;
    }

    @Override
    protected void onViewCreate(Context context, View view) {
        TextView errorDesc = view.findViewById(R.id.tv_error_desc);
        ImageView errorIcon = view.findViewById(R.id.iv_error_icon);
        if (view.getTag() != null) {
            if (view.getTag().equals(NET_WORK_STATE)) {
                errorDesc.setText(context.getString(R.string.network_is_bad));
                errorIcon.setImageResource(R.mipmap.empty_network);
            } else if (view.getTag().equals(ERROR_STATE)) {
                errorDesc.setText(context.getString(R.string.server_error));
                errorIcon.setImageResource(R.mipmap.empty_server);
            }

        }
    }

    @Override
    public boolean isVisible() {
        return super.isVisible();
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return false;
    }
}
