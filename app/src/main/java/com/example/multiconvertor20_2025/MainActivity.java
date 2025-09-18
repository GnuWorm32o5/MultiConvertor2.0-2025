package com.example.multiconvertor20_2025;
import androidx.activity.OnBackPressedCallback;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    WebView webView;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        backBtn = findViewById(R.id.backbutton);

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

            backBtn.setVisibility(View.VISIBLE);
            backBtn.bringToFront();
        }
    }


    public void goBack(View view) {
     webView.setVisibility(View.GONE);
     backBtn.setVisibility(View.GONE);
    }


}