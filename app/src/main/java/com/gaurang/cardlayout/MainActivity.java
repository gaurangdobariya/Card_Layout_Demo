package com.gaurang.cardlayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

import io.fotoapparat.Fotoapparat;
import io.fotoapparat.result.BitmapPhoto;
import io.fotoapparat.result.PhotoResult;
import io.fotoapparat.view.CameraView;

import static io.fotoapparat.log.LoggersKt.fileLogger;
import static io.fotoapparat.log.LoggersKt.logcat;
import static io.fotoapparat.log.LoggersKt.loggers;
import static io.fotoapparat.selector.FocusModeSelectorsKt.autoFocus;
import static io.fotoapparat.selector.FocusModeSelectorsKt.fixed;
import static io.fotoapparat.selector.SelectorsKt.firstAvailable;


public class MainActivity extends AppCompatActivity  {
    LinearLayout containerLinearLayout;
    TextView t;
    public static RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    public static RecyclerView recyclerView;
    public static ArrayList<DataModel> data;
    DataModel d;
    CameraView cameraView;
    static View.OnClickListener myOnClickListener;
    Fotoapparat fotoapparat;
    ImageView imageView;
    private PhotoResult FotoResult;
    private LinearLayout mContainerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraView=findViewById(R.id.camera_view);
fotoapparat=new Fotoapparat(this,cameraView)
                .with(this)
                .into(cameraView)           // view which will draw the camera preview
                .previewScaleType(io.fotoapparat.parameter.ScaleType.CenterCrop)  // we want the preview to fill the view
                .focusMode(firstAvailable( // (optional) use the first focus mode which is supported by device
                        autoFocus(),        // in case if continuous focus is not available on device, auto focus will be used
                        fixed()             // if even auto focus is not available - fixed focus mode will be used
                ))

                .logger(loggers(            // (optional) we want to log camera events in 2 places at once
                        logcat(),           // ... in logcat
                        fileLogger(this)    // ... and to file
                ))
                .build();


        myOnClickListener = new MyOnClickListener(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<DataModel>();
        d = new DataModel("gaurang", "sadqwwr", "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription ane ", R.drawable.dof);
        data.add(d);data.add(d);data.add(d);
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
        imageView=findViewById(R.id.result);
Button b=findViewById(R.id.bu);
b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FotoResult=fotoapparat.takePicture();
        fotoapparat.stop();
        //mContainerView.removeView((View) view.getParent().);

        Toast.makeText(getApplicationContext(),"photo captured",Toast.LENGTH_SHORT).show();
        //FotoResult.saveToFile(new java.io.File());
    }
});

    }

    @Override
    protected void onStart() {
        super.onStart();
        fotoapparat.start();
    }


    @Override
    protected void onStop() {
        super.onStop();
        fotoapparat.stop();
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
            if (textViewName.isShown()) {
                textViewName.setVisibility(View.GONE);
            } else {
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

