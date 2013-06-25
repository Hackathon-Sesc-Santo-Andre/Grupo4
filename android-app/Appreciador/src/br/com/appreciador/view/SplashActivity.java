package br.com.appreciador.view;

import jim.h.common.android.lib.zxing.sample.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.androidquery.AQuery;

public class SplashActivity extends BaseActivity {

	
	private AQuery a;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		a = new AQuery(this);
		
		
		final Runnable irParaOutraTela = new Runnable(){
			@Override
			public void run() {
				Intent tela2 = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(tela2);
				finish();
			}
		};
		
		
		AnimationListener escutadorDeAnimacao = new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				Handler handler = new Handler();
				handler.postDelayed(irParaOutraTela, 300);
				
			}
		};
		
		a.id(R.id.logo).animate(R.anim.caindo, escutadorDeAnimacao);
	}

	

}
