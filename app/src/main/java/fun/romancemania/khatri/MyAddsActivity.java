package fun.romancemania.khatri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

public class MyAddsActivity extends AppCompatActivity {
    private final String TAG = "HTAG";
    private Toolbar toolbar;
    private ImageView movieImage;
    private ImageView imgVideoPlay;
    private ProgressDialog pDialog;
    private InterstitialAd mInterstitial;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_adds);

        toolbar = findViewById(R.id.toolbar_myAdds);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        String name = intent.getStringExtra("name");
        final String videoUrl = intent.getStringExtra("videoUrl");
        Log.d(TAG, imageUrl);
        Log.d(TAG, "onCreate: 11 " + name);
        
        toolbar.setTitle(name);
        imgVideoPlay = findViewById(R.id.videoPlay);
        movieImage = findViewById(R.id.movieImage);
        Picasso.with(this).load(imageUrl).fit().centerCrop()
                .placeholder(R.drawable.home_bg)
                .error(R.drawable.ic_play)
                .into(movieImage);
        
        imgVideoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(videoUrl);
            }
        });
        
        movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(videoUrl);
            }
        });


        mAdView = (AdView) findViewById(R.id.adViewUrlMovieScreen);
        mAdView.loadAd(new AdRequest.Builder().build());
        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
            }

            @Override
            public void onAdFailedToLoad(int error) {
                mAdView.setVisibility(View.GONE);
            }

            @Override
            public void onAdLeftApplication() {
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdLoaded() {
                mAdView.setVisibility(View.VISIBLE);
            }
        });

    }

    private void playVideo (final String videoUrl) {

        LoadingDialog();
        mInterstitial = new InterstitialAd(this);
        mInterstitial.setAdUnitId(this.getResources().getString(R.string.admob_interstitial_id));
        mInterstitial.loadAd(new AdRequest.Builder().build());

        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // TODO Auto-generated method stub
                super.onAdLoaded();
                pDialog.dismiss();
                if (mInterstitial.isLoaded()) {
                    mInterstitial.show();
                }
            }

            public void onAdClosed() {
                startPlayingVideo(videoUrl);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                pDialog.dismiss();
                startPlayingVideo(videoUrl);
            }

        });
    }

    private void startPlayingVideo (final String videoUrl) {
        this.startActivity(new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(videoUrl)));
    }

    public void LoadingDialog() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(this.getResources().getString(R.string.loading));
        pDialog.setCancelable(false);
        pDialog.show();

    }


    @Override   // to handle click back button to go for previous screen
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}