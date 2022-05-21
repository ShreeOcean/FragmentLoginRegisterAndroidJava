package ocean.loginregisfragmentdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ocean.loginregisfragmentdemo.databinding.ActivityMainBinding;

public class LoginFragment extends Fragment {

    View view;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private String userId, password;
    TextView tvRegisLink;
    EditText etuserId, etPassword;
    Button btnLogin;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_login, container, false);

        etuserId = view.findViewById(R.id.etLoginId);
        etPassword = view.findViewById(R.id.etLoginPswd);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvRegisLink = view.findViewById(R.id.tvRegisLink);

        sharedPreferences = getContext().getSharedPreferences("REGISLOGINFRAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String _userId = sharedPreferences.getString("userId","");
        String _password = sharedPreferences.getString("password","");

        tvRegisLink.setOnClickListener(view1 -> {
            tvRegisLink.setVisibility(View.GONE);
            replaceMainFrameToRegisFrag(new RegisFragment());
        });

        btnLogin.setOnClickListener(view1 -> {
            userId = etuserId.getText().toString();
            password  = etPassword.getText().toString();

            if(userId.equals("")) {

                etuserId.setError("Please Enter UserName");

            }else  if(password.equals("")) {

                etPassword.setError("Please Enter Password");
            }
            else  if(!userId.equals(_userId) || !password.equals(_password)) {

                Toast.makeText(getActivity(), "User Id or Password Doesn't Match !!!", Toast.LENGTH_SHORT).show();//.makeText(getActivity(), getString(R.string.invalid_msg), Toast.LENGTH_SHORT).show();

            } else {

                //TODO: Shared Preference
                editor.putBoolean("isLogin", true);
                editor.commit();
                Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();

                //welcome  fragment
                replaceLoginToWelcomFrag(new WelcomeFragment());
            }
        });

        return view;


    }

    private void replaceMainFrameToRegisFrag(RegisFragment regisFragment) {
        // create a FragmentManager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragTransaction.replace(R.id.fragment_container_main_activity, regisFragment);
        // save the changes
        fragTransaction.commit();
    }

    private void replaceLoginToWelcomFrag(WelcomeFragment welcomeFragment) {

        // create a FragmentManager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragTransaction.replace(R.id.fragment_container_main_activity, welcomeFragment);
        // save the changes
        fragTransaction.commit();
    }
}