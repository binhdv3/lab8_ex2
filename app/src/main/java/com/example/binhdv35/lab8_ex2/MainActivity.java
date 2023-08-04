package com.example.binhdv35.lab8_ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.binhdv35.lab8_ex2.Fragment.HomeFragment;
import com.example.binhdv35.lab8_ex2.Fragment.LoginSignUpFragment;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {



    private FirebaseAuth fAuth;
    private Fragment mContent;
    public final static String FRAGMENT_TAG = "MyFragment";
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();
//firebase messaging instance
        FirebaseMessaging fMsg = FirebaseMessaging.getInstance();
//firebase instance to subscribe topics
        fMsg.subscribeToTopic("todos");
//firebase analytics instance
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
                mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
        mFirebaseAnalytics.setSessionTimeoutDuration(600000);
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();

            if (fAuth.getCurrentUser() != null) {
                mContent = HomeFragment.newInstance();
                bundle.putString(FirebaseAnalytics.Param.DESTINATION, "HomeFragment");
            } else {
                mContent = LoginSignUpFragment.newInstance();
                bundle.putString(FirebaseAnalytics.Param.DESTINATION, "LoginFragment");
            }
            FragmentManager fm = getSupportFragmentManager();

            bundle.putString(FirebaseAnalytics.Param.ORIGIN, "MainActivity");
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,
                            bundle);
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.content, mContent, FRAGMENT_TAG);
            ft.commit();
        } else {
            mContent = getSupportFragmentManager().getFragment(
                    savedInstanceState, FRAGMENT_TAG);
        }

    }

    public void onSaveInstanceState(Bundle outState, PersistableBundle
            outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mContent =
                getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        if (mContent != null)
            getSupportFragmentManager().putFragment(outState, FRAGMENT_TAG,
                    mContent);
    }
}