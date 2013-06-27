package br.com.appreciador.view;

import br.com.indigo.android.facebook.SocialFacebook;
import br.com.indigo.android.facebook.SocialFacebook.NewObjectListener;
import br.com.indigo.android.facebook.models.FbSimplePost;
import jim.h.common.android.lib.zxing.sample.R;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends BaseActivity {
	public static final String MIME_TEXT_PLAIN = "text/plain";
	public static final String TAG = "NfcDemo";

	private static String tag;
	private NfcAdapter mNfcAdapter;
	
	String[] permissions = {"create_event", "user_events", "rsvp_event", "publish_stream"};
	String APP_ID = "481674528575609";
	String APP_SECRET = "a99627a866400527b71fcdc5ec36ed66";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void btnOuvirClicked(View v) {
		Intent intent = new Intent(this, ScanActivity.class);
		Bundle parametro = new Bundle();
		parametro.putString("acao", "ouvir");
		intent.putExtras(parametro);
		startActivity(intent);
	}
	
	public void btnLerClicked(View v){
		Intent intent = new Intent(this, ScanActivity.class);
		Bundle parametro = new Bundle();
		parametro.putString("acao", "ler");
		intent.putExtras(parametro);
		startActivity(intent);
	}
	
	public void publica(View view){
		FbSimplePost post = new FbSimplePost();
		post.setName("Exposição Audácia Concreta - Luiz Sacilotto ");
		post.setCaption("Olá !! Veja a exposição que visitei no SESC Santo André");
		post.setLink("http://www.sescsp.org.br/sesc/programa_new/mostra_detalhe.cfm?programacao_id=243389");
		post.setPicture("http://3.bp.blogspot.com/-y4VOIfB0BWo/UZPj0uODLQI/AAAAAAAAC0M/U7-7dTXVcZQ/s1600/expo_sacilotto_audacia_concreta.jpg");
		
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
