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

import static hznu.linxin.banner.Public_Function.checkModifyPrinter;
import static hznu.linxin.banner.Public_Function.checkModifyWasher;
import static hznu.linxin.banner.Public_Function.checkPrinter;
import static hznu.linxin.banner.Public_Function.checkWasher;
import static hznu.linxin.banner.Public_Function.db;

public class Washers_Activity extends AppCompatActivity {

    private List<Washers> washersList = new ArrayList<Washers>();
    WashersAdapter adapter;
    private Button go_back;
    private Button add_btn;
    private FrameLayout edit_framelayout;
    private ImageView delete_framelayout;
    private ImageView edit_framelayout_img;
    private EditText machine_id;
    private EditText machine_address;
    private RadioButton use_radio;
    private RadioButton free_radio;
    private Button addDB_btn;
    private EditText edittext;
    private ImageView search_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_recycleview);
        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("洗衣空间");
        // init();
        firstInitWashers();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // ---------瀑布布局----------
        StaggeredGridLayoutManager layoutManager=new
                StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new WashersAdapter(washersList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        // bind
        go_back = (Button) findViewById(R.id.go_back);
        add_btn = (Button) findViewById(R.id.add_button);
        edit_framelayout = (FrameLayout) findViewById(R.id.edit_framelayout);
        delete_framelayout = (ImageView) findViewById(R.id.delete_framelayout);
        edit_framelayout_img = (ImageView) findViewById(R.id.edit_framelayout_img);
        machine_id = (EditText) findViewById(R.id.machine_id);
        machine_address = (EditText) findViewById(R.id.machine_address);
        use_radio = (RadioButton) findViewById(R.id.use_radio);
        free_radio = (RadioButton) findViewById(R.id.free_radio);
        addDB_btn = (Button) findViewById(R.id.addDB_btn);
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
                searchWashers(search_content);
            }
        });

        // 点击添加事件
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_framelayout.setVisibility(View.VISIBLE);
                edit_framelayout_img.setImageResource(R.mipmap.wash_machine);
                machine_id.setText("");
                machine_address.setText("");
                addDB_btn.setText("添加");
            }
        });
        // 实现添加到数据库
        addDB_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String machine_idText = machine_id.getText().toString();
                String machine_addressText = machine_address.getText().toString();
                String machine_usage = null;
                if (use_radio.isChecked()) {
                    machine_usage = use_radio.getText().toString();
                }
                else if (free_radio.isChecked()) {
                    machine_usage = free_radio.getText().toString();
                }

                if (addDB_btn.getText().equals("添加")) {
                    if (checkWasher(machine_idText, machine_addressText, machine_usage) == 1) {
                        Toast.makeText(Washers_Activity.this, "机器" + machine_idText + "已存在, 请更换机器名", Toast.LENGTH_LONG).show();
                    }
                    else if(checkWasher(machine_idText, machine_addressText, machine_usage) == 2) {
                        Toast.makeText(Washers_Activity.this, machine_idText + "机器名不合法, 请输入3-10位数字", Toast.LENGTH_LONG).show();
                    }
                    // 0表示数据正常 可以插入
                    else if(checkWasher(machine_idText, machine_addressText, machine_usage) == 0){
                        ContentValues values = new ContentValues();
                        values.put("wash_id", machine_idText);
                        values.put("wash_address", machine_addressText);
                        if (machine_usage.equals("空闲中") || machine_usage.equals("空闲") || machine_usage.equals("free")){
                            values.put("wash_usage", "free");
                        }
                        else {
                            values.put("wash_usage", "use");
                        }
                        values.put("wash_imageId", "washer1_img");
                        values.put("wash_time", "00:00");
                        db.insert("Washer", null, values);
                        Toast.makeText(Washers_Activity.this, "机器号" + machine_idText + "插入成功", Toast.LENGTH_LONG).show();
                        edit_framelayout.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                        initWashers();
                    }
                }
                else if (addDB_btn.getText().equals("修改")) {
                    if (checkModifyWasher(machine_idText, machine_addressText, machine_usage) == 0) {
                        ContentValues values = new ContentValues();
                        values.put("wash_address", machine_addressText);
                        if (machine_usage.equals("空闲中") || machine_usage.equals("空闲") || machine_usage.equals("free")){
                            values.put("wash_usage", "free");
                        }
                        else {
                            values.put("wash_usage", "use");
                        }
                        values.put("wash_imageId", "washer1_img");
                        values.put("wash_time", "00:00");
                        db.update("Washer", values, "wash_id = ?",
                                new String[] { machine_idText });
                        Toast.makeText(Washers_Activity.this, "机器号" + machine_idText + "修改成功", Toast.LENGTH_LONG).show();
                        edit_framelayout.setVisibility(View.GONE);
                    }
                    adapter.notifyDataSetChanged();
                    initWashers();
                }
            }
        });
        // 点击隐藏FrameLayout
        delete_framelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_framelayout.setVisibility(View.GONE);
            }
        });
        // 点击子项的修改按钮
        adapter.setOnItemClickListener(new PrintersAdapter.OnItemClickListener(){
            @Override
            public void onClick(int position) {
                edit_framelayout.setVisibility(View.VISIBLE);
                edit_framelayout_img.setImageResource(R.mipmap.wash_machine);
                Washers washers = washersList.get(position);
                machine_id.setText(washers.getWasher_id());
                machine_address.setText(washers.getWasher_address());
                if (washers.isWasher_usage()) {
                    use_radio.setChecked(true);
                    free_radio.setChecked(false);
                }
                else {
                    use_radio.setChecked(false);
                    free_radio.setChecked(true);
                }
                addDB_btn.setText("修改");
            }
        });
    }

    // 向数据库中请求数据
    private void firstInitWashers() {
        washersList.clear();
        Cursor cursor = db.query("Washer", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.d("WasherActivity_test", "test query begin:");
                String wash_id = cursor.getString(cursor
                        .getColumnIndex("wash_id"));
                String wash_address = cursor.getString(cursor
                        .getColumnIndex("wash_address"));
                String wash_imageId = cursor.getString(cursor
                        .getColumnIndex("wash_imageId"));
                String wash_usage = cursor.getString(cursor
                        .getColumnIndex("wash_usage"));
                String wash_time = cursor.getString(cursor
                        .getColumnIndex("wash_time"));
                // 如果使用情况为：
                if (wash_usage.equals("use")|| wash_usage.equals("使用中")) {
                    Washers washers = new Washers(wash_id, wash_address, R.mipmap.wash_machine,
                            true, wash_time);
                    washersList.add(washers);
                }
                else {
                    Washers washers = new Washers(wash_id, wash_address, R.mipmap.wash_machine,
                            false, wash_time);
                    washersList.add(washers);
                }
            }
            cursor.close();
        }
        adapter = new WashersAdapter(washersList);
        adapter.notifyDataSetChanged();
    }

    // 向数据库中请求数据
    private void initWashers() {
        washersList.clear();
        Cursor cursor = db.query("Washer", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.d("WasherActivity_test", "test query begin:");
                String wash_id = cursor.getString(cursor
                        .getColumnIndex("wash_id"));
                String wash_address = cursor.getString(cursor
                        .getColumnIndex("wash_address"));
                String wash_imageId = cursor.getString(cursor
                        .getColumnIndex("wash_imageId"));
                String wash_usage = cursor.getString(cursor
                        .getColumnIndex("wash_usage"));
                String wash_time = cursor.getString(cursor
                        .getColumnIndex("wash_time"));
                // 如果使用情况为：
                if (wash_usage.equals("use")|| wash_usage.equals("使用中")) {
                    Washers washers = new Washers(wash_id, wash_address, R.mipmap.wash_machine,
                            true, wash_time);
                    washersList.add(washers);
                }
                else {
                    Washers washers = new Washers(wash_id, wash_address, R.mipmap.wash_machine,
                            false, wash_time);
                    washersList.add(washers);
                }
            }
            cursor.close();
        }
