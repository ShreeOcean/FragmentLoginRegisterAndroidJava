package ocean.loginregisfragmentdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ocean.loginregisfragmentdemo.databinding.ActivityMainBinding;
import ocean.loginregisfragmentdemo.databinding.FragmentRegisBinding;

public class RegisFragment extends Fragment{

    View view;
    TextView tvLoginFragLink;
    Button btnRegister;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String userId, password, conPassword;
    EditText etRegisId, etPassword, etConPass;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_regis, container, false);

        etRegisId = view.findViewById(R.id.etRegisId);
        etPassword = view.findViewById(R.id.etRegisPswd);
        etConPass = view.findViewById(R.id.etRegisConPswd);
        btnRegister = view.findViewById(R.id.btnRegister);
//        tvLoginFragLink = view.findViewById(R.id.tvGoToLoginFragment);

        sharedPreferences = getContext().getSharedPreferences("REGISLOGINFRAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
//        tvLoginFragLink.setOnClickListener(this);
//        btnRegister.setOnClickListener(this);
//        fragmentRegisBinding = FragmentRegisBinding.inflate(inflater,R.layout.fragment_regis,container,false);
//        view = fragmentRegisBinding.getRoot();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = etRegisId.getText().toString();
                password = etPassword.getText().toString();
                conPassword = etConPass.getText().toString();

                editor.putString("userId",userId);
                editor.putString("password",password);
                editor.putString("conPassword",conPassword);
                editor.putBoolean("isRegister",true);
                editor.commit();

                sharedPreferences.getString("userId","");
                Toast.makeText(getActivity(), "Register Successful", Toast.LENGTH_SHORT).show();
                replaceRegisFragToLoginFrag(new LoginFragment());
            }
        });

        return view;
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.tvGoToLoginFragment:
//                replaceRegisFragToLoginFrag(new LoginFragment());
//                break;
//            case R.id.btnRegister:
//
//                if (password != conPassword){
//                    // Toast.makeText(this, "Confirm password doesn't match", Toast.LENGTH_SHORT).show();
//                    etConPass.setError("Confirm password doesn't match");
//                    etConPass.requestFocus();
//                    return;
//
//                } else if(TextUtils.isEmpty(userId)){
//                    etRegisId.setError("PLEASE ENTER USER ID");
//                    etRegisId.requestFocus();
//                    return;
//                }else if(TextUtils.isEmpty(password)){
//                    etPassword.setError("PLEASE ENTER PASSWORD");
//                    etPassword.requestFocus();
//                    return;
//                }else if(TextUtils.isEmpty(conPassword)){
//                    etConPass.setError("PLEASE ENTER CONFIRMED PASSWORD");
//                    etConPass.requestFocus();
//                    return;
//                }else if (userId != null && password != null && conPassword != null){
//                    regisThroughSharePref();
//                }
//                break;
//        }
//    }
//
//
//    private void regisThroughSharePref() {
//
//
//        sharedPreferences = getActivity().getSharedPreferences("login_register", Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//
//        editor.putString(Keys.USERID, userId);
//        editor.putString(Keys.PASSWORD, password);
//        editor.apply();
//
//        userId = sharedPreferences.getString(Keys.USERID,"");
//        password = sharedPreferences.getString(Keys.PASSWORD, "");
//
//        Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_SHORT).show();
//
//    }

    private void replaceRegisFragToLoginFrag(LoginFragment loginFragment) {

        // create a FragmentManager
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragTransaction = fragmentManager.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragTransaction.replace(R.id.fragment_container_main_activity, loginFragment);
        // save the changes
        fragTransaction.commit();
    }
}