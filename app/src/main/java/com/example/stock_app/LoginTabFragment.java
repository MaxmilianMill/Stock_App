package com.example.stock_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.stock_app.database.RoomDB;
import com.example.stock_app.database.User;

import java.util.List;

public class LoginTabFragment extends Fragment {

    //String TAG = "LOGIN";

    EditText email;
    EditText password;
    Button login;
    
    PassUserData dataPasser;

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (PassUserData) context;
    }

    public void userCheck (String email, String password){

        if (isConnectedToInternet()) {

            //connect to DB
            RoomDB db = RoomDB.getDbInstance(this.requireActivity().getApplicationContext());

            List<User> userList = db.userDao().getAllUsers();
            boolean UserCheck = false;
            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(getActivity(), "Please fill all Fields!", Toast.LENGTH_SHORT).show();
            } else {
                for (User u : userList) {
                    System.out.println(u.email);

                    if (!u.email.equals(email)) {
                        Toast.makeText(getActivity(), "Geben Sie ein bestehenden Nutzer ein!", Toast.LENGTH_SHORT).show();
                        UserCheck = false;
                    } else {
                        UserCheck = true;
                        List<User> UserList = db.userDao().selectFromEmail(email);
                        String Email = UserList.get(0).email;
                        int id = UserList.get(0).id;

                        dataPasser.passUserData(UserList.get(0).firstName,
                                    UserList.get(0).lastName,
                                    UserList.get(0).email,
                                    UserList.get(0).id);

                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                }
            }
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

interface PassUserData {
    
    public void passUserData(String firstName, String lastName, String email, int id);
}
