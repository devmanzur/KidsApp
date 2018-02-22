package com.noushad.kidsapp.model;

import android.support.annotation.NonNull;

import com.noushad.kidsapp.data_provider.AbstractDataProvider;

/**
 * Created by noushad on 2/22/18.
 */

public class Alphabet extends AbstractDataProvider.Data implements Comparable<Alphabet> {

    private  long mId;
    private  String mText;
    private  int mViewType;
    private boolean mPinned;

    public Alphabet(long id, int viewType, String text, int swipeReaction) {
        mId = id;
        mViewType = viewType;
        mText = text;
    }

    @Override
    public boolean isSectionHeader() {
        return false;
    }

    @Override
    public int getViewType() {
        return mViewType;
    }

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public String toString() {
        return mText;
    }

    @Override
    public String getText() {
        return mText;
    }

    @Override
    public boolean isPinned() {
        return mPinned;
    }

    @Override
    public void setPinned(boolean pinned) {
        mPinned = pinned;
    }

    @Override
    public int compareTo(@NonNull Alphabet alphabet) {
        return (int) (this.getId()-alphabet.getId());
    }
}
