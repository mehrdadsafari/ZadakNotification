package ir.zadak.zadaknotify.pendingintent;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import ir.zadak.zadaknotify.constants.BroadcastActions;
import ir.zadak.zadaknotify.interfaces.PendingIntentNotification;
import ir.zadak.zadaknotify.notification.PugNotification;


public class ClickPendingIntentActivity implements PendingIntentNotification {
    private final Class<?> mActivity;
    private final Bundle mBundle;
    private final int mIdentifier;

    public ClickPendingIntentActivity(Class<?> activity, Bundle bundle, int identifier) {
        this.mActivity = activity;
        this.mBundle = bundle;
        this.mIdentifier = identifier;
    }

    @Override
    public PendingIntent onSettingPendingIntent() {
        Intent clickIntentActivity = new Intent(PugNotification.mSingleton.mContext, mActivity);
        clickIntentActivity.setAction(BroadcastActions.ACTION_PUGNOTIFICATION_CLICK_INTENT);
        clickIntentActivity.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        clickIntentActivity.setPackage(PugNotification.mSingleton.mContext.getPackageName());

        if (mBundle != null) {
            clickIntentActivity.putExtras(mBundle);
        }
        return PendingIntent.getActivity(PugNotification.mSingleton.mContext, mIdentifier, clickIntentActivity,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
