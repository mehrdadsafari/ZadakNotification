package ir.zadak.zadaknotify.pendingintent;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import ir.zadak.zadaknotify.constants.BroadcastActions;
import ir.zadak.zadaknotify.interfaces.PendingIntentNotification;
import ir.zadak.zadaknotify.notification.PugNotification;


public class ClickPendingIntentBroadCast implements PendingIntentNotification {
    private final Bundle mBundle;
    private final int mIdentifier;

    public ClickPendingIntentBroadCast(Bundle bundle, int identifier) {
        this.mBundle = bundle;
        this.mIdentifier = identifier;
    }

    @Override
    public PendingIntent onSettingPendingIntent() {
        Intent clickIntentBroadcast = new Intent(BroadcastActions.ACTION_PUGNOTIFICATION_CLICK_INTENT);
        clickIntentBroadcast.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        clickIntentBroadcast.setPackage(PugNotification.mSingleton.mContext.getPackageName());
        if (mBundle != null) {
            clickIntentBroadcast.putExtras(mBundle);
        }

        return PendingIntent.getBroadcast(PugNotification.mSingleton.mContext, mIdentifier, clickIntentBroadcast,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
