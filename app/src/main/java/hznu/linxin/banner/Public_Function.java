package hznu.linxin.banner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import enjoyuse.Printers;
import enjoyuse.PrintersAdapter;

// 公共方法
public class Public_Function {
    // 表示当前登录用户
    public static String current_user;
    // 数据库
    public static MyDataBaseHelper dbHelper;
    public static SQLiteDatabase db;

    public static TextView public_price_tv;
    public static ListView public_listview;
    public static GoodsCardAdapter public_adpter;
    public static PrintersAdapter public_printer_adpter;
    public static List<Printers> public_printersList;
    // 新建或打开数据库
    public static SQLiteDatabase openDB(Context context) {
        // MyDatabaseHelper的四个参数(Context, 数据库名, 查询数据时返回的一个自定义Cursor 一般设置为null， 当前版本号)
        dbHelper = new MyDataBaseHelper(context, "enjoy_school.db", null, 2);
        // getWritableDatabase()方法创建或打开一个现有的数据库
        db = dbHelper.getWritableDatabase();
        return db;
    }
    // 校验用户名
    public static boolean checkUserName(String username){
        Pattern pattern=Pattern.compile("^([\\u4e00-\\u9fa5\\d_]{2,7})$|^([a-zA-Z0-9_]{2,14})$");
        Matcher matcher=pattern.matcher(username);
        return matcher.matches();
    }
    // 校验手机号码
    public static boolean checkPhoneNumber(String phoneNumber){
        Pattern pattern=Pattern.compile("^(1[0-9]{10})$");
        Matcher matcher=pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    // 校验年龄
    public static boolean checkAge(String age){
        Pattern pattern=Pattern.compile("^((1[0-1]|[1-9])?\\d|120)$");
        Matcher matcher=pattern.matcher(age);
        return matcher.matches();
    }
    // 校验邮箱
    public static boolean checkEmail(String email) {
        Pattern pattern=Pattern.compile("^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$");
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();
    }

    // 校验插入的打印机
    public static int checkPrinter(String name, String address, String usage) {
        Cursor cursor = db.query("Printer", null, "printer_id=?", new String[] {name}, null, null, null);
        int count = cursor.getCount();
        if (count > 0 ) {
            return 1;
        }
        Pattern pattern=Pattern.compile("^([0-9]{3,10})$");
        Matcher matcher=pattern.matcher(name);
        if (!matcher.matches()) {
            return 2;
        }
        return 0;
    }
    // 校验修改的打印机
    public static int checkModifyPrinter(String name, String address, String usage) {
        return 0;
    }
    // 校验插入的洗衣机
    public static int checkWasher(String name, String address, String usage) {
        Cursor cursor = db.query("Washer", null, "wash_id=?", new String[] {name}, null, null, null);
        int count = cursor.getCount();
        if (count > 0 ) {
            return 1;
        }
        Pattern pattern=Pattern.compile("^([0-9]{3,10})$");
        Matcher matcher=pattern.matcher(name);
        if (!matcher.matches()) {
            return 2;
        }
        return 0;
    }
    // 校验修改的洗衣机
    public static int checkModifyWasher(String name, String address, String usage) {
        return 0;
    }
    // 校验添加的图书
    public static int checkSecondBook(String id, String name, String price) {
        Cursor cursor = db.query("SecondHandBooks", null, "book_id=?", new String[] {id}, null, null, null);
        int count = cursor.getCount();
        if (count > 0 ) {
            return 1;
        }
        Pattern pattern=Pattern.compile("^([0-9]{3,10})$");
        Matcher matcher=pattern.matcher(id);
        if (!matcher.matches()) {
            return 2;
        }
        return 0;
    }
    // 校验修改的图书
    public static int checkModifySecondBook(String name, String address, String usage) {
        return 0;
    }

    // 校验插入的厨房
    public static int checkKitchen(String id, String address, String wait, String time) {
        Cursor cursor = db.query("Kitchen", null, "kitchen_id=?", new String[] {id}, null, null, null);
        int count = cursor.getCount();
        if (count > 0 ) {
            return 1;
        }
        Pattern pattern=Pattern.compile("^([0-9]{3,10})$");
        Matcher matcher=pattern.matcher(id);
        if (!matcher.matches()) {
            return 2;
        }
        return 0;
    }
    // 校验修改的厨房
    public static int checkModifyKitchen(String id, String address, String wait, String time) {
        return 0;
    }
}
