package br.com.appreciador.view;

import java.io.IOException;

import jim.h.common.android.lib.zxing.sample.R;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.androidquery.AQuery;

public class AndroidBuildingMusicPlayerActivity extends RoboActivity implements SeekBar.OnSeekBarChangeListener {
	private  MediaPlayer mp;
	// Handler to update UI timer, progress bar etc,.
	private Handler mHandler = new Handler();;
	private Utilities utils;
	private int seekForwardTime = 5000; // 5000 milliseconds
	private int seekBackwardTime = 5000; // 5000 milliseconds
	private String song; // = "http://mundowebproducoes.com.br/app/wp-content/uploads/2013/06/1-Take-Five.mp3";
	private AQuery aq;
	private String tag = "AudioActivity";
	
	@InjectView(R.id.songProgressBar) SeekBar songProgressBar;
	@InjectView(R.id.songTitle) TextView songTitleLabel;
	@InjectView(R.id.songCurrentDurationLabel) TextView songCurrentDurationLabel;
	@InjectView(R.id.songTotalDurationLabel) TextView songTotalDurationLabel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		
		Intent intent = getIntent();
		if(intent.getExtras() != null){
			Bundle parametro = intent.getExtras();
			if(parametro != null){
				song = parametro.getString("resultado_qrcode").replaceAll("dir", "audio") + ".mp3";	
			}
		}
		
        try {
            mp = new MediaPlayer();
            mp.setDataSource(getApplicationContext(), Uri.parse(song));
            mp.setAudioStreamType(AudioManager.STREAM_RING);
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                	mHandler.removeCallbacks(mUpdateTimeTask);
                    mp.stop();
                    mp.release();
                    finish();
                }
            });
            mp.prepare();
            mp.start();
            
			songProgressBar.setProgress(0);
			songProgressBar.setMax(100);
			updateProgressBar();
        } catch (IOException e) {
            Log.e(tag, "Error playing audio resource", e);
        }
		
		aq = new AQuery(this);
		aq.id(R.id.btnPlay).clicked(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ImageView btnPlay = aq.id(R.id.btnPlay).getImageView();
				if(mp.isPlaying()) {
					mHandler.removeCallbacks(mUpdateTimeTask);
					btnPlay.setImageResource(R.drawable.btn_pause);
					mp.pause();
				} else {
					updateProgressBar();
					btnPlay.setImageResource(R.drawable.btn_play);
					mp.start();
				}
			}
		});		
		
		aq.id(R.id.btnForward).clicked(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int currentPosition = mp.getCurrentPosition();
				if (currentPosition + seekForwardTime <= mp.getDuration()) {
					mp.seekTo(currentPosition + seekForwardTime);
				} else {
					mp.seekTo(mp.getDuration());
				}
			}
		});
		
		aq.id(R.id.btnBackward).clicked(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int currentPosition = mp.getCurrentPosition();
				if(currentPosition - seekBackwardTime >= 0){
					mp.seekTo(currentPosition - seekBackwardTime);
				}else{
					mp.seekTo(0);
				}

			}
		});

		utils = new Utilities();
	}
	
	/**
	 * Update timer on seekbar
	 * */
	public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);        
    }	
	
	/**
	 * Background Runnable thread
	 * */
	private Runnable mUpdateTimeTask = new Runnable() {
		   public void run() {
			   Log.i(tag, "run -> if");
			   if (mp != null && mp.isPlaying()) {
				   Log.i(tag, "run -> getDuration");
				   long totalDuration = mp.getDuration();
				   long currentDuration = mp.getCurrentPosition();
				  
				   // Displaying Total Duration time
				   songTotalDurationLabel.setText(""+utils.milliSecondsToTimer(totalDuration));
				   // Displaying time completed playing
				   songCurrentDurationLabel.setText(""+utils.milliSecondsToTimer(currentDuration));
				   
				   // Updating progress bar
				   int progress = (int)(utils.getProgressPercentage(currentDuration, totalDuration));
				   //Log.d("Progress", ""+progress);
				   songProgressBar.setProgress(progress);
				   
				   // Running this thread after 100 milliseconds
			       mHandler.postDelayed(this, 100);
			   }
		   }
		};
		
	/**
	 * 
	 * */
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
		
	}

	/**
	 * When user starts moving the progress handler
	 * */
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// remove message Handler from updating progress bar
		mHandler.removeCallbacks(mUpdateTimeTask);
    }
	
	/**
	 * When user stops moving the progress hanlder
	 * */
	@Override
    public void onStopTrackingTouch(SeekBar seekBar) {
		mHandler.removeCallbacks(mUpdateTimeTask);
		int totalDuration = mp.getDuration();
		int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);
		
		// forward or backward to certain seconds
		mp.seekTo(currentPosition);
		
		// update timer progress again
		updateProgressBar();
    }

	@Override
	 public void onDestroy(){
	 super.onDestroy();
	 	Log.i(tag, "onDestroy");
	    mp.release();
	 }	
}