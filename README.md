
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ZadakNotification-green.svg?style=flat)](https://android-arsenal.com/details/1/7749)
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
 implementation 'com.github.mehrdadsafari:ZadakNotification:0.0.2'
```

# Introduction

![Screenshots](https://raw.githubusercontent.com/halysongoncalves/pugnotification/master/art/screenshot.png)

You're probably tired of writing code to display notifications in your applications, the library abstracts all the notifications construction process for you in a single line of code. this library support Android Wear.

```java
ZadakNotification.with(context)
    .load()
    .notificationChannelId(CHANNEL_ID)
    .identifier(identifier)
    .title(title)
    .message(message)
    .bigTextStyle(bigtext)
    .smallIcon(smallIcon)
    .largeIcon(largeIcon)
    .flags(Notification.DEFAULT_ALL)
    .button(icon, title, pendingIntent)
    .click(cctivity, bundle)
    .dismiss(activity, bundle)
    .color(color)
    .ticker(ticker)
    .when(when)
    .vibrate(vibrate)
    .lights(color, ledOnMs, ledOfMs)
    .sound(sound) 
    .autoCancel(autoCancel)
    .simple()
    .build();
```
# Simple Notification

Simple notification with just text and message.

```java
ZadakNotification.with(context)
    .load()
    .notificationChannelId(CHANNEL_ID)
    .title(title)
    .message(message)
    .bigTextStyle(bigtext)
    .smallIcon(R.drawable.ic_launcher)
    .largeIcon(R.drawable.ic_launcher)
    .flags(Notification.DEFAULT_ALL)
    .simple()
    .build();
```

# Progress Notification

Simple notification with progress.

```java
ZadakNotification.with(context)
    .load()
    .notificationChannelId(CHANNEL_ID)
    .identifier(identifier)
    .smallIcon(R.drawable.ic_launcher)
    .progress()
    .value(progress,max, indeterminate)
    .build();
```

```java
ZadakNotification.with(context)
    .load()
    .notificationChannelId(CHANNEL_ID)
    .identifier(identifier)
    .smallIcon(R.drawable.ic_launcher)
    .progress()
    .update(identifier,progress,max, indeterminate)
    .build();
```

# Custom Notification

We have changed the way the library handles the download images for custom notifications. Previously disrespectfully because of the Picasso library. But many users were asking for the option of being able to choose how to download the image.
So we serve the requests and modify the library to allow the download of image management as an example:

```java
ZadakNotification.with(context)
    .load()
    .notificationChannelId(CHANNEL_ID)
    .title(title)
    .message(message)
    .bigTextStyle(bigtext)
    .smallIcon(R.drawable.ic_launcher)
    .largeIcon(R.drawable.ic_launcher)
    .flags(Notification.DEFAULT_ALL)
    .color(android.R.color.background_dark)
    .custom()
    .background(url)
    .setImageLoader(Callback)
    .setPlaceholder(R.drawable.ic_placeholder)
    .build();  
```


# Wear Notification

```java
ZadakNotification.with(mContext).load()
    .notificationChannelId(CHANNEL_ID)
    .smallIcon(R.drawable.ic_launcher)
    .autoCancel(true)
    .largeIcon(R.drawable.ic_launcher)
    .title(title)
    .message(message)
    .bigTextStyle(bigtext)
    .flags(Notification.DEFAULT_ALL)
    .wear()
    .background(Bitmap)
    .setRemoteInput(icon, title, pendingIntent, remoteInput)
    .setPages(List<Notification> listNotification)
    .setHideIcon(Boolean)
    .build();
```

Now just the client implement the ImageLoader interface and implement a way to manage the download of the image. Below we use the Picasso:

```java
    @Override
    public void load(String uri, final OnImageLoadingCompleted onCompleted) {
        viewTarget = getViewTarget(onCompleted);
        Picasso.with(this).load(uri).into(viewTarget);
    }
    
    private static Target getViewTarget(final OnImageLoadingCompleted onCompleted) {
        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                onCompleted.imageLoadingCompleted(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
    }
```
ZadakNotification supports placeholders if download the image in the background is not successful. The library already have a default placeholder size 622x384.

# Important note
For disable the swipe delete notification use 
```
.setOngoing(true)
```
Cancel Notification
```
ZadakNotification.with(mContext).cancel(identifier);
```
# ProGuard

If you use the Picasso to manage the download of the image, add the line below to your proguard file:

```
-dontwarn com.squareup.okhttp.**
```
