package ir.zadak.zadaknotify.notification;

import android.app.NotificationManager;
import android.content.Context;

public class ZadakNotification {
    private static final String TAG = ZadakNotification.class.getSimpleName();
    public static ZadakNotification mSingleton = null;
    public final Context mContext;
    public boolean shutdown;

    public ZadakNotification(Context context) {
        this.mContext = context;
    }

    public static ZadakNotification with(Context context) {
        if (mSingleton == null) {
            synchronized (ZadakNotification.class) {
                if (mSingleton == null) {
                    mSingleton = new Contractor(context).build();
                }
            }
        }
        return mSingleton;
    }

    public Load load() {
        return new Load();
    }

    public void cancel(int identifier) {
        NotificationManager notifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notifyManager.cancel(identifier);
    }

    public void cancel(String tag, int identifier) {
        NotificationManager notifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notifyManager.cancel(tag, identifier);
    }

    public void shutdown() {
        if (this == mSingleton) {
            throw new UnsupportedOperationException("Default singleton instance cannot be shutdown.");
        }
        if (shutdown) {
            return;
        }
        shutdown = true;
    }

    private static class Contractor {
        private final Context mContext;

        public Contractor(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.mContext = context.getApplicationContext();
        }

        public ZadakNotification build() {
            return new ZadakNotification(mContext);
        }
    }
}
