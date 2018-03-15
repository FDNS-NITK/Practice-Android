package flappybird.failedcoder.com.demo2;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class CODING extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding);
    }
    public void onButtonClick(View v) {
        if (v.getId() == R.id.Bdisplay1) {
//            Intent i = new Intent(CODING.this, STL.class);
//            startActivity(i);
//            view12();
            download();
        } else if (v.getId() == R.id.Bdisplay2) {
            Intent i = new Intent(CODING.this, DATA.class);
            startActivity(i);
        } else if (v.getId() == R.id.Bdisplay3) {
            Intent i = new Intent(CODING.this, ALGO.class);
            startActivity(i);
        } else if (v.getId() == R.id.Bdisplay4) {
            Intent i = new Intent(CODING.this, ADVA.class);
            startActivity(i);
        }
    }
    public void download()
    {
        new DownloadFile().execute("http://www.cppreference.com/Cpp_STL_ReferenceManual.pdf","Cpp_STL_ReferenceManual.pdf");
    }

    public void view12()
    {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/CODING/" + "Cpp_STL_ReferenceManual.pdf");  // -> filename = maven.pdf
        if(!pdfFile.exists()){
            Toast.makeText(CODING.this, "Dont exists Downloading.......", Toast.LENGTH_SHORT).show();
            download();
            pdfFile = new File(Environment.getExternalStorageDirectory() + "/CODING/" + "Cpp_STL_ReferenceManual.pdf");  // -> filename = maven.pdf
        }
            Uri path = Uri.fromFile(pdfFile);
            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(path, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                startActivity(pdfIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(CODING.this, "No Application available to view PDF Downloading...", Toast.LENGTH_SHORT).show();
            }
    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {

            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "CODING");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);

            view12();
            return null;
        }
    }


}
