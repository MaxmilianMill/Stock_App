package com.example.stock_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.stock_app.database.RoomDB;
import com.example.stock_app.database.User;

import java.util.List;

public class LoginTabFragment extends Fragment {

    //String TAG = "LOGIN";

    EditText email;
    EditText password;
    Button login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.etEmail);
        password = root.findViewById(R.id.etPassword);

        //Button Funktion Login
        login = root.findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userCheck(email.getText().toString(), password.getText().toString());

                Intent intent = new Intent (getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });
        return root;
    }

    public void userCheck (String email, String password){

        RoomDB db = RoomDB.getDbInstance(this.requireActivity().getApplicationContext());
        List<User> UserList = db.userDao().selectFromEmail(email);
        String Email = UserList.get(0).email;

        //Log.d(TAG, "Logged in!");
    }


}
