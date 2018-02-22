package com.noushad.kidsapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.noushad.kidsapp.R;
import com.noushad.kidsapp.data_provider.AbstractDataProvider;
import com.noushad.kidsapp.fragment.AlphabetDataProviderFragment;
import com.noushad.kidsapp.fragment.ReArrangeFragment;

public class ReArrangeActivity extends BaseActivity {

    private static final String FRAGMENT_TAG_DATA_PROVIDER = "data provider";
    private static final String FRAGMENT_RE_ARRANGE = "re arrange";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_arrange);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add( new AlphabetDataProviderFragment(),FRAGMENT_TAG_DATA_PROVIDER)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new ReArrangeFragment(), FRAGMENT_RE_ARRANGE)
                    .commit();
        }

    }

    public AbstractDataProvider getDataProvider() {
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG_DATA_PROVIDER);
        return ((AlphabetDataProviderFragment) fragment).getDataProvider();
    }
}
