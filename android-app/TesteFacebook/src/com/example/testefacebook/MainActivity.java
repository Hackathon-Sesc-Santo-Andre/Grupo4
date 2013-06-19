package com.example.testefacebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import br.com.indigo.android.facebook.SocialFacebook;
import br.com.indigo.android.facebook.SocialFacebook.NewObjectListener;
import br.com.indigo.android.facebook.models.FbSimplePost;

public class MainActivity extends Activity {
	String[] permissions = {"create_event", "user_events", "rsvp_event", "publish_stream"};
	String APP_ID = "481674528575609";
	String APP_SECRET = "a99627a866400527b71fcdc5ec36ed66";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void publica(View view){
		FbSimplePost post = new FbSimplePost();
		post.setName("segundo teste de post ");
		post.setCaption("outro post enviado pelo Appreciador");
		post.setDescription("Será que tá funcionando ??");
		post.setLink("http://www.sescsp.org.br/sesc/programa_new/mostra_detalhe.cfm?programacao_id=235923");
		post.setPicture("http://www.sescsp.org.br/sesc/home/images/logosesc.gif");
		//post.setActionName("pra que serve isso ??");
		//post.setActionLink("pra que serve isso ??");
		post.setMessage("Diga o que achou disso...");
		
		SocialFacebook.getInstance(this, APP_ID, APP_SECRET, permissions).publish(this, post, new NewObjectListener(){
			public void onComplete(String id){
				System.out.println(id);
			}
			public void onFail(Throwable thr){
				thr.printStackTrace();
			}
			public void onCancel(){}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		SocialFacebook.getInstance().authorizeCallback(requestCode, resultCode, data);
	}
}
