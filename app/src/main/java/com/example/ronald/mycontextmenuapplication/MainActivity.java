package com.example.ronald.mycontextmenuapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);

        registerForContextMenu(textView);
        registerForContextMenu(imageView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        if (v == textView) {
            menu.setHeaderTitle("Text Context");
            inflater.inflate(R.menu.text_menu, menu);
        }
        else if ( v == imageView) {
            menu.setHeaderTitle("Image Context");
            inflater.inflate(R.menu.image_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.yes) {
            Toast.makeText(this, "Yes was Clicked", Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.no) {
            Toast.makeText(this, "No was Clicked", Toast.LENGTH_LONG).show();
        }

        if (item.getItemId() == R.id.Ino) {
            Toast.makeText(this, "No was Clicked", Toast.LENGTH_LONG).show();
        }
        if (item.getItemId() == R.id.Iyes) {
            Toast.makeText(this, "Yes was Clicked", Toast.LENGTH_LONG).show();
        }

        return super.onContextItemSelected(item);
    }

    public void showCustomDialog(final View view) {
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.dialog);
        final RadioButton radioButton = (RadioButton) d.findViewById(R.id.radioButton);
        Button closeBtn = (Button) d.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.cancel();
            }
        });
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //d.cancel();
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(d.getContext());

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("Whats good")
                        .setTitle("Yo");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Toast.makeText(d.getContext(),"Clicked OK",Toast.LENGTH_SHORT).show();
                        showProgress(view);
                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Toast.makeText(d.getContext(),"Clicked Cancel",Toast.LENGTH_SHORT).show();
                        d.cancel();
                    }
                });
                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        d.setTitle("Custom");
        d.show();
    }

    ProgressDialog pd;

    public void showProgress(View view) {
        pd = new ProgressDialog(this);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("Loading - Please Wait");
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.show();

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                Toast.makeText(getApplicationContext()," "+millisUntilFinished,Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(),"FINISHED EM",Toast.LENGTH_SHORT).show();
                pd.cancel();
            }

        }.start();


       /* Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    final int finalI = i;
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("Thread_ope", "" + finalI);
                            MainActivity.this.pd.setProgress(finalI);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                pd.dismiss();

            }
        });

        myThread.start();
*/

    }
}
