package ocean.loginregisfragmentdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ocean.loginregisfragmentdemo.databinding.ActivityMainBinding;
import ocean.loginregisfragmentdemo.databinding.FragmentRegisBinding;

public class WelcomeFragment extends Fragment {

    View view;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView tvUserId, tvregislink;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_welcome, container, false);

        tvUserId = view.findViewById(R.id.tvHelloUserName);
        //tvregislink = view.findViewById(R.id.tvRegisLink);

        sharedPreferences = getContext().getSharedPreferences("REGISLOGINFRAG", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String _userId = sharedPreferences.getString("userId","");

        if (_userId != null){
            tvUserId.setText(_userId);
            //tvregislink.setVisibility(View.GONE);

        }
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_logout:

//                SharedPreferences preferences = getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putBoolean("isLogin",false);
//                editor.apply();
//                editor.clear();
//                editor.commit();
                sharedPreferences.edit().putBoolean("isLogin", false).apply();

                replaceWelcomeToLoginFrag(new LoginFragment());

                //TODO: log out is to be done as it is not working
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceWelcomeToLoginFrag(LoginFragment loginFragment) {

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