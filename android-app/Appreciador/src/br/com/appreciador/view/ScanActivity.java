package br.com.appreciador.view;

import jim.h.common.android.lib.zxing.config.ZXingLibConfig;
import jim.h.common.android.lib.zxing.integrator.IntentIntegrator;
import jim.h.common.android.lib.zxing.integrator.IntentResult;
import jim.h.common.android.lib.zxing.sample.R;
import jim.h.common.android.lib.zxing.sample.R.id;
import jim.h.common.android.lib.zxing.sample.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ScanActivity extends BaseActivity {
    private Handler        handler = new Handler();
    private ZXingLibConfig zxingLibConfig;
    private static String tag = "ScanActivity";
    private String acao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_activity);

        zxingLibConfig = new ZXingLibConfig();
        zxingLibConfig.useFrontLight = true;

        View btnScan = findViewById(R.id.scan_button);
        
        btnScan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Log.i(tag, "setOnClickListener -> onClick -> initiateScan");
                IntentIntegrator.initiateScan(ScanActivity.this, zxingLibConfig);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IntentIntegrator.REQUEST_CODE: // 扫描结果
                IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode,
                        resultCode, data);
                if (scanResult == null) {
                    return;
                }
                final String result = scanResult.getContents();
                if (result != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                        	
                        	//Intent intent = new Intent(getApplicationContext(), AudioActivity.class);
                        	
                        	Intent proximaTela = null;
                        	
                        	Intent telaAnterior = getIntent();
                        	if(telaAnterior.getExtras() != null){
                        		Bundle parametro = telaAnterior.getExtras();
                        		if(parametro != null){
                        			acao = parametro.getString("acao");
                        		}
                        	}
                        	
                        	if(acao.equalsIgnoreCase("ouvir")){
                        		proximaTela = new Intent(getApplicationContext(), AndroidBuildingMusicPlayerActivity.class);
                        	}else if(acao.equalsIgnoreCase("ler")){
                        		proximaTela = new Intent(getApplicationContext(), ActivityTexto.class);
                        	}
                        	
                        	Bundle parametro = new Bundle();
                    		parametro.putString("resultado_qrcode", result);
                    		proximaTela.putExtras(parametro);
                        	startActivity(proximaTela);
                        }
                    });
                }
                break;
            default:
        }
    }
}
