package br.com.appreciador.view;

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
}
