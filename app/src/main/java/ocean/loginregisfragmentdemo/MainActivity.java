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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding activityMainBinding;
    private FragmentLoginBinding loginFragmentBinding;
    private FragmentRegisBinding regisFragmentBinding;
    private FragmentWelcomeBinding welcomeBinding;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String userId, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.tvRegisLink.setOnClickListener(this);
        activityMainBinding.btnGoToLoginFragment.setOnClickListener(this);

        replaceMainFrameToLoginFrag(new LoginFragment());

        sharedPreferences = getSharedPreferences("login_register", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGoToLoginFragment:
//                activityMainBinding.tvRegisLink.setVisibility(View.VISIBLE);
//                replaceMainFrameToLoginFrag(new LoginFragment());
//                activityMainBinding.btnGoToLoginFragment.setVisibility(View.GONE);
//                activityMainBinding.tvWelcomeNote.setVisibility(View.GONE);
                break;
            case R.id.tvRegisLink:
                activityMainBinding.tvRegisLink.setVisibility(View.GONE);
                replaceMainFrameToRegisFrag(new RegisFragment());
                break;

            case R.id.btnRegister:

                String conPassword = regisFragmentBinding.etRegisConPswd.getText().toString();

                if (password != conPassword){
                    Toast.makeText(this, "Confirm password doesn't match", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(userId)){
                    regisFragmentBinding.etRegisId.setError("PLEASE ENTER USER ID");
                    regisFragmentBinding.etRegisId.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    regisFragmentBinding.etRegisPswd.setError("PLEASE ENTER PASSWORD");
                    regisFragmentBinding.etRegisPswd.requestFocus();
                    return;
                }
                if(TextUtils.isEmpty(conPassword)){
                    regisFragmentBinding.etRegisConPswd.setError("PLEASE ENTER CONFIRMED PASSWORD");
                    regisFragmentBinding.etRegisConPswd.requestFocus();
                    return;
                }
                if (userId != null && password != null && conPassword != null){
                    regisThroughSharePref();
                }

                Log.d("Getting UserId and Password : ", "onClick: case R.id.btnRegister---->" + userId + "---->" + password);

                break;
        }
    }

    private void regisThroughSharePref() {

        editor.putString(Keys.USERID, regisFragmentBinding.etRegisId.getText().toString());
        editor.putString(Keys.PASSWORD, regisFragmentBinding.etRegisPswd.getText().toString());
        editor.apply();

        userId = sharedPreferences.getString(Keys.USERID,"");
        password = sharedPreferences.getString(Keys.PASSWORD, "");

        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
        replaceMainFrameToLoginFrag(new LoginFragment());

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
        fragTransaction.add(R.id.fragment_container_main_activity, loginFragment);
        // save the changes
        fragTransaction.commit();
    }

}