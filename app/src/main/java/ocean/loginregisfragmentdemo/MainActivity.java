package ocean.loginregisfragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import ocean.loginregisfragmentdemo.databinding.ActivityMainBinding;
import ocean.loginregisfragmentdemo.databinding.FragmentLoginBinding;
import ocean.loginregisfragmentdemo.databinding.FragmentRegisBinding;
import ocean.loginregisfragmentdemo.databinding.FragmentWelcomeBinding;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding activityMainBinding;
//    private FragmentLoginBinding loginFragmentBinding;
//    private FragmentRegisBinding regisFragmentBinding;
//    private FragmentWelcomeBinding welcomeBinding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String userId, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());



        sharedPreferences = getSharedPreferences("REGISLOGINFRAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
        if (isLogin){

            replaceMainFrameToWelcomeFrag(new WelcomeFragment());

        }else if (savedInstanceState == null){

            sharedPreferences.edit().putBoolean("isLogin", false).apply();
            editor.clear();
            editor.commit();
            replaceMainFrameToLoginFrag(new LoginFragment());

        }

    }

    private void replaceMainFrameToWelcomeFrag(WelcomeFragment welcomeFragment) {

        // create a FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragTransaction.add(R.id.fragment_container_main_activity, welcomeFragment);
        // save the changes
        fragTransaction.commit();

    }



    private void replaceMainFrameToLoginFrag(LoginFragment loginFragment) {

        // create a FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragTransaction.add(R.id.fragment_container_main_activity, loginFragment);
        // save the changes
        fragTransaction.commit();
    }

}