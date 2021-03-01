package enjoyuse;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import hznu.linxin.banner.R;

import static hznu.linxin.banner.Public_Function.checkKitchen;
import static hznu.linxin.banner.Public_Function.checkModifyKitchen;
import static hznu.linxin.banner.Public_Function.checkModifyWasher;
import static hznu.linxin.banner.Public_Function.checkWasher;
import static hznu.linxin.banner.Public_Function.db;

public class Kitchen_Activity extends AppCompatActivity {
    private List<Kitchen> kitchenList = new ArrayList<Kitchen>();
    Kitchen_Adapter adapter;
    private Button go_back;
    private Button add_btn;
    private FrameLayout edit_framelayout3;
    private ImageView delete_framelayout3;
    private ImageView edit_framelayout_img3;
    private EditText kitchenIdEdit;
    private EditText kitchenAddressEdit;
    private EditText kitchenWaitEdit;
    private EditText kitchenTimeEdit;
    private Button addDB_btn3;
    private EditText edittext;
    private ImageView search_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_recycleview);
        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("共享厨房");

        // init()
        firstInitKitchen();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // ---------瀑布布局----------
        StaggeredGridLayoutManager layoutManager=new
                StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Kitchen_Adapter(kitchenList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        // bind
        go_back = (Button) findViewById(R.id.go_back);
        add_btn = (Button) findViewById(R.id.add_button);
        edit_framelayout3 = (FrameLayout) findViewById(R.id.edit_framelayout3);
        delete_framelayout3 = (ImageView) findViewById(R.id.delete_framelayout3);
        edit_framelayout_img3 = (ImageView) findViewById(R.id.edit_framelayout_img3);
        kitchenIdEdit = (EditText) findViewById(R.id.kitchenIdEdit);
        kitchenAddressEdit = (EditText) findViewById(R.id.kitchenAddressEdit);
        kitchenWaitEdit = (EditText) findViewById(R.id.kitchenWaitEdit);
        kitchenTimeEdit = (EditText) findViewById(R.id.kitchenTimeEdit);
        addDB_btn3 = (Button) findViewById(R.id.addDB_btn3);
        edittext = (EditText) findViewById(R.id.edittext);
        search_btn = (ImageView) findViewById(R.id.search_btn);

        // 点击返回事件
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });

        // 实现搜索按钮
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_content = edittext.getText().toString();
                searchKitchen(search_content);
            }
        });

        // 点击添加事件
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_framelayout3.setVisibility(View.VISIBLE);
                edit_framelayout_img3.setImageResource(R.mipmap.kitchen_img);
                kitchenIdEdit.setText("");
                kitchenAddressEdit.setText("");
                kitchenWaitEdit.setText("");
                kitchenTimeEdit.setText("");
                addDB_btn3.setText("添加");
            }
        });
        // 实现添加到数据库
        addDB_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kitchen_idText = kitchenIdEdit.getText().toString();
                String kitchen_addressText = kitchenAddressEdit.getText().toString();
                String kitchen_waitText = kitchenWaitEdit.getText().toString();
                String kitchen_timeText = kitchenTimeEdit.getText().toString();
                if (addDB_btn3.getText().equals("添加")) {
                    if (checkKitchen(kitchen_idText, kitchen_addressText, kitchen_waitText, kitchen_timeText) == 1) {
                        Toast.makeText(Kitchen_Activity.this, "编号" + kitchen_idText + "已存在, 请更换厨房编号", Toast.LENGTH_LONG).show();
                    }
                    else if(checkKitchen(kitchen_idText, kitchen_addressText, kitchen_waitText, kitchen_timeText) ==  2) {
                        Toast.makeText(Kitchen_Activity.this, kitchen_idText + "厨房编号不合法, 请输入3-10位数字", Toast.LENGTH_LONG).show();
                    }
                    // 0表示数据正常 可以插入
                    else if(checkKitchen(kitchen_idText, kitchen_addressText, kitchen_waitText, kitchen_timeText) == 0){
                        ContentValues values = new ContentValues();
                        values.put("kitchen_id", kitchen_idText);
                        values.put("kitchen_address", kitchen_addressText);
                        values.put("kitchen_wait", kitchen_waitText);
                        values.put("kitchen_time", kitchen_timeText);
                        values.put("kitchen_imageId", "kitchen_image");
                        db.insert("Kitchen", null, values);
                        Toast.makeText(Kitchen_Activity.this, "厨房编号" + kitchen_idText + "插入成功", Toast.LENGTH_SHORT).show();
                        edit_framelayout3.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                        initKitchen();
                    }
                }
                else if (addDB_btn3.getText().equals("修改")) {
                    if (checkModifyKitchen(kitchen_idText, kitchen_addressText, kitchen_waitText, kitchen_timeText) == 0) {
                        ContentValues values = new ContentValues();
                        values.put("kitchen_address", kitchen_addressText);
                        values.put("kitchen_wait", kitchen_waitText);
                        values.put("kitchen_time", kitchen_timeText);
                        values.put("kitchen_imageId", "kitchen_image");
                        db.update("Kitchen", values, "kitchen_id = ?",
                                new String[] { kitchen_idText });
                        Toast.makeText(Kitchen_Activity.this, "厨房号" + kitchen_idText + "修改成功", Toast.LENGTH_LONG).show();
                        edit_framelayout3.setVisibility(View.GONE);
                    }
                    edit_framelayout3.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                    initKitchen();
                }
            }
        });
        // 点击隐藏FrameLayout
        delete_framelayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_framelayout3.setVisibility(View.GONE);
            }
        });
        // 点击子项的修改按钮
        adapter.setOnItemClickListener(new Kitchen_Adapter.OnItemClickListener(){
            @Override
            public void onClick(int position) {
                edit_framelayout3.setVisibility(View.VISIBLE);
                edit_framelayout_img3.setImageResource(R.mipmap.kitchen_img);
                Kitchen kitchen = kitchenList.get(position);
                kitchenIdEdit.setText(kitchen.getKitchen_id());
                kitchenAddressEdit.setText(kitchen.getKitchen_address());
                kitchenWaitEdit.setText(kitchen.getKitchen_wait());
                kitchenTimeEdit.setText(kitchen.getKitchen_time());
                addDB_btn3.setText("修改");
            }
        });
    }

    //
    private void firstInitKitchen() {
        kitchenList.clear();
        Cursor cursor = db.query("Kitchen", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String kitchen_id = cursor.getString(cursor
                        .getColumnIndex("kitchen_id"));
                String kitchen_address = cursor.getString(cursor
                        .getColumnIndex("kitchen_address"));
                String kitchen_imageId = cursor.getString(cursor
                        .getColumnIndex("kitchen_imageId"));
                String kitchen_wait = cursor.getString(cursor
                        .getColumnIndex("kitchen_wait"));
                String kitchen_time = cursor.getString(cursor
                        .getColumnIndex("kitchen_time"));
                Kitchen kitchen = new Kitchen(kitchen_id, kitchen_address, R.mipmap.kitchen_img,
                        kitchen_wait, kitchen_time);
                kitchenList.add(kitchen);
            }
            cursor.close();
        }
        adapter = new Kitchen_Adapter(kitchenList);
        adapter.notifyDataSetChanged();
    }

    private void initKitchen() {
        kitchenList.clear();
        Cursor cursor = db.query("Kitchen", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String kitchen_id = cursor.getString(cursor
                        .getColumnIndex("kitchen_id"));
                String kitchen_address = cursor.getString(cursor
                        .getColumnIndex("kitchen_address"));
                String kitchen_imageId = cursor.getString(cursor
                        .getColumnIndex("kitchen_imageId"));
                String kitchen_wait = cursor.getString(cursor
                        .getColumnIndex("kitchen_wait"));
                String kitchen_time = cursor.getString(cursor
                        .getColumnIndex("kitchen_time"));
                Kitchen kitchen = new Kitchen(kitchen_id, kitchen_address, R.mipmap.kitchen_img,
                        kitchen_wait, kitchen_time);
                kitchenList.add(kitchen);
            }
            cursor.close();
        }
        adapter = new Kitchen_Adapter(kitchenList);
        adapter.notifyDataSetChanged();
    }

    // 向数据库请求查找数据
    private void searchKitchen(String content) {
        kitchenList.clear();
        int query_cnt = 0;
        Cursor cursor = db.query("Kitchen", null, "kitchen_id like ? or kitchen_address like ?", new String[] { "%" + content + "%", "%" + content + "%" }, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String kitchen_id = cursor.getString(cursor
                        .getColumnIndex("kitchen_id"));
                String kitchen_address = cursor.getString(cursor
                        .getColumnIndex("kitchen_address"));
                String kitchen_imageId = cursor.getString(cursor
                        .getColumnIndex("kitchen_imageId"));
                String kitchen_wait = cursor.getString(cursor
                        .getColumnIndex("kitchen_wait"));
                String kitchen_time = cursor.getString(cursor
                        .getColumnIndex("kitchen_time"));
                Kitchen kitchen = new Kitchen(kitchen_id, kitchen_address, R.mipmap.kitchen_img,
                        kitchen_wait, kitchen_time);
                kitchenList.add(kitchen);
                query_cnt++;
            }
            cursor.close();
        }
        if (query_cnt == 0) {
            // 提示框
            AlertDialog.Builder builder = new AlertDialog.Builder(Kitchen_Activity.this);
            builder.setTitle("查询结果");
            builder.setMessage("未查询到结果，请输入厨房编号或地址");
            builder.setCancelable(false);
            builder.setPositiveButton("确认",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    });
            builder.show();
            initKitchen();
        }
//        adapter = new Kitchen_Adapter(kitchenList);
        adapter.notifyDataSetChanged();
    }
}
