package com.yuplo.support.Internet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private static NetworkListener nlistener;

    public static void setListener(NetworkListener listener) {
        nlistener = listener;
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {

        boolean status = NetworkUtil.getConnectivityStatusString(context);
        if (nlistener != null) {
            nlistener.onNetworkChange(status);
        }
    }

    public interface NetworkListener {
        void onNetworkChange(boolean status);
    }
}