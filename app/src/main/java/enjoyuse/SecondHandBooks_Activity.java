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
import static hznu.linxin.banner.Public_Function.checkModifySecondBook;
import static hznu.linxin.banner.Public_Function.checkPrinter;
import static hznu.linxin.banner.Public_Function.checkSecondBook;
import static hznu.linxin.banner.Public_Function.db;

public class SecondHandBooks_Activity extends AppCompatActivity {

    private List<SecondHand_Books> secondHandBooksList = new ArrayList<SecondHand_Books>();
    SecondHand_BooksAdapter adapter;
    private Button go_back;
    private Button add_btn;
    private FrameLayout edit_framelayout2;
    private ImageView delete_framelayout2;
    private ImageView edit_framelayout_img2;
    private EditText bookIdEdit;
    private EditText bookNameEdit;
    private EditText bookPriceEdit;
    private Button addDB_btn2;
    private EditText edittext;
    private ImageView search_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_recycleview);
        TextView title = (TextView) findViewById(R.id.tv_title);
        title.setText("图书交易");
        // init();
        firstInitSecondHandBooks();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // ---------瀑布布局----------
        StaggeredGridLayoutManager layoutManager=new
                StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SecondHand_BooksAdapter(secondHandBooksList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        // bind
        go_back = (Button) findViewById(R.id.go_back);
        add_btn = (Button) findViewById(R.id.add_button);
        edit_framelayout2 = (FrameLayout) findViewById(R.id.edit_framelayout2);
        delete_framelayout2 = (ImageView) findViewById(R.id.delete_framelayout2);
        edit_framelayout_img2 = (ImageView) findViewById(R.id.edit_framelayout_img2);
        bookNameEdit = (EditText) findViewById(R.id.bookNameEdit);
        bookPriceEdit = (EditText) findViewById(R.id.bookPriceEdit);
        bookIdEdit = (EditText) findViewById(R.id.bookIdEdit);
        addDB_btn2 = (Button) findViewById(R.id.addDB_btn2);
        edittext = (EditText) findViewById(R.id.edittext);
        search_btn = (ImageView) findViewById(R.id.search_btn);


        // 点击返回按钮事件
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
                searchSecondHandBooks(search_content);
            }
        });

        // 点击添加事件
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_framelayout2.setVisibility(View.VISIBLE);
                edit_framelayout_img2.setImageResource(R.mipmap.book_img);
                bookIdEdit.setText("");
                bookNameEdit.setText("");
                bookPriceEdit.setText("");
                addDB_btn2.setText("添加");
            }
        });
        // 实现添加到数据库
        addDB_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookIdText = bookIdEdit.getText().toString();
                String bookNameText = bookNameEdit.getText().toString();
                String bookPriceText = bookPriceEdit.getText().toString();
                if (addDB_btn2.getText().equals("添加")) {
                    if (checkSecondBook(bookIdText, bookNameText, bookPriceText) == 1) {
                        Toast.makeText(SecondHandBooks_Activity.this, "图书编号" + bookIdText + "已存在, 请更换编号", Toast.LENGTH_LONG).show();
                    }
                    else if (checkSecondBook(bookIdText, bookNameText, bookPriceText) == 2) {
                        Toast.makeText(SecondHandBooks_Activity.this, "图书编号" + bookIdText + "不合法,请输入3-10位数字", Toast.LENGTH_LONG).show();
                    }
                    else if (checkSecondBook(bookIdText, bookNameText, bookPriceText) == 0){
                        ContentValues values = new ContentValues();
                        values.put("book_id", bookIdText);
                        values.put("book_name", bookNameText);
                        values.put("book_price", bookPriceText);
                        values.put("book_imageId", "book_image");
                        db.insert("SecondHandBooks", null, values);
                        edit_framelayout2.setVisibility(View.GONE);
                        Toast.makeText(SecondHandBooks_Activity.this, "图书" + bookNameText + "插入成功!", Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                        initSecondHandBooks();
                    }
                }
                else if (addDB_btn2.getText().equals("修改")) {
                    if (checkModifySecondBook(bookIdText, bookNameText, bookPriceText) == 0) {
                        ContentValues values = new ContentValues();
                        values.put("book_price", bookPriceText);
                        values.put("book_name", bookNameText);
                        values.put("book_imageId", "book_image");
                        db.update("SecondHandBooks", values, "book_id = ?",
                                new String[] { bookIdText });
                        edit_framelayout2.setVisibility(View.GONE);
                        Toast.makeText(SecondHandBooks_Activity.this, "图书" + bookNameText + "修改成功!" + bookIdText, Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                        initSecondHandBooks();
                    }
                    else {
                        Toast.makeText(SecondHandBooks_Activity.this, "修改出错", Toast.LENGTH_SHORT);
                    }
                }
                adapter.notifyDataSetChanged();
                initSecondHandBooks();
            }
        });
        // 点击隐藏FrameLayout
        delete_framelayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_framelayout2.setVisibility(View.GONE);
            }
        });
        // 点击子项的修改按钮
        adapter.setOnItemClickListener(new SecondHand_BooksAdapter.OnItemClickListener(){
            @Override
            public void onClick(int position) {
                edit_framelayout2.setVisibility(View.VISIBLE);
                SecondHand_Books secondHand_books = secondHandBooksList.get(position);
                bookIdEdit.setText(secondHand_books.getBook_id());
                bookNameEdit.setText(secondHand_books.getBook_name());
                bookPriceEdit.setText(secondHand_books.getBook_price());
                addDB_btn2.setText("修改");
            }
        });
    }


    private void firstInitSecondHandBooks() {
        secondHandBooksList.clear();
        Cursor cursor = db.query("SecondHandBooks", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.d("SecondHandActivity", "Test");
                String book_id = cursor.getString(cursor
                        .getColumnIndex("book_id"));
                String book_name = cursor.getString(cursor
                        .getColumnIndex("book_name"));
                String book_imageId = cursor.getString(cursor
                        .getColumnIndex("book_imageId"));
                String book_price = cursor.getString(cursor
                        .getColumnIndex("book_price"));
                Log.d("SecondHandActivity", book_id + book_name + book_imageId + book_price);
                SecondHand_Books secondHand_books = new SecondHand_Books(book_id, book_name, R.mipmap.book_img, book_price);
                secondHandBooksList.add(secondHand_books);
            }
            cursor.close();
        }
        adapter = new SecondHand_BooksAdapter(secondHandBooksList);
        adapter.notifyDataSetChanged();
    }
    // 向数据库中请求数据
    private void initSecondHandBooks() {
        secondHandBooksList.clear();
        Cursor cursor = db.query("SecondHandBooks", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.d("SecondHandActivity", "Test");
                String book_id = cursor.getString(cursor
                        .getColumnIndex("book_id"));
                String book_name = cursor.getString(cursor
                        .getColumnIndex("book_name"));
                String book_imageId = cursor.getString(cursor
                        .getColumnIndex("book_imageId"));
                String book_price = cursor.getString(cursor
                        .getColumnIndex("book_price"));
                Log.d("SecondHandActivity", book_id + book_name + book_imageId + book_price);
                SecondHand_Books secondHand_books = new SecondHand_Books(book_id, book_name, R.mipmap.book_img, book_price);
                secondHandBooksList.add(secondHand_books);
            }
            cursor.close();
        }
