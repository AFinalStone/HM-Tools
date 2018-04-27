package com.hm.iou.tools;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

/**
 * Created by SHI on 2016/9/10 15:36
 */
public class ImageLoaderUtil {

    private static ImageLoaderUtil imageLoaderUtil;

    //为了加载本地drawable和mipmap目录下面的资源
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private Context context;
    private Picasso picasso;

    public synchronized static ImageLoaderUtil getInstance(Context context) {
        if (imageLoaderUtil == null) {
            imageLoaderUtil = new ImageLoaderUtil(context);
        }
        return imageLoaderUtil;
    }

    public ImageLoaderUtil(Context context) {
        this.context = context.getApplicationContext();
        initImageLoader();
    }

    private void initImageLoader() {
        picasso = Picasso.with(context);
    }

    public void displayImage(String imageUrl, ImageView imageView) {
        if (!StringUtil.isEmpty(imageUrl)) {
            picasso.load(imageUrl).into(imageView);
        }
    }

    public void displayImage(String imageUrl, ImageView imageView, int placeholderResId, int errorResId) {
        if (!StringUtil.isEmpty(imageUrl)) {
            picasso.load(imageUrl).placeholder(placeholderResId).error(errorResId).into(imageView);
        }
    }

    public void displayImage(String imageUrl, ImageView imageView, int placeholderResId, int errorResId, Callback callback) {
        if (!StringUtil.isEmpty(imageUrl)) {
            picasso.load(imageUrl).placeholder(placeholderResId).error(errorResId).into(imageView, callback);
        }
    }

    public void displayImage(String imageUrl, ImageView imageView, int placeholderResId, int errorResId, Transformation transformation) {
        if (!StringUtil.isEmpty(imageUrl)) {
            picasso.load(imageUrl).transform(transformation).placeholder(placeholderResId).error(errorResId).into(imageView);
        }
    }

    public void displayImage(Uri imageUrl, ImageView imageView) {
        picasso.load(imageUrl).into(imageView);
    }
    public void displayImage(Uri imageUrl, ImageView imageView, int placeholderResId, int errorResId) {
        picasso.load(imageUrl).placeholder(placeholderResId).error(errorResId).into(imageView);
    }

    public void displayImage(File file, ImageView imageView) {
        picasso.load(file).into(imageView);
    }

    public void displayImage(File file, ImageView imageView, int placeholderResId, int errorResId) {
        picasso.load(file).placeholder(placeholderResId).error(errorResId).into(imageView);
    }

    public void displayImage(int resId, ImageView imageView) {
        picasso.load(resId).into(imageView);
    }

    public void displayImage(int resId, ImageView imageView, int placeholderResId, int errorResId) {
        picasso.load(resId).placeholder(placeholderResId).error(errorResId).into(imageView);
    }

    private Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName()
                + FOREWARD_SLASH + resourceId);
    }

    public void fetchImage(String imageUrl) {
        if (!StringUtil.isEmpty(imageUrl)) {
            picasso.load(imageUrl).fetch();
        }
    }

    public void fetchImage(String imageUrl, Callback callback) {
        if (!StringUtil.isEmpty(imageUrl)) {
            picasso.load(imageUrl).fetch(callback);
        }
    }
}
