package com.example.renancardoso.invasion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;


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
        try {
            String[] arr = textViewCode.getText().toString().trim().split("\\s+");
            if (!validateArr(arr)) {
                showErr("O códico está incorreto. Por favor, digite novamente");
                return;
            }

            int M = Integer.parseInt(arr[0]);
            if (!validateM(M)) {
                showErr("O primeiro número deve estar entre 1 e 5");
                return;
            }

            int N = Integer.parseInt(arr[1]);
            if (!validateN(N)) {
                showErr("O segundo número deve estar entre 1 e 10");
                return;
            }

            String[] Ms = Arrays.copyOfRange(arr, 2, 2 + M);
            if (!validateMs(Ms, M)) {
                showErr("A segunda parte do código deve conter " + M + " elementos");
                return;
            }

            String[] Ns = Arrays.copyOfRange(arr, 2 + M, arr.length);
            if (!validateNs(Ns, N)) {
                showErr("A terceira parde do código deve conter " + N + " elementos");
                return;
            }


            CalculateInvasion invasion = new CalculateInvasion(M, N, Ms, Ns);
            openDialog(invasion.getDate());
        } catch (java.lang.NumberFormatException e) {
            showErr("Número invalido");
        } catch (java.lang.IndexOutOfBoundsException e) {
            showErr("Array invalida");
        } catch (Exception e) {
            showErr("Erro desconhecido");
        }

	}

    private boolean validateArr(String[] arr) {
        int sum = (Integer.parseInt(arr[0]) + Integer.parseInt(arr[1]));
        boolean part1 = arr.length >= 4;
        boolean part2 = sum + 2 == arr.length;
        boolean result =  part1 && part2;
        return result;
    }

    private void showErr(CharSequence text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    private boolean validateM(int M) {
        return M >= 1 && M <= 5;
    }

    private boolean validateN(int N) {
        return N >= 1 && N <= 10;
    }

    private boolean validateMs(String[] Ms, int M) {
        for (String s: Ms) if (Integer.parseInt(s) >= 500) return false;
        return Ms.length == M;
    }

    private boolean validateNs(String[] Ns, int N) {
        return  Ns.length == N;
    }

    private void openDialog(CharSequence text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Data da Invasão:");
        builder.setMessage(text);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Código decifrado com succeso!", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }
}
