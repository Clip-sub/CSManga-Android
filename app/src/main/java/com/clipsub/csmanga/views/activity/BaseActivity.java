package com.clipsub.csmanga.views.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * Base activity for other material design oriented activities.
 */
public class BaseActivity extends AppCompatActivity {
    // Workaround fix for LG devices with support library:

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == KeyEvent.KEYCODE_MENU && "LGE".equalsIgnoreCase(Build.BRAND) || super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && "LGE".equalsIgnoreCase(Build.BRAND)) {
            // Open the option menu for users to remap themselves.
            openOptionsMenu();
            return true;
        }

        return super.onKeyUp(keyCode, event);
    }
}
