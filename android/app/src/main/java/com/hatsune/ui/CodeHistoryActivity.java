package com.hatsune.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.hatsune.R;
import com.hatsune.fragment.CodeHistoryFragment;

public class CodeHistoryActivity extends AppCompatActivity {
    public static final String CODE_HISTORY_DATA_KEY = "code_history_data_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_container);
        if (savedInstanceState == null) {
            String queryUrl = getIntent().getStringExtra(CODE_HISTORY_DATA_KEY);
            Fragment fragment = CodeHistoryFragment.newInstance(queryUrl);
            if (fragment != null)
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragContainer, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(fragment.getClass().getSimpleName())
                        .commit();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
