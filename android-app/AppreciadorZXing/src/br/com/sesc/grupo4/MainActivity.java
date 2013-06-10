package br.com.sesc.grupo4;

import jim.h.common.android.lib.zxing.sample.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void btnOuvirClicked(View v) {
		Intent intent = new Intent(this, ScanActivity.class);
		startActivity(intent);
	}
	
}
