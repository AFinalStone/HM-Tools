package com.hm.iou.tools;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;

/**
 * Created by SHI on 2016/9/10 15:36
 */
public class ImageLoader {

    private static ImageLoader INSTANCE;

    private Picasso picasso;

    public synchronized static ImageLoader getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ImageLoader(context.getApplicationContext());
        }
        return INSTANCE;
    }

    private ImageLoader(Context context) {
        initImageLoader(context);
    }

    private void initImageLoader(Context context) {
        picasso = new Picasso.Builder(context).build();
    }

    public void displayImage(String imageUrl, ImageView imageView) {
        if (!TextUtils.isEmpty(imageUrl)) {
            picasso.load(imageUrl).into(imageView);
        }
    }

    public void displayImage(String imageUrl, ImageView imageView, int placeholderResId, int errorResId) {
        if (!TextUtils.isEmpty(imageUrl)) {
            picasso.load(imageUrl).placeholder(placeholderResId).error(errorResId).into(imageView);
        }
    }

    public void displayImage(String imageUrl, ImageView imageView, int errorResId) {
        if (!TextUtils.isEmpty(imageUrl)) {
            picasso.load(imageUrl).error(errorResId).into(imageView);
        }
    }

    public void displayImage(String imageUrl, ImageView imageView, int errorResId, Callback callback) {
        picasso.load(imageUrl).error(errorResId).into(imageView, callback);
    }

    public void displayImage(String imageUrl, ImageView imageView, int errorResId, Transformation transformation) {
        picasso.load(imageUrl).transform(transformation).error(errorResId).into(imageView);
    }

    public void displayImage(String imageUrl, ImageView imageView, int placeholderResId, int errorResId, Callback callback) {
        if (!TextUtils.isEmpty(imageUrl)) {
            picasso.load(imageUrl).placeholder(placeholderResId).error(errorResId).into(imageView, callback);
        }
    }

    public void displayImage(String imageUrl, ImageView imageView, int placeholderResId, int errorResId, Transformation transformation) {
        if (!TextUtils.isEmpty(imageUrl)) {
            picasso.load(imageUrl).transform(transformation).placeholder(placeholderResId).error(errorResId).into(imageView);
        }
    }

    public void displayImage(Uri imageUrl, ImageView imageView) {
        picasso.load(imageUrl).into(imageView);
    }

    public void displayImage(Uri imageUrl, ImageView imageView, int placeholderResId, int errorResId) {
        picasso.load(imageUrl).placeholder(placeholderResId).error(errorResId).into(imageView);
    }

    public void displayImage(Uri imageUrl, ImageView imageView, int errorResId) {
        picasso.load(imageUrl).error(errorResId).into(imageView);
    }

    public void displayImage(Uri imageUrl, ImageView imageView, int errorResId, Callback callback) {
        picasso.load(imageUrl).error(errorResId).into(imageView, callback);
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

    /**
     * 把res下面的资源文件id转换成对应的uri
     * res/drawable(mipmap)/xxx.png::::uri－－－－>url
     *
     * @param context
     * @param resourceId
     * @return android.resource://com.hm.iou.demo/2131427368
     */
    public Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + context.getPackageName()
                + "/" + resourceId);
    }

    /**
     * 把res下面的资源文件名称转换成对应的uri
     * res/drawable(mipmap)/xxx.png::::uri－－－－>url
     *
     * @return android.resource://com.hm.iou.demo/mipmap/jietiao_ic_home_top_search
     */
    public Uri resourceNameToUri(Context context, int resId) {

        Resources r = context.getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId));

        return uri;
    }

    public void fetchImage(String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            picasso.load(imageUrl).fetch();
        }
    }

    public void fetchImage(String imageUrl, Callback callback) {
        if (!TextUtils.isEmpty(imageUrl)) {
            picasso.load(imageUrl).fetch(callback);
        }
    }
}
