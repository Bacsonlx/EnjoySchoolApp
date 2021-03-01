package hznu.linxin.banner;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static hznu.linxin.banner.Public_Function.*;

public class RegisterActivity extends AppCompatActivity {
    SQLiteDatabase myDB;
    // 按钮
    private Button register_btn;
    private Button goback_btn;
    // 文本框
    private EditText register_input_username;
    private EditText register_input_password;
    private EditText confirm_input_password;
    private EditText register_input_realname;
    private EditText register_input_mobile;
    private EditText register_input_email;
    // 字符串
    String register_username = "";
    String register_passwd = "";
    String confirm_passwd = "";
    String register_realname = "";
    String register_mobile = "";
    String register_email = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // bind()
        register_btn = (Button) findViewById(R.id.register_btn);
        goback_btn = (Button) findViewById(R.id.back_btn);
        register_input_username = (EditText) findViewById(R.id.register_input_username);
        register_input_password = (EditText) findViewById(R.id.register_input_password);
        confirm_input_password = (EditText) findViewById(R.id.confirm_input_password);
        register_input_realname = (EditText) findViewById(R.id.register_input_realname);
        register_input_mobile = (EditText) findViewById(R.id.register_input_mobile);
        register_input_email = (EditText) findViewById(R.id.register_input_email);

        // test
        register_input_username.setText("lnxnii");
        register_input_password.setText("123456");
        confirm_input_password.setText("123456");
        register_input_realname.setText("林鑫");
        register_input_mobile.setText("15005961707");
        register_input_email.setText("1160950618@qq.com");

        // 点击注册按钮
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // init()
                register_username = register_input_username.getText().toString();
                register_passwd = register_input_password.getText().toString();
                confirm_passwd = confirm_input_password.getText().toString();
                register_realname = register_input_realname.getText().toString();
                register_mobile = register_input_mobile.getText().toString();
                register_email = register_input_email.getText().toString();

