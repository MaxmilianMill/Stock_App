package com.example.stock_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
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

    EditText firstname;
    EditText lastname;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button signUp;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.sign_up_tab_fragment, container, false);

        firstname = root.findViewById(R.id.etFirstName);
        lastname = root.findViewById(R.id.etLastName);
        email = root.findViewById(R.id.etEmail);
        password = root.findViewById(R.id.etPassword);
        confirmPassword = root.findViewById(R.id.etConfirmPassword);

        //Button Funktion SignUp
        signUp = root.findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //create User
                createUser(firstname.getText().toString(), lastname.getText().toString(), email.getText().toString(), password.getText().toString(), confirmPassword.getText().toString());

            }
        });
        return root;
    }

    public void createUser(String firstname, String lastname, String email, String password, String confirmPassword) {

        if (isConnectedToInternet()) {
            RoomDB db = RoomDB.getDbInstance(this.requireActivity().getApplicationContext());

            //Check fields
            if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {

                Toast.makeText(getActivity(), "Please fill all Fields!", Toast.LENGTH_SHORT).show();
            }
            //Check if Password = ConfirmPassword
            else if (!password.equals(confirmPassword)) {

                Toast.makeText(getActivity(), "Passwords are not the same!", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User();
                user.email = email;
                user.password = password;
                user.firstName = firstname;
                user.lastName = lastname;

                db.userDao().insertUser(user);

                Toast.makeText(getActivity(), "User created", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "User created!");

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        } else {
            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
        }
    }

    public boolean isConnectedToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

}
