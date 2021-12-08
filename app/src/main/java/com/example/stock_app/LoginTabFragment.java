package com.example.stock_app;

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



            }
        });
        return root;
    }

    public void userCheck (String email, String password){

        if (isConnectedToInternet()) {
            RoomDB db = RoomDB.getDbInstance(this.requireActivity().getApplicationContext());
            List<User> UserList = db.userDao().selectFromEmail(email);
            String Email = UserList.get(0).email;

            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);

            //Log.d(TAG, "Logged in!");
        }
        else {
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
