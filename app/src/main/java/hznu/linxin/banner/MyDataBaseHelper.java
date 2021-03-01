package hznu.linxin.banner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_ADMIN = "create table Admin ("
            + "username text primary key, "
            + "passwd text, "
            + "realname text, "
            + "email text, "
            + "age text, "
            + "tele text, "
            + "gender text, "
            + "headimg text)";
    public static final String CREATE_PRINTER = "create table Printer ("
            + "printer_id text primary key, "
            + "printer_address text, "
            + "printer_usage text, "
            + "printer_imageId text)";
    public static final String CREATE_WASHER = "create table Washer ("
            + "wash_id text primary key, "
            + "wash_address text, "
            + "wash_usage text, "
            + "wash_imageId text, "
            + "wash_time text)";
    public static final String CREATE_SECONDBOOK = "create table SecondHandBooks ("
            + "book_id text primary key, "
            + "book_name text, "
            + "book_price text, "
            + "book_imageId text)";
    public static final String CREATE_KITCHEN = "create table Kitchen ("
            + "kitchen_id text primary key, "
            + "kitchen_address text, "
            + "kitchen_imageId text, "
            + "kitchen_wait text, "
            + "kitchen_time text)";
    private Context mContext;


    public MyDataBaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ADMIN);
        db.execSQL(CREATE_PRINTER);
        db.execSQL(CREATE_WASHER);
        db.execSQL(CREATE_SECONDBOOK);
        db.execSQL(CREATE_KITCHEN);
        Toast.makeText(mContext, "数据库创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        switch (oldVersion) {
//            case 1:
//                db.execSQL(CREATE_ADMIN);
//                db.execSQL(CREATE_PRINTER);
//            case 2:
//                db.execSQL("alter table Book add column category_id integer");
//            default:
//        }
    }
}
