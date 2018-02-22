package com.noushad.kidsapp.data_provider;

import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;
import com.noushad.kidsapp.model.Alphabet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by noushad on 2/22/18.
 */

public class AlphabetDataProvider extends AbstractDataProvider {

    private List<Alphabet> mData;


    public AlphabetDataProvider() {

        final String atoz = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        mData = new ArrayList<>();


        for (int j = 0; j < atoz.length(); j++) {
            final long id = mData.size();
            final int viewType = 0;
            final String text = Character.toString(atoz.charAt(j));
            final int swipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_UP | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_DOWN;
            mData.add(new Alphabet(id, viewType, text, swipeReaction));
        }
        Collections.shuffle(mData);

    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Data getItem(int index) {
        if (index < 0 || index >= getCount()) {
            throw new IndexOutOfBoundsException("index = " + index);
        }

        return mData.get(index);
    }

    @Override
    public void moveItem(int fromPosition, int toPosition) {
        if (fromPosition == toPosition) {
            return;
        }

        final Alphabet item = mData.remove(fromPosition);

        mData.add(toPosition, item);
    }

    @Override
    public void swapItem(int fromPosition, int toPosition) {
        if (fromPosition == toPosition) {
            return;
        }

        Collections.swap(mData, toPosition, fromPosition);
    }




}
