package pk.getsub.latahitsongs.videoplay;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;
import pk.getsub.latahitsongs.R;

public class YoutubePlay extends YouTubeFailureRecoveryActivity {

	private String id;
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		setContentView(R.layout.youtube);
		Bundle b = getIntent().getExtras();
		id = (String) b.get("id");
		Log.e("id", id);
		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		youTubeView.initialize(getString(R.string.youtube_api_key), this);


	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
		// TODO Auto-generated method stub
		if (!wasRestored) {
			player.loadVideo(id);
		}
	}

	@Override
	protected Provider getYouTubePlayerProvider() {
		// TODO Auto-generated method stub
		return (YouTubePlayerView) findViewById(R.id.youtube_view);
	}
}