package jim.h.common.android.lib.zxing.sample;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
		Log.i(tag, "btnOuvirClicked");
		startActivity(intent);
	}
}
