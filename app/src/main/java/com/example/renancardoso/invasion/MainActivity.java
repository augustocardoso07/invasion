package com.example.renancardoso.invasion;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	private TextView textViewCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textViewCode = (TextView) findViewById(R.id.textViewCode);

		Button BtnBackspace = (Button) findViewById(R.id.textViewBackspace);
		BtnBackspace.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				textViewCode.setText("");
				return true;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.example1) {
			textViewCode.setText("3 4 50 2 13 67 4 23 18");
			return true;
		}


		if (id == R.id.example2) {
			textViewCode.setText("5 6 22 122 34 67 89 32 189 25 53 67 125");
			return true;
		}


		if (id == R.id.example3) {
			textViewCode.setText("5 6 22 123 34 67 89 32 189 25 53 67 125");
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void updateCode(View v) {
		String s = textViewCode.getText().toString();
		String toAdd = ((Button) v).getText().toString();
		try {
			if (v.getId() == R.id.textViewBackspace) {
				s = s.subSequence(0, s.length() - 1).toString();
				toAdd = "";
			}
		} catch (IndexOutOfBoundsException e) {
			return;
		}
		if (v.getId() == R.id.textViewSpace)     toAdd = " ";
		textViewCode.setText(s + toAdd);
	}

	public void calculateInvasion(View v) {
		Toast.makeText(getApplicationContext(), "Teste", Toast.LENGTH_LONG).show();
	}
}
