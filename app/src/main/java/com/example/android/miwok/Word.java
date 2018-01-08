package com.example.android.miwok;

import android.media.MediaPlayer;

/**
 * Created by Manbir Marwah on 22-07-2017.
 */

public class Word {

    private String mDefaultTranslation;

    private String mMiwokTranslation;

    private int mImageResourceId = NO_IMAGE;

    private int mAudioResourceId;

    public static final int NO_IMAGE = -1;

    public Word(){

    }

    public Word(String defaultTranslation, String miwokTranslation, int AudioResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = AudioResourceId;
    }


    public Word(String defaultTranslation, String miwokTranslation, int ImageResourceId, int AudioResourceId){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = ImageResourceId;
        mAudioResourceId = AudioResourceId;
    }


    public String getDefaultTranslation(){

        return mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;

    }

//    @Override
//    public String toString() {
//        return "Word{" +
//                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
//                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
//                ", mImageResourceId=" + mImageResourceId +
//                ", mAudioResourceId=" + mAudioResourceId +
//                '}';
//    }
}
