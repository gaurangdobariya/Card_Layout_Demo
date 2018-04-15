package com.gaurang.cardlayout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    LinearLayout containerLinearLayout;
    TextView t;
    public static RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    public static RecyclerView recyclerView;
    public static ArrayList<DataModel> data;
    DataModel d;
    int flag=0;
    static View.OnClickListener myOnClickListener;
    public static ArrayList<Integer> removedItems;

    TextView desc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myOnClickListener = new MyOnClickListener(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
flag=1;
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
       // animationUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.);
        //animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<DataModel>();
        d = new DataModel("gaurang", "sadqwwr","descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription ane ", R.drawable.dof);
        data.add(d);
        data.add(d);

        data.add(d);
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);


    }

    public static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        public MyOnClickListener(Context context) {
            this.context = context;

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "onclick", Toast.LENGTH_SHORT).show();

               Coll_Expand(v);
        }

        private void Coll_Expand(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.card_view_show_more);
            String selectedName = (String) textViewName.getText();
            if(textViewName.isShown()){
                textViewName.setVisibility(View.GONE);
            }
            else
            {
                textViewName.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
      /*  if (item.getItemId() == R.id.add_item) {
            //check if any items to add
            if (removedItems.size() != 0) {
                addRemovedItemToList();
            } else {
                Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
            }
        }*/
        return true;
    }

    private void addRemovedItemToList() {
        int addItemAtListPosition = 3;
        data.add(addItemAtListPosition, d);
        adapter.notifyItemInserted(addItemAtListPosition);
        //  removedItems.remove(0);
    }
}

