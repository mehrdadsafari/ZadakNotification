# ZadakNotification
# Download
# Step 1:
Add it in your root build.gradle at the end of repositories:
``` groovy
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
		     }
	}
```
# Step 2:
Add the dependency:
```groovy
 implementation 'implementation 'com.github.araditc:ChatCore:0.0.4'
```

# Introduction
initial library:
```
new Core(context , stanzaRecieveCallback);

public class App extends Application implements StanzaReceiveCallback {

    @Override
    public void onCreate() {
        super.onCreate();
        new Core().init(this);
        new Core(this, this); // this ==> Context , this ==> StanzaReceiverCallback
    }

    @Override
    public void onReceiveMessage(String stanza) {
        Log.i("XmppStanza" , stanza);
    }
}
```
# Set Xmpp User
```
Core.setXmpp("Username" , "Password");
```

# ProGuard
```
-dontwarn com.squareup.okhttp.**
```
