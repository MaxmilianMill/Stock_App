package com.example.stock_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.stock_app.database.RoomDB;
import com.example.stock_app.database.User;

import java.util.List;
import java.util.Objects;

public class SignupTabFragment extends Fragment {

    String TAG = "SIGNUP";

    EditText email;
    EditText password;
    EditText confirmPassword;
    Button signUp;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_tab_fragment, container, false);

        email = root.findViewById(R.id.etEmail);
        password = root.findViewById(R.id.etPassword);
        confirmPassword = root.findViewById(R.id.etConfirmPassword);

        //Button Funktion SignUp
        signUp = root.findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!password.getText().toString().equals(confirmPassword)){

                    password.setBackground(getResources().getDrawable(R.drawable.border_change_false));
                    confirmPassword.setBackground(getResources().getDrawable(R.drawable.border_change_false));
                    //Toast.makeText(SignupTabFragment.this, "Passwords are not the same!", Toast.LENGTH_SHORT).show();

                } else {
                    createUser(email.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);

                }
            }
        });
        return root;



    }

    public void createUser(String email, String password, String confirmPassword) {

        RoomDB db = RoomDB.getDbInstance(this.requireActivity().getApplicationContext());

        if (!password.equals(confirmPassword)) {

            System.out.println("Passwords are not the same!");
        }

        User user = new User();
        user.email = email;
        user.password = password;
        user.firstName = "Niklas";
        user.lastName = "Hammer";

        db.userDao().insertUser(user);

        Log.d(TAG, "User created!");
    }
}
