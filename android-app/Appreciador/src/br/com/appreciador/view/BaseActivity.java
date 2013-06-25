package br.com.appreciador.view;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
/*import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;*/
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BaseActivity extends Activity {
	public static final String MIME_TEXT_PLAIN = "text/plain";
	public static final String TAG = "Appreciador";

	private static String tag;
	//private NfcAdapter mNfcAdapter;
	
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

		if (mNfcAdapter == null) {
			// Stop here, we definitely need NFC
			Toast.makeText(this, "This device doesn't support NFC.", Toast.LENGTH_LONG).show();
			finish();
			return;
		}
	
		if (!mNfcAdapter.isEnabled()) {
			Toast.makeText(getApplicationContext(), "NFC is disabled.", Toast.LENGTH_LONG).show();
			Log.i(tag, "NFC Enabled");
		} else {
			//Toast.makeText(getApplicationContext(), R.string.explanation, Toast.LENGTH_LONG).show();
		}
		*/
		
		handleIntent(getIntent());		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		//setupForegroundDispatch(this, mNfcAdapter);
	}
	
	@Override
	protected void onPause() {
		
		//stopForegroundDispatch(this, mNfcAdapter);
		
		super.onPause();
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		/*
		 * This method gets called, when a new Intent gets associated with the current activity instance.
		 * Instead of creating a new activity, onNewIntent will be called. For more information have a look
		 * at the documentation.
		 * 
		 * In our case this method gets called, when the user attaches a Tag to the device.
		 */
		Log.i(tag, "onNewIntent");
		handleIntent(intent);
	}
	
	@SuppressLint("NewApi")
	private void handleIntent(Intent intent) {
		Log.i(tag, "handleIntent");
		
		String action = intent.getAction();
		/*
		 if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
			Log.i(tag, "ACTION_NDEF_DISCOVERED");
			
			String type = intent.getType();
			if (MIME_TEXT_PLAIN.equals(type)) {
				Log.i(tag, "MIME_TEXT_PLAIN");
				
				Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
				new NdefReaderTask().execute(tag);
				
			} else {
				Log.d(TAG, "Wrong mime type: " + type);
			}
		} else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
			Log.i(tag, "ACTION_TECH_DISCOVERED");
			
			// In case we would still use the Tech Discovered Intent
			Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
			String[] techList = tag.getTechList();
			String searchedTech = Ndef.class.getName();
			
			for (String tech : techList) {
				if (searchedTech.equals(tech)) {
					new NdefReaderTask().execute(tag);
					break;
				}
			}
		}
		*/
	}
	
	/**
	 * @param activity The corresponding {@link Activity} requesting the foreground dispatch.
	 * @param adapter The {@link NfcAdapter} used for the foreground dispatch.
	 */
	/*
	public static void setupForegroundDispatch(final Activity activity, NfcAdapter adapter) {
		Log.i(tag, "setupForegroundDispatch");
		
		final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

		final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);

		IntentFilter[] filters = new IntentFilter[1];
		String[][] techList = new String[][]{};

		// Notice that this is the same filter as in our manifest.
		filters[0] = new IntentFilter();
		filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
		filters[0].addCategory(Intent.CATEGORY_DEFAULT);
		try {
			filters[0].addDataType(MIME_TEXT_PLAIN);
			Log.i(tag, "try setupForegroundDispatch");
		} catch (MalformedMimeTypeException e) {
			Log.i(tag, "catch setupForegroundDispatch");
			throw new RuntimeException("Check your mime type.");
		}
		
		adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
	}

	*/
	/**
	 * @param activity The corresponding {@link BaseActivity} requesting to stop the foreground dispatch.
	 * @param adapter The {@link NfcAdapter} used for the foreground dispatch.
	 */
	/*
	public static void stopForegroundDispatch(final Activity activity, NfcAdapter adapter) {
		Log.i(tag, "stopForegroundDispatch");
		adapter.disableForegroundDispatch(activity);
	}
	*/
	
	/**
	 * Background task for reading the data. Do not block the UI thread while reading. 
	 * 
	 * @author Ralf Wondratschek
	 *
	 */
	
	/*
	private class NdefReaderTask extends AsyncTask<Tag, Void, String> {
		@Override
		protected String doInBackground(Tag... params) {
			Log.i(tag, "doInBackground");
			
			Tag tTag = params[0];
			
			Ndef ndef = Ndef.get(tTag);
			if (ndef == null) {
				// NDEF is not supported by this Tag. 
				return null;
			}

			NdefMessage ndefMessage = ndef.getCachedNdefMessage();

			NdefRecord[] records = ndefMessage.getRecords();
			Log.i(tag, "ndefMessage.getRecords()");
			
			for (NdefRecord ndefRecord : records) {
				if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
					try {
						Log.i(tag, "readText(ndefRecord)");
						return readText(ndefRecord);
					} catch (UnsupportedEncodingException e) {
						Log.e(TAG, "Unsupported Encoding", e);
					}
				}
			}

			return null;
		}
		*/
		
		/*
		private String readText(NdefRecord record) throws UnsupportedEncodingException {
			
			Log.i(tag, "readText");

			byte[] payload = record.getPayload();

			// Get the Text Encoding
			String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";

			// Get the Language Code
			int languageCodeLength = payload[0] & 0063;
			
			// String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
			// e.g. "en"
			
			// Get the Text
			return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
		}
		*/
		
		protected void onPostExecute(String result) {
			Log.i(tag, "onPostExecute: " + result);
			
			if (result != null) {
				Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
			}
		}
	}

