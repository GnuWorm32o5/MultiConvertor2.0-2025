package com.example.multiconvertor20_2025;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import androidx.activity.OnBackPressedCallback;



public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);   // dukljuci JS ako batre
        webView.setWebViewClient(new WebViewClient());  // kljuc svega, keeps nav inside app logicno
    }



    public void openLink(View view) {
        String url = "";

        if (view.getId() == R.id.button0) {
            url = "https://www.youtube.com";
        } else if (view.getId() == R.id.button1) {
            url = "https://ytmp3.as/AOPR/";
        } else if (view.getId() == R.id.button2) {
            url = "https://www.online-convert.com/";
        } else if (view.getId() == R.id.button3) {
            url = "https://www.freeconvert.com/";
        } else if (view.getId() == R.id.bottomtext2) {
            url = "https://stivsworld.unaux.com/index.html";
        }

        if (!url.isEmpty()) {
            webView.loadUrl(url); // opens the link inside the app WebView
            webView.setVisibility(view.VISIBLE);
            webView.bringToFront();
            webView.loadUrl(url);

            Button backBtn = findViewById(R.id.backbutton);
            backBtn.setVisibility(View.VISIBLE);
            backBtn.bringToFront();
        }
    }


    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }




}