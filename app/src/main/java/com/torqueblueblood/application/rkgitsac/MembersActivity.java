package com.torqueblueblood.application.rkgitsac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MembersActivity extends AppCompatActivity {
    ListView listView;
    private static final String TAG="MembersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        Log.d(TAG,"onCreate: Started");
        listView=(ListView) findViewById(R.id.list_item);
        Adapter Dhrati=new Adapter("Dhrati Gupta","Singing");
        Adapter Prakhar=new Adapter("Prakhar Singh","Ramp Core");
        Adapter Anukriti=new Adapter("Anukriti Pudmja","Ramp Core");
        Adapter Shubhang=new Adapter("Shubhang Tripathi","Dance Core");
        Adapter K=new Adapter("K. Ashutosh tripathi","XT and Dramatics Core");
        Adapter Sidharth=new Adapter("Sidharth Singh","XT Core");
        Adapter Naman=new Adapter("Naman Mishra","Event Manager");
        Adapter Vijay=new Adapter("Vijay Sehrawat","Decore Head");
        Adapter Nidhi=new Adapter("Nidhi Singh","Decore Head");

        ArrayList<Adapter>memberslist=new ArrayList<>();
        memberslist.add(Dhrati);
        memberslist.add(Prakhar);
        memberslist.add(Anukriti);
        memberslist.add(Shubhang);
        memberslist.add(K);
        memberslist.add(Sidharth);
        memberslist.add(Naman);
        memberslist.add(Vijay);
        memberslist.add(Nidhi);

        AdapterListAdapter adapter =new AdapterListAdapter(this,R.layout.listview,memberslist);
        listView.setAdapter(adapter);

    }
    public void onBackPressed(){
        finish();
        Intent intent=new Intent(MembersActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

}
