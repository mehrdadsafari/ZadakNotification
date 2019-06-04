package ir.zadak.zadaknotify.interfaces;

public interface ImageLoader {
    void load(String uri, OnImageLoadingCompleted onCompleted);

    void load(int imageResId, OnImageLoadingCompleted onCompleted);
}
