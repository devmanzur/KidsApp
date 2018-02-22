package com.noushad.kidsapp.fragment;

import android.os.Bundle;

import com.noushad.kidsapp.data_provider.AbstractDataProvider;
import com.noushad.kidsapp.data_provider.AlphabetDataProvider;


public class AlphabetDataProviderFragment extends android.support.v4.app.Fragment {

    private AbstractDataProvider mDataProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        mDataProvider = new AlphabetDataProvider();
    }

    public AbstractDataProvider getDataProvider() {
        return mDataProvider;
    }
}