                // Output Test
                Log.d("RegisterActivity_Test", "" + checkPhoneNumber(register_mobile));
                myDB = openDB(RegisterActivity.this);
                // 校验信息是否合法：
                if (check_Invalid()){
                    // 校验用户名是否已存在
                    Cursor cursor = db.query("Admin", null, "username=?", new String[] {register_username}, null, null, null);
                    if (cursor != null){
                        if (cursor.getCount() != 0) {
                            Toast.makeText(RegisterActivity.this, register_username + "用户名已经存在", Toast.LENGTH_LONG).show();
                            Log.d("RegisterActivity_test", "cursor is not null! insert DB fail");
                        }
                        else{
                            // 注意在这里插入所有表的数据
                            // ---------------插入用户信息
                            ContentValues values = new ContentValues();
                            values.put("username", register_username);
                            values.put("passwd", register_passwd);
                            values.put("realname", register_realname);
                            values.put("tele", register_mobile);
                            values.put("email", register_email);
                            values.put("age", "");
                            values.put("gender", "");
                            values.put("headimg", "defalut");
                            db.insert("Admin", null, values);
                            // ---------------插入打印机数据
                            values.clear();
                            values.put("printer_id", "0001");
                            values.put("printer_address", "精思苑2号楼");
                            values.put("printer_usage", "use");
                            values.put("printer_imageId", "printer1_img");
                            db.insert("Printer", null, values);
                            values.clear();
                            values.put("printer_id", "0002");
                            values.put("printer_address", "精思苑2号楼");
                            values.put("printer_usage", "free");
                            values.put("printer_imageId", "printer1_img");
                            db.insert("Printer", null, values);
                            values.clear();
                            values.put("printer_id", "0003");
                            values.put("printer_address", "精思苑2号楼");
                            values.put("printer_usage", "free");
                            values.put("printer_imageId", "printer1_img");
                            db.insert("Printer", null, values);
                            values.clear();
                            values.put("printer_id", "0004");
                            values.put("printer_address", "精思苑2号楼");
                            values.put("printer_usage", "use");
                            values.put("printer_imageId", "printer1_img");
                            db.insert("Printer", null, values);
                            values.clear();
                            values.put("printer_id", "0005");
                            values.put("printer_address", "精思苑2号楼");
                            values.put("printer_usage", "free");
                            values.put("printer_imageId", "printer1_img");
                            db.insert("Printer", null, values);
                            values.clear();
                            values.put("printer_id", "0006");
                            values.put("printer_address", "精思苑2号楼");
                            values.put("printer_usage", "use");
                            values.put("printer_imageId", "printer1_img");
                            db.insert("Printer", null, values);
                            values.clear();
                            values.put("printer_id", "0007");
                            values.put("printer_address", "精思苑2号楼");
                            values.put("printer_usage", "use");
                            values.put("printer_imageId", "printer1_img");
                            db.insert("Printer", null, values);


                            //-------------插入洗衣机数据
                            values.clear();
                            values.put("wash_id", "0001");
                            values.put("wash_address", "精思苑2号楼");
                            values.put("wash_usage", "use");
                            values.put("wash_imageId", "washer1_img");
                            values.put("wash_time", "40:00");
                            db.insert("Washer", null, values);
                            values.clear();
                            values.put("wash_id", "0002");
                            values.put("wash_address", "精思苑2号楼");
                            values.put("wash_usage", "free");
                            values.put("wash_imageId", "washer1_img");
                            values.put("wash_time", "00:00");
                            db.insert("Washer", null, values);
                            values.clear();
                            values.put("wash_id", "0003");
                            values.put("wash_address", "精思苑2号楼");
                            values.put("wash_usage", "free");
                            values.put("wash_imageId", "washer1_img");
                            values.put("wash_time", "00:00");
                            db.insert("Washer", null, values);
                            values.clear();
                            values.put("wash_id", "0004");
                            values.put("wash_address", "精思苑2号楼");
                            values.put("wash_usage", "free");
                            values.put("wash_imageId", "washer1_img");
                            values.put("wash_time", "00:00");
                            db.insert("Washer", null, values);
                            values.clear();
                            values.put("wash_id", "0005");
                            values.put("wash_address", "精思苑2号楼");
                            values.put("wash_usage", "free");
                            values.put("wash_imageId", "washer1_img");
                            values.put("wash_time", "00:00");
                            db.insert("Washer", null, values);
                            values.clear();
                            values.put("wash_id", "0006");
                            values.put("wash_address", "精思苑2号楼");
                            values.put("wash_usage", "free");
                            values.put("wash_imageId", "washer1_img");
                            values.put("wash_time", "00:00");
                            db.insert("Washer", null, values);

                            // -------------插入图书数据
                            values.clear();
                            values.put("book_id", "0001");
                            values.put("book_name", "JSP");
                            values.put("book_price", "28");
                            values.put("book_imageId", "book_image");
                            db.insert("SecondHandBooks", null, values);
                            values.clear();
                            values.put("book_id", "0002");
                            values.put("book_name", "第一行代码");
                            values.put("book_price", "28");
                            values.put("book_imageId", "book_image");
                            db.insert("SecondHandBooks", null, values);
                            values.clear();
                            values.put("book_id", "0003");
                            values.put("book_name", "操作系统");
                            values.put("book_price", "28");
                            values.put("book_imageId", "book_image");
                            db.insert("SecondHandBooks", null, values);
                            values.clear();
                            values.put("book_id", "0004");
                            values.put("book_name", "计算机网络");
                            values.put("book_price", "28");
                            values.put("book_imageId", "book_image");
                            db.insert("SecondHandBooks", null, values);

                            // 插入共享厨房数据
                            values.clear();
                            values.put("kitchen_id", "0001");
                            values.put("kitchen_address", "精思苑2号楼");
                            values.put("kitchen_wait", "3");
                            values.put("kitchen_time", "00:00");
                            values.put("kitchen_imageId", "kitchen_image");
                            db.insert("Kitchen", null, values);
                            values.clear();
                            values.put("kitchen_id", "0002");
                            values.put("kitchen_address", "精思苑2号楼");
                            values.put("kitchen_wait", "3");
                            values.put("kitchen_time", "00:00");
                            values.put("kitchen_imageId", "kitchen_image");
                            db.insert("Kitchen", null, values);
                            values.clear();
                            values.put("kitchen_id", "0003");
                            values.put("kitchen_address", "精思苑2号楼");
                            values.put("kitchen_wait", "3");
                            values.put("kitchen_time", "00:00");
                            values.put("kitchen_imageId", "kitchen_image");
                            db.insert("Kitchen", null, values);
                            values.clear();
                            values.put("kitchen_id", "0004");
                            values.put("kitchen_address", "精思苑2号楼");
                            values.put("kitchen_wait", "3");
                            values.put("kitchen_time", "00:00");
                            values.put("kitchen_imageId", "kitchen_image");
                            db.insert("Kitchen", null, values);

                            Log.d("RegisterActivity_test", "cursor is null! insert DB success");
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                        }
                        cursor.close();
                    }

                }

            }
        });

        // 点击返回登录按钮
        goback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    // 校验注册信息
    public boolean check_Invalid() {

        if(register_username.length() == 0 || register_passwd.length() == 0 || confirm_passwd.length() == 0 ||
            register_realname.length() == 0|| register_mobile.length() == 0 || register_email.length() == 0) {
            Toast.makeText(RegisterActivity.this, "填写字段不能为空", Toast.LENGTH_LONG).show();
            return false;
        }
        else if (!checkUserName(register_username)) {
            Toast.makeText(RegisterActivity.this, "用户名的长度为2-14个英文或2-7个汉字（数字、下划线）", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (5 > register_passwd.length() || register_passwd.length() > 14) {
            Toast.makeText(RegisterActivity.this, "密码的长度为6-14个字符", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!register_passwd.equals(confirm_passwd)) {
            Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            confirm_input_password.setText("");
            return false;
        }
        else if (!checkPhoneNumber(register_mobile)){
            Toast.makeText(RegisterActivity.this, "请输入以1开头的11位手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
//        else if (!checkEmail(register_email)) {
//            Toast.makeText(RegisterActivity.this, "请输入正确的邮箱", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }
}
