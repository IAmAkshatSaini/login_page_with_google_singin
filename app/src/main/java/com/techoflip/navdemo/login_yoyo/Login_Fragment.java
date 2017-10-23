package com.techoflip.navdemo.login_yoyo;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Akshat on 09-09-2017.
 */

public class Login_Fragment extends Fragment implements View.OnClickListener {
    private static Animation shakeAnimation;
    LinearLayout loginlayout;
    private static View view;
    private static EditText emailid, password,adhar;
    private static Button loginButton;



    public Login_Fragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        setListeners();
        return view;

    }

    private void initViews() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        emailid = (EditText) view.findViewById(R.id.login_emailid);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
         loginlayout=(LinearLayout)view.findViewById(R.id.layoutInput);
        adhar=(EditText) view.findViewById(R.id.adhar_no);



        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        // Setting text selector over textviews
        XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);




        } catch (Exception e) {
        }
    }


    private void setListeners() {
        loginButton.setOnClickListener(this);


    }
        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.loginBtn:
                    checkValidation();
                    break;



            }

        }


    // Check Validation before login
    private void checkValidation() {

                // Get email id and password
        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();
        password.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);



        // to hide

        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getEmailId);






        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            loginlayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "Enter both credentials.");

        }
        // Check if email id is valid or not
        else if (!m.find()){
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");
        loginlayout.startAnimation(shakeAnimation);}

            // Else do login and do your stuff
        else
            Toast.makeText(getActivity(), "Do Login.", Toast.LENGTH_SHORT)
                    .show();




    }

}
