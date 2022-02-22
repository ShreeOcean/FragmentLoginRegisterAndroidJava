package ocean.loginregisfragmentdemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import ocean.loginregisfragmentdemo.databinding.ActivityMainBinding;
import ocean.loginregisfragmentdemo.databinding.FragmentRegisBinding;

public class RegisFragment extends Fragment implements View.OnClickListener{

    View view;
    TextView tvLoginFragLink;
    Button btnRegister;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@NonNull ViewGroup container,
                             @NonNull Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_regis, container, false);

        tvLoginFragLink = view.findViewById(R.id.tvGoToLoginFragment);
        tvLoginFragLink.setOnClickListener(this);

        return view;

//        fragmentRegisBinding = FragmentRegisBinding.inflate(inflater,R.layout.fragment_regis,container,false);
//        view = fragmentRegisBinding.getRoot();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvGoToLoginFragment:
                replaceRegisFragToLoginFrag(new LoginFragment());
                break;
            case R.id.btnRegister:
                break;
        }
    }

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