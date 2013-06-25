package br.com.appreciador.view;

import jim.h.common.android.lib.zxing.sample.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class ActivityTexto extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_texto);
		WebView wv = (WebView) findViewById(R.id.textohtml);
		
		Intent intent = getIntent();
		if(intent.getExtras() != null){
			Bundle parametro = intent.getExtras();
			if(parametro != null){
				String resultado = parametro.getString("resultado_qrcode").replaceAll("dir", "texto") + ".html";
				wv.loadUrl(resultado);
			}
		}
	}
}