//        adapter = new SecondHand_BooksAdapter(secondHandBooksList);
        adapter.notifyDataSetChanged();
    }

    //
    private void searchSecondHandBooks(String content) {
        secondHandBooksList.clear();
        int query_cnt = 0;
        Cursor cursor = db.query("SecondHandBooks", null, "book_id like ? or book_name like ?", new String[] { "%" + content + "%", "%" + content + "%" }, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
//                Log.d("SecondHandActivity", "Test");
                String book_id = cursor.getString(cursor
                        .getColumnIndex("book_id"));
                String book_name = cursor.getString(cursor
                        .getColumnIndex("book_name"));
                String book_imageId = cursor.getString(cursor
                        .getColumnIndex("book_imageId"));
                String book_price = cursor.getString(cursor
                        .getColumnIndex("book_price"));
                Log.d("SecondHandActivity", book_id + book_name + book_imageId + book_price);
                SecondHand_Books secondHand_books = new SecondHand_Books(book_id, book_name, R.mipmap.book_img, book_price);
                secondHandBooksList.add(secondHand_books);
                query_cnt++;
            }
            cursor.close();
        }
        if (query_cnt == 0) {
            // 提示框
            AlertDialog.Builder builder = new AlertDialog.Builder(SecondHandBooks_Activity.this);
            builder.setTitle("查询结果");
            builder.setMessage("未查询到结果，请输入编号或地址");
            builder.setCancelable(false);
            builder.setPositiveButton("确认",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    });
            builder.show();
            initSecondHandBooks();
        }
//        adapter = new SecondHand_BooksAdapter(secondHandBooksList);
        adapter.notifyDataSetChanged();
    }
}
