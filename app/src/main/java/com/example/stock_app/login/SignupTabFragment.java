package com.example.stock_app.login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.stock_app.R;
import com.example.stock_app.database.RoomDB;
import com.example.stock_app.database.User;
import com.example.stock_app.login.LoginActivity;

import java.util.List;

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
        //if internet is connected
        if (isConnectedToInternet()) {
            //connect to DB
            RoomDB db = RoomDB.getDbInstance(this.requireActivity().getApplicationContext());

            List<User> userList = db.userDao().getAllUsers();

            boolean isAlreadyActive = false;

            for (User u: userList) {
                System.out.println(u.email);

                if (u.email.equals(email)) {
                    Toast.makeText(getActivity(), "Account with email is already active", Toast.LENGTH_SHORT).show();
                    isAlreadyActive = true;
                }
            }

            if (!isAlreadyActive) {

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

                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
          //open internet-settings if the internet is not conected

        } else {
            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
        }
    }

    //check Internet connection
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
