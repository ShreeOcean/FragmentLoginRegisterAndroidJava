package ocean.loginregisfragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import ocean.loginregisfragmentdemo.databinding.ActivityMainBinding;
import ocean.loginregisfragmentdemo.databinding.FragmentLoginBinding;
import ocean.loginregisfragmentdemo.databinding.FragmentRegisBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding activityMainBinding;
    private FragmentLoginBinding loginFragmentBinding;
    private FragmentRegisBinding regisFragmentBinding;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        context = this;

        activityMainBinding.tvRegisLink.setOnClickListener(this);
        activityMainBinding.btnGoToLoginFragment.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGoToLoginFragment:
                activityMainBinding.tvRegisLink.setVisibility(View.VISIBLE);
                replaceMainFrameToLoginFrag(new LoginFragment());
                activityMainBinding.btnGoToLoginFragment.setVisibility(View.GONE);
                activityMainBinding.tvWelcomeNote.setVisibility(View.GONE);
                break;
            case R.id.tvRegisLink:
                activityMainBinding.tvRegisLink.setVisibility(View.GONE);
                replaceMainFrameToRegisFrag(new RegisFragment());
                break;
        }
    }

    private void replaceMainFrameToRegisFrag(RegisFragment regisFragment) {
        // create a FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragTransaction.replace(R.id.fragment_container_main_activity, regisFragment);
        // save the changes
        fragTransaction.commit();
    }

    private void replaceMainFrameToLoginFrag(LoginFragment loginFragment) {

        // create a FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragTransaction.replace(R.id.fragment_container_main_activity, loginFragment);
        // save the changes
        fragTransaction.commit();
    }

}