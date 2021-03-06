package com.gaurang.cardlayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

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


public class MainActivity extends AppCompatActivity implements IPickResult {
    public static RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
    public static RecyclerView recyclerView;
    public static ArrayList<DataModel> data;
    DataModel d,d1;
    PickImageDialog dialog;
    static View.OnClickListener myOnClickListener;

    ImageView imageView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        cameraView=findViewById(R.id.camera_view);
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
                .build();*/


        myOnClickListener = new MyOnClickListener(this);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<DataModel>();
        d = new DataModel("gaurang", "sadqwwr", "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription ane ", R.drawable.gmd);
        d1 = new DataModel("gaurang", "sadqwwr", "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription ane ", R.drawable.second);

        data.add(d);data.add(d);data.add(d1);
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
        imageView=findViewById(R.id.result);
Button b=findViewById(R.id.bu);
b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        PickSetup setup = new PickSetup()
                .setTitle("Choose")
                .setTitleColor(Color.WHITE)
                .setBackgroundColor(Color.GRAY)
                .setProgressTextColor(Color.WHITE)
                .setCancelText("CANCEL")
                .setCancelTextColor(Color.WHITE)
                .setFlip(true)
                .setMaxSize(500)
                .setPickTypes(EPickType.GALLERY, EPickType.CAMERA)
                .setCameraButtonText("Camera")
                .setGalleryButtonText("Gallery")
                .setIconGravity(Gravity.LEFT)
                .setButtonOrientation(LinearLayoutCompat.VERTICAL)
                .setSystemDialog(false);

        dialog = PickImageDialog.build(setup).show(MainActivity.this);
dialog.setOnPickCancel(new IPickCancel() {
    @Override
    public void onCancelClick() {
dialog.dismiss();    }
});
    }
});

    }

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            imageView.setImageBitmap(r.getBitmap());

            //or

          //  imageView.setImageURI(r.getUri());
        } else {
            //Handle possible errors
            //TODO: do what you have to do with r.getError();
        }
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

