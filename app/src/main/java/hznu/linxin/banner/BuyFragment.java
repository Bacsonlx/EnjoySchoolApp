package hznu.linxin.banner;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import static hznu.linxin.banner.Public_Function.public_adpter;
import static hznu.linxin.banner.Public_Function.public_price_tv;
import static hznu.linxin.banner.Public_Function.public_listview;
import javax.security.auth.callback.Callback;

import enjoyuse.Printers_Activity;

@SuppressLint("ValidFragment")
public class BuyFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<Goods> goodsList = new ArrayList<Goods>();
    private List<Goods_CardInfo> goods_cardInfoList = new ArrayList<Goods_CardInfo>();
    private ListView list_goods;
    private ListView list_goods_card;
    private FrameLayout edit_framelayout;
    private FrameLayout goods_card_fg;
    private ImageView delete_framelayout;
    private ImageView delete_goodscard_fg;
    private ImageView framelayout_img;
    private TextView framelayout_goods_name;
    private TextView framelayout_goods_price;
    private TextView goods_number;
    private ImageButton reduce_framelayout_goods;
    private ImageButton add_framelayout_goods;
    private LinearLayout shopping_card_btn;
    private TextView total_price_tv;
    private LinearLayout look_card;
    private Button settlement_btn;
    private Button clear_card;
    private GoodsCardAdapter goodsCardAdapter;
    private int i_goods_number;
    private int i_goods_position;
    private double totle_price = 0;
    public BuyFragment(ArrayList<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buy_fg_content,container,false);
        // bind()
        list_goods = (ListView) view.findViewById(R.id.list_goods);
        list_goods_card = (ListView) view.findViewById(R.id.list_goods_card);
        public_listview = (ListView) view.findViewById(R.id.list_goods_card);
        edit_framelayout = (FrameLayout) view.findViewById(R.id.edit_framelayout);
        goods_card_fg = (FrameLayout) view.findViewById(R.id.goods_card_fg);
        delete_framelayout = (ImageView) view.findViewById(R.id.delete_framelayout);
        delete_goodscard_fg = (ImageView) view.findViewById(R.id.delete_goodscard_fg);
        framelayout_img = (ImageView) view.findViewById(R.id.framelayout_img);
        framelayout_goods_name = (TextView) view.findViewById(R.id.framelayout_goods_name);
        framelayout_goods_price = (TextView) view.findViewById(R.id.framelayout_goods_price);
        goods_number = (TextView) view.findViewById(R.id.goods_number);
        reduce_framelayout_goods = (ImageButton) view.findViewById(R.id.reduce_framelayout_goods);
        add_framelayout_goods = (ImageButton) view.findViewById(R.id.add_framelayout_goods);
        shopping_card_btn = (LinearLayout) view.findViewById(R.id.shopping_card_btn);
        total_price_tv = (TextView) view.findViewById(R.id.total_price_tv);
        public_price_tv = (TextView) view.findViewById(R.id.total_price_tv);
        look_card = (LinearLayout) view.findViewById(R.id.look_card);
        settlement_btn = (Button) view.findViewById(R.id.settlement_btn);
        clear_card = (Button) view.findViewById(R.id.clear_card);
        // List
        GoodsAdapter goodsAdapter = new GoodsAdapter(goodsList, getActivity());
        list_goods.setAdapter(goodsAdapter);
        goodsCardAdapter = new GoodsCardAdapter(goods_cardInfoList, getActivity());
        public_adpter = goodsCardAdapter;
        list_goods_card.setAdapter(goodsCardAdapter);

        // 点击子项的选规格按钮
        goodsAdapter.setOnItemClickListener(new GoodsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                edit_framelayout.setVisibility(View.VISIBLE);
                // 获取商品数量
                goods_number.setText("0"); // 初始值为0
                i_goods_number = 0;
                i_goods_position = position;
                // 赋值
                Goods current_goods = goodsList.get(position);
                framelayout_goods_name.setText(current_goods.getGoodsName());
                framelayout_goods_price.setText(current_goods.getGoodsPrice());
                framelayout_img.setImageResource(current_goods.getImageId());
            }
        });


        // 点击删除按钮
        delete_framelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_framelayout.setVisibility(View.GONE);
            }
        });
        // 隐藏查看购物车
        delete_goodscard_fg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goods_card_fg.setVisibility(View.GONE);
            }
        });
        // 点击减少商品
        reduce_framelayout_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i_goods_number > 0) {
                    i_goods_number -= 1;
                    goods_number.setText("" + i_goods_number);
                }
            }
        });
        // 点击增加商品
        add_framelayout_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i_goods_number += 1;
                goods_number.setText("" + i_goods_number);
            }
        });

        // 点击加入购物车
        shopping_card_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double singel_price = Double.valueOf(framelayout_goods_price.getText().toString().split("￥")[1]);
                totle_price += singel_price * i_goods_number;
                total_price_tv.setText("".format("%.2f", totle_price) + "元");
                Goods_CardInfo goods_cardInfo = null;
                if (i_goods_number != 0) {
                    // 合并
//                    for (int i = 0;  i < goods_cardInfoList.size(); i++) {
//                        if (framelayout_goods_name.getText().toString().equals(goods_cardInfoList.get(i).getGoodsName())) {
//
//                        }
//                    }
                    goods_cardInfo = new Goods_CardInfo(framelayout_goods_name.getText().toString(), framelayout_goods_price.getText().toString(),
                            i_goods_position, i_goods_number);
                }

                goods_cardInfoList.add(goods_cardInfo);
                edit_framelayout.setVisibility(View.GONE);
            }
        });
        // 点击查看购物车
        look_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo
                goods_card_fg.setVisibility(View.VISIBLE);
                goodsCardAdapter.notifyDataSetChanged();
                for (int i = 0;  i < goods_cardInfoList.size(); i++) {
                    if (goods_cardInfoList.get(i).getNum() == 0) {
                        goods_cardInfoList.remove(i);
                    }
//                    Log.d("Buybuy", public_price_tv.getText() + goods_cardInfoList.get(i).getGoodsName() + " " + goods_cardInfoList.get(i).getImageId()
//                     + " " + goods_cardInfoList.get(i).getNum()+ " " + goods_cardInfoList.get(i).getGoodsPrice());

                }
            }
        });

        // 清空购物车
        clear_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goods_cardInfoList.clear();
                public_listview.setAdapter(public_adpter);
                totle_price = 0;
                total_price_tv.setText("0.00元");
            }
        });

        // 点击去结算
        settlement_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("结算");
                builder.setMessage("共计" + total_price_tv.getText().toString());
                builder.setCancelable(false);
                builder.setPositiveButton("确认",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "ClickTest", Toast.LENGTH_LONG).show();
    }
}
