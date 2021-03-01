package hznu.linxin.banner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import static hznu.linxin.banner.Public_Function.*;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private SQLiteDatabase myDB;
    private long exit_time;
    private boolean passwdVisable = false;

    private Button login_btn;
    private Button del_username_btn;
    private Button passwd_eye_btn;
    private EditText usernameEdit;
    private EditText passwdEdit;
    private CheckBox rememberPass;
    private TextView registerTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);

        // bind()
        login_btn = (Button) findViewById(R.id.login_btn);
        del_username_btn = (Button) findViewById(R.id.del_username_btn);
        passwd_eye_btn = (Button) findViewById(R.id.passwd_eye_btn);
        usernameEdit = (EditText) findViewById(R.id.login_input_username);
        passwdEdit = (EditText) findViewById(R.id.login_input_password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pwd);
        registerTv = (TextView) findViewById(R.id.register_btn);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        // 判断是否记住密码
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            usernameEdit.setText(account);
            passwdEdit.setText(password);
            rememberPass.setChecked(true);
        }

        // 点击登录按钮事件
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = usernameEdit.getText().toString();
                String password = passwdEdit.getText().toString();
                myDB = openDB(LoginActivity.this);
                // 校验用户名和密码是否正确
                Cursor cursor = db.query("Admin", null, "username=? and passwd=?", new String[] { account, password }, null, null, null);
                if (cursor != null){
                    // 如果校验成功
                    if (cursor.getCount() != 0) {
                        Toast.makeText(LoginActivity.this,  "登录成功", Toast.LENGTH_LONG).show();
                        Log.d("LoginActivity_test", "Login_success");
                        editor = pref.edit();
                        if (rememberPass.isChecked()) {
                            editor.putBoolean("remember_password", true);
                            editor.putString("account", account);
                            editor.putString("password", password);
                        } else {
                            editor.clear();
                        }
                        editor.commit();
                        current_user = account;
                        finish();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "账号或密码错误!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // 点击注册按钮事件
        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // 清空username
        del_username_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameEdit.setText("");
            }
        });

        // 密码是否可见
        passwd_eye_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!passwdVisable){
                    passwdEdit.setInputType(InputType.TYPE_CLASS_TEXT);
                    passwdVisable = true;
                    passwd_eye_btn.setBackgroundResource(R.mipmap.eye);
                }
                else {
                    passwdEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwdVisable = false;
                    passwd_eye_btn.setBackgroundResource(R.mipmap.close_eye);
                }
            }
        });
    }


    @Override
    //按两次back键退出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //获取按键并比较两次按back的时间大于2s不退出，否则退出
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exit_time > 2000) {
                Toast.makeText(LoginActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exit_time = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
