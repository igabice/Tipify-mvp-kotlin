package com.digitalexplorers.tipify;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;
import com.digitalexplorers.tipify.App.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AhoyOnboarderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_intro);
        if (MyApplication.getInstance().getTinyDB().getBoolean("intro")) {
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
        } else{
        AhoyOnboarderCard ahoyOnboarderCard1 = new AhoyOnboarderCard("Link Cards", "Sign up for free by activating your credit cards.", R.drawable.spend);
        AhoyOnboarderCard ahoyOnboarderCard2 = new AhoyOnboarderCard("Dine Out", "Choose from 100's of restaurants with new spots added daily.", R.drawable.food);
        AhoyOnboarderCard ahoyOnboarderCard3 = new AhoyOnboarderCard("Get Cashback", "Tip your favourite waiters each time you dine with linked cards in network.", R.drawable.reward);

        ahoyOnboarderCard1.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard2.setBackgroundColor(R.color.black_transparent);
        ahoyOnboarderCard3.setBackgroundColor(R.color.black_transparent);

        List<AhoyOnboarderCard> pages = new ArrayList<>();

        pages.add(ahoyOnboarderCard1);
        pages.add(ahoyOnboarderCard2);
        pages.add(ahoyOnboarderCard3);

        for (AhoyOnboarderCard page : pages) {
            page.setTitleColor(R.color.white);
            page.setDescriptionColor(R.color.grey_200);
            //page.setTitleTextSize(dpToPixels(12, this));
            //page.setDescriptionTextSize(dpToPixels(8, this));
            //page.setIconLayoutParams(width, height, marginTop, marginLeft, marginRight, marginBottom);
        }

        setFinishButtonTitle("Finish");
        showNavigationControls(true);
        setGradientBackground();

        //set the button style you created
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.rounded_button));
        }

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        setFont(face);

        setOnboardPages(pages);
        }
        MyApplication.getInstance().getTinyDB().putBoolean("intro", true);
    }

    @Override
    public void onFinishButtonPressed() {
        MyApplication.getInstance().getTinyDB().putBoolean("intro", true);
        startActivity(new Intent(IntroActivity.this, MainActivity.class));
    }
}
