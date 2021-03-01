package hznu.linxin.banner;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import enjoyuse.Kitchen_Activity;
import enjoyuse.Printers;
import enjoyuse.PrintersAdapter;
import enjoyuse.Printers_Activity;
import enjoyuse.SecondHandBooks_Activity;
import enjoyuse.Washers_Activity;

import static hznu.linxin.banner.Public_Function.db;

/**
 * Created by Jay on 2015/8/28 0028.
 */
@SuppressLint("ValidFragment")
public class PlayFragment extends Fragment {

    private Button printer_more;
    private Button washer_more;
    private Button secondbook_more;
    private Button kitchen_more;
    private TextView washmachine_free;
    private TextView washmachine_use;
    private View printerL1;
    private View washerL2;
    private View booksL3;
    private View kitchenL4;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.play_fg_content,container,false);
        // bind()
        printerL1 = (View) view.findViewById(R.id.L1);
        washerL2 = (View) view.findViewById(R.id.L2);
        booksL3 = (View) view.findViewById(R.id.L3);
        kitchenL4 = (View) view.findViewById(R.id.L4);
        kitchen_more = (Button) view.findViewById(R.id.kitchen_more);
        printer_more = (Button) view.findViewById(R.id.printer_more);
        washer_more = (Button) view.findViewById(R.id.washer_more);
        secondbook_more = (Button) view.findViewById(R.id.secondbook_more);
        washmachine_free = (TextView) view.findViewById(R.id.washmachine_free);
        washmachine_use = (TextView) view.findViewById(R.id.washmachine_use);

        // 查询洗衣机的使用情况
        Cursor cursor = db.query("Washer", null, "wash_usage=?", new String[] {"free"}, null, null, null);
        int count = cursor.getCount();
        washmachine_free.setText("空闲：" + count + "台");
        cursor = db.query("Washer", null, "wash_usage=?", new String[] {"use"}, null, null, null);
        count = cursor.getCount();
        washmachine_use.setText("使用中：" + count + "台");
        cursor.close();

        // 打印机列表
        printerL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PlayFragmentTest", "onClick L1!!!");
                Intent intent = new Intent(getActivity(), Printers_Activity.class);
                startActivity(intent);
            }
        });
        printer_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PlayFragmentTest", "onClick L1!!!");
                Intent intent = new Intent(getActivity(), Printers_Activity.class);
                startActivity(intent);
            }
        });
        // 洗衣机列表
        washerL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PlayFragmentTest", "onClick L2!!!");
                Intent intent = new Intent(getActivity(), Washers_Activity.class);
                startActivity(intent);

            }
        });
        washer_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PlayFragmentTest", "onClick L2!!!");
                Intent intent = new Intent(getActivity(), Washers_Activity.class);
                startActivity(intent);

            }
        });
        // 二手书列表
        booksL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PlayFragmentTest", "onClick L3!!!");
                Intent intent = new Intent(getActivity(), SecondHandBooks_Activity.class);
                startActivity(intent);
            }
        });
        secondbook_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PlayFragmentTest", "onClick L2!!!");
                Intent intent = new Intent(getActivity(), SecondHandBooks_Activity.class);
                startActivity(intent);

            }
        });

        // 共享厨房列表
        kitchenL4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PlayFragmentTest", "onClick L4!!!");
                Intent intent = new Intent(getActivity(), Kitchen_Activity.class);
                startActivity(intent);
            }
        });
        kitchen_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PlayFragmentTest", "onClick L4!!!");
                Intent intent = new Intent(getActivity(), Kitchen_Activity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}
