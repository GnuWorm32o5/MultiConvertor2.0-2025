package com.example.multiconvertor20_2025;

import android.Manifest;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import android.webkit.DownloadListener;
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


        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimeType,
                                        long contentLenght) {

                try {
                    //creates a request to download the file at the url
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

                    request.setMimeType(mimeType);
                    // user-agent that some servers need

                    request.addRequestHeader("User-Agent", userAgent);

                    // get notifications
                    request.setNotificationVisibility(
                            DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                    // downloading over wifi and data
                    request.setAllowedNetworkTypes(
                            DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);

                    // guess filename if not specified
                    String fileName = URLUtil.guessFileName(url, contentDisposition, mimeType);
                    // destination for android 10+ and emulator

                    request.setDestinationInExternalFilesDir(
                            MainActivity.this, Environment.DIRECTORY_DOWNLOADS, fileName);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

                    // use system download manager
                    DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                    dm.enqueue(request);

                    Toast.makeText(getApplicationContext(), "Downloading File...", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Donwload failed: ", Toast.LENGTH_LONG).show();
                }
            }
        });


        //runtime permission for android sdk 6.0+
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
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