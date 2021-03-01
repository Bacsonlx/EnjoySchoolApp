package hznu.linxin.banner;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import enjoyuse.Printers_Activity;
import enjoyuse.SecondHandBooks_Activity;
import enjoyuse.Washers;
import enjoyuse.Washers_Activity;

import static hznu.linxin.banner.Public_Function.current_user;
import static hznu.linxin.banner.Public_Function.db;

/**
 * Created by Jay on 2015/8/28 0028.
 */
@SuppressLint("ValidFragment")
public class MyFragment extends Fragment {
    private TextView exitTv;
    private TextView textname;
    private RelativeLayout relspecialist;
    private RelativeLayout relconsult;
    private RelativeLayout relpourout;
    private RelativeLayout relcourse;
    private RelativeLayout reltest;
    public MyFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);

        // bind()
        exitTv = (TextView) view.findViewById(R.id.text49);
        relconsult = (RelativeLayout) view.findViewById(R.id.relconsult);
        relspecialist = (RelativeLayout) view.findViewById(R.id.relspecialist);
        relpourout = (RelativeLayout) view.findViewById(R.id.relpourout);
        relcourse = (RelativeLayout) view.findViewById(R.id.relcourse);
        reltest = (RelativeLayout) view.findViewById(R.id.reltest);
        textname = (TextView) view.findViewById(R.id.textname);
        textname.setText(current_user);
        // 点击打印空间按钮
        relconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Printers_Activity.class);
                startActivity(intent);
            }
        });
        // 点击洗衣服务按钮
        relpourout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Washers_Activity.class);
                startActivity(intent);
            }
        });
        // 点击旧书交易按钮
        relcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SecondHandBooks_Activity.class);
                startActivity(intent);
            }
        });
        // 点击共享厨房按钮
        reltest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo

            }
        });
        // 点击退出按钮
        relspecialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 提示框
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("退出");
                builder.setMessage("确定退出吗?");
                builder.setCancelable(false);
                builder.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing

                            }
                        });
                builder.setPositiveButton("确认",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                builder.show();
            }
        });
        return view;
    }
}
