package enjoyuse;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import static hznu.linxin.banner.Public_Function.checkPrinter;
import static hznu.linxin.banner.Public_Function.db;
import static hznu.linxin.banner.Public_Function.public_printersList;
import static hznu.linxin.banner.Public_Function.public_printer_adpter;
public class Printers_Activity extends AppCompatActivity {

    private List<Printers> printersList = new ArrayList<Printers>();
    PrintersAdapter adapter;
    private Button go_back;
    private Button add_btn;
    private FrameLayout edit_framelayout;
    private ImageView delete_framelayout;
    private ImageView edit_framelayout_img;
    private EditText edittext;
    private ImageView search_btn;
    private EditText machine_id;
    private EditText machine_address;
    private RadioButton use_radio;
    private RadioButton free_radio;
    private Button addDB_btn;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_recycleview);
        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("打印空间");
        // init();
        firstInitPrinters();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // ---------瀑布布局----------
        StaggeredGridLayoutManager layoutManager=new
                StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PrintersAdapter(printersList);
        public_printer_adpter = adapter;
        public_printersList = printersList;
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        // bind
        go_back = (Button) findViewById(R.id.go_back);
        add_btn = (Button) findViewById(R.id.add_button);
        edit_framelayout = (FrameLayout) findViewById(R.id.edit_framelayout);
        delete_framelayout = (ImageView) findViewById(R.id.delete_framelayout);
        edit_framelayout_img = (ImageView) findViewById(R.id.edit_framelayout_img);
        edittext = (EditText) findViewById(R.id.edittext);
        search_btn = (ImageView) findViewById(R.id.search_btn);
        machine_id = (EditText) findViewById(R.id.machine_id);
        machine_address = (EditText) findViewById(R.id.machine_address);
        use_radio = (RadioButton) findViewById(R.id.use_radio);
        free_radio = (RadioButton) findViewById(R.id.free_radio);
        addDB_btn = (Button) findViewById(R.id.addDB_btn);
        // 点击返回按钮事件
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });
        // 点击添加事件
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_framelayout.setVisibility(View.VISIBLE);
                edit_framelayout_img.setImageResource(R.mipmap.print_machine_img);
                machine_id.setText("");
                machine_address.setText("");
                addDB_btn.setText("添加");
            }
        });
        // 实现搜索按钮
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search_content = edittext.getText().toString();
                searchPrinters(search_content);

                adapter.notifyDataSetChanged();
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
                    if (checkPrinter(machine_idText, machine_addressText, machine_usage) == 1) {
                        Toast.makeText(Printers_Activity.this, "机器" + machine_idText + "已存在, 请更换机器名", Toast.LENGTH_LONG).show();
                    }
                    else if (checkPrinter(machine_idText, machine_addressText, machine_usage) == 2) {
                        Toast.makeText(Printers_Activity.this, "机器名" + machine_idText + "不合法,请输入3-10位数字", Toast.LENGTH_LONG).show();
                    }
                    else if (checkPrinter(machine_idText, machine_addressText, machine_usage) == 0){
                        ContentValues values = new ContentValues();
                        values.put("printer_id", machine_idText);
                        values.put("printer_address", machine_addressText);
                        if (machine_usage.equals("空闲中") || machine_usage.equals("空闲") || machine_usage.equals("free")){
                            values.put("printer_usage", "free");
                        }
                        else {
                            values.put("printer_usage", "use");
                        }
                        values.put("printer_imageId", "printer1_img");
                        db.insert("Printer", null, values);
                        Toast.makeText(Printers_Activity.this, "机器" + machine_idText + "插入成功!", Toast.LENGTH_LONG).show();
                        edit_framelayout.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                        initPrinters();
                    }
                }
                else if (addDB_btn.getText().equals("修改")) {
                    if (checkModifyPrinter(machine_idText, machine_addressText, machine_usage) == 0) {
                        ContentValues values = new ContentValues();
                        values.put("printer_address", machine_addressText);
                        if (machine_usage.equals("空闲中") || machine_usage.equals("空闲") || machine_usage.equals("free")){
                            values.put("printer_usage", "free");
                        }
                        else {
                            values.put("printer_usage", "use");
                        }
                        values.put("printer_imageId", "printer1_img");
                        db.update("Printer", values, "printer_id = ?",
                                new String[] { machine_idText });
                        edit_framelayout.setVisibility(View.GONE);
                        Toast.makeText(Printers_Activity.this, "机器" + machine_idText + "修改成功!" + machine_usage, Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(Printers_Activity.this, "修改出错", Toast.LENGTH_SHORT);
                    }
//                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    initPrinters();

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
                Printers printers = printersList.get(position);
                machine_id.setText(printers.getPrinter_id());
                machine_address.setText(printers.getPrinter_address());
                if (printers.isPrinter_usage()) {
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
    private void firstInitPrinters() {
        printersList.clear();
        Cursor cursor = db.query("Printer", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.d("RegisterActivity_test", "test query begin:");
                String printer_id = cursor.getString(cursor
                        .getColumnIndex("printer_id"));
                String printer_address = cursor.getString(cursor
                        .getColumnIndex("printer_address"));
                String printer_imageId = cursor.getString(cursor
                        .getColumnIndex("printer_imageId"));
                String printer_usage = cursor.getString(cursor
                        .getColumnIndex("printer_usage"));
                if(printer_usage.equals("use") || printer_usage.equals("使用中")){
                    Printers printers = new Printers(printer_id, printer_address, R.mipmap.print_machine_img, true);
                    printersList.add(printers);
                }
                else {
                    Printers printers = new Printers(printer_id, printer_address, R.mipmap.print_machine_img, false);
                    printersList.add(printers);
                }
            }
            cursor.close();
        }
        adapter = new PrintersAdapter(printersList);
        adapter.notifyDataSetChanged();
    }

    // 向数据库中请求数据
    private void initPrinters() {
        printersList.clear();
        Cursor cursor = db.query("Printer", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String printer_id = cursor.getString(cursor
                        .getColumnIndex("printer_id"));
                String printer_address = cursor.getString(cursor
                        .getColumnIndex("printer_address"));
                String printer_imageId = cursor.getString(cursor
                        .getColumnIndex("printer_imageId"));
                String printer_usage = cursor.getString(cursor
                        .getColumnIndex("printer_usage"));
                if(printer_usage.equals("use") || printer_usage.equals("使用中")){
                    Printers printers = new Printers(printer_id, printer_address, R.mipmap.print_machine_img, true);
                    printersList.add(printers);
                }
                else {
                    Printers printers = new Printers(printer_id, printer_address, R.mipmap.print_machine_img, false);
                    printersList.add(printers);
                }
            }
            cursor.close();
        }
        adapter.notifyDataSetChanged();
    }

    // 向数据库请求查找数据
    private void searchPrinters(String content) {
        printersList.clear();
        int query_cnt = 0;
        Cursor cursor = db.query("Printer", null, "printer_id like ? or printer_address like ?",
                 new String[] { "%" + content + "%", "%" + content + "%" }, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.d("RegisterActivity_test", "test query begin:");
                String printer_id = cursor.getString(cursor
                        .getColumnIndex("printer_id"));
                String printer_address = cursor.getString(cursor
                        .getColumnIndex("printer_address"));
                String printer_imageId = cursor.getString(cursor
                        .getColumnIndex("printer_imageId"));
                String printer_usage = cursor.getString(cursor
                        .getColumnIndex("printer_usage"));
                if(printer_usage.equals("use") || printer_usage.equals("使用中")){
                    Printers printers = new Printers(printer_id, printer_address, R.mipmap.print_machine_img, true);
                    printersList.add(printers);
                }
                else {
                    Printers printers = new Printers(printer_id, printer_address, R.mipmap.print_machine_img, false);
                    printersList.add(printers);
                }
                query_cnt ++;
            }
            cursor.close();
        }
        if (query_cnt == 0) {
            // 提示框
            AlertDialog.Builder builder = new AlertDialog.Builder(Printers_Activity.this);
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
            initPrinters();
        }
//        adapter = new PrintersAdapter(printersList);
        adapter.notifyDataSetChanged();
    }

}
