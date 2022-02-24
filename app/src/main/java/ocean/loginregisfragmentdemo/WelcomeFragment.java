package ocean.loginregisfragmentdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
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

        return view;


    }
}