//        adapter = new WashersAdapter(washersList);
        adapter.notifyDataSetChanged();
    }

    //
    private void searchWashers(String content) {
        washersList.clear();
        int query_cnt = 0;
        Cursor cursor = db.query("Washer", null, "wash_id like ? or wash_address like ?", new String[] { "%" + content + "%", "%" + content + "%" }, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.d("WasherActivity_test", "test query begin:");
                String wash_id = cursor.getString(cursor
                        .getColumnIndex("wash_id"));
                String wash_address = cursor.getString(cursor
                        .getColumnIndex("wash_address"));
                String wash_imageId = cursor.getString(cursor
                        .getColumnIndex("wash_imageId"));
                String wash_usage = cursor.getString(cursor
                        .getColumnIndex("wash_usage"));
                String wash_time = cursor.getString(cursor
                        .getColumnIndex("wash_time"));
                // 如果使用情况为：
                if (wash_usage.equals("use")|| wash_usage.equals("使用中")) {
                    Washers washers = new Washers(wash_id, wash_address, R.mipmap.wash_machine,
                            true, wash_time);
                    washersList.add(washers);
                }
                else {
                    Washers washers = new Washers(wash_id, wash_address, R.mipmap.wash_machine,
                            false, wash_time);
                    washersList.add(washers);
                }
                query_cnt++;
            }
            cursor.close();
        }
        if (query_cnt == 0) {
            // 提示框
            AlertDialog.Builder builder = new AlertDialog.Builder(Washers_Activity.this);
            builder.setTitle("查询结果");
            builder.setMessage("未查询到结果，请输入设备编号或地址");
            builder.setCancelable(false);
            builder.setPositiveButton("确认",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    });
            builder.show();
            initWashers();
        }
//        adapter = new WashersAdapter(washersList);
        adapter.notifyDataSetChanged();
    }

}
