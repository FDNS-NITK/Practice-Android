package flappybird.failedcoder.com.demo2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;

public class notes extends AppCompatActivity {
    static ArrayList<String> not;
    static ArrayAdapter arrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_in,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.newitem ){
            Intent intent = new Intent(getApplicationContext(),notesputter.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("flappybird.failedcoder.com.demo2", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("not",null);


        not = new ArrayList<>();
        ListView listView = findViewById(R.id.listview);
        Log.i("ni","sdf");
        if(set == null) {
            Log.i("nidsfadfaf","sdf");
            not.add("Example Notes");
        }else {
            not = new ArrayList<>(set);
            Log.i("nidsfadfaf","sdffff");
        }
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,not);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),notesputter.class);
                intent.putExtra("noteId",i);
                startActivity(intent);
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int ll = i;
                new AlertDialog.Builder(notes.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete")
                        .setMessage("Do you want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                not.remove(ll);
                                arrayAdapter.notifyDataSetChanged();
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("flappybird.failedcoder.com.demo2", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet<>(notes.not);
                                sharedPreferences.edit().putStringSet("not",set).apply();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

}

