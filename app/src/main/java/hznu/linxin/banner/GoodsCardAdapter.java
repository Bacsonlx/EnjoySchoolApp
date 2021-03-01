package hznu.linxin.banner;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import hznu.linxin.banner.BuyFragment;
import java.util.List;

import javax.security.auth.callback.Callback;

import enjoyuse.Kitchen_Adapter;

import static hznu.linxin.banner.Public_Function.public_adpter;
import static hznu.linxin.banner.Public_Function.public_listview;
import static hznu.linxin.banner.Public_Function.public_price_tv;

public class GoodsCardAdapter extends BaseAdapter implements View.OnClickListener{
    private List<Goods_CardInfo> mData;
    private Context mContext;
    private ViewHolder viewHolder;
    private int cur_position;
    private double totle_price = 0;
    private double single_price = 0;
//    private GoodsCardAdapter.OnItemClickListener mOnItemClickListener;

    public GoodsCardAdapter(List<Goods_CardInfo> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

//    // 自定义onItemClickListener监听器,为adapter的内部接口
//    public interface OnItemClickListener{
//        void addBtnClick(int position);
//        void reduceBtnClick(int position);
//    }
//    //为adapter设置监听器
//    public void setOnItemClickListener(GoodsCardAdapter.OnItemClickListener onItemClickListener ){
//        this.mOnItemClickListener=onItemClickListener;
//
//    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.goods_card_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.goods_card_name = (TextView) convertView.findViewById(R.id.goods_card_name);
            viewHolder.goods_card_price = (TextView) convertView.findViewById(R.id.goods_card_price);
            viewHolder.goods_card_number = (TextView) convertView.findViewById(R.id.goods_card_number);
            viewHolder.goods_card_image = (ImageView) convertView.findViewById(R.id.goods_card_image);
            viewHolder.reduce_goods = (ImageButton) convertView.findViewById(R.id.reduce_goods);
            viewHolder.add_goods = (ImageButton) convertView.findViewById(R.id.add_goods);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.goods_card_name.setText(mData.get(position).getGoodsName());
        viewHolder.goods_card_price.setText(mData.get(position).getGoodsPrice());
        viewHolder.goods_card_number.setText(String.valueOf(mData.get(position).getNum()));

        cur_position = mData.get(position).getImageId();
        if (cur_position == 0) {
            viewHolder.goods_card_image.setImageResource(R.mipmap.goods_image01);
        }
        else if (cur_position == 1) {
            viewHolder.goods_card_image.setImageResource(R.mipmap.goods_image02);
        }
        else if (cur_position == 2) {
            viewHolder.goods_card_image.setImageResource(R.mipmap.goods_image03);
        }
        else if (cur_position == 3) {
            viewHolder.goods_card_image.setImageResource(R.mipmap.goods_image04);
        }
        else if (cur_position == 4) {
            viewHolder.goods_card_image.setImageResource(R.mipmap.goods_image05);
        }
        else if (cur_position == 5) {
            viewHolder.goods_card_image.setImageResource(R.mipmap.goods_image06);
        }
        else if (cur_position == 6) {
            viewHolder.goods_card_image.setImageResource(R.mipmap.goods_image07);
        }
        else if (cur_position == 7) {
            viewHolder.goods_card_image.setImageResource(R.mipmap.goods_image08);
        }
        else if (cur_position == 8) {
            viewHolder.goods_card_image.setImageResource(R.mipmap.goods_image09);
        }
        else {
            viewHolder.goods_card_image.setImageResource(R.mipmap.goods_image10);
        }

        viewHolder.reduce_goods.setOnClickListener(this);
        viewHolder.reduce_goods.setTag(R.id.reduce_goods, position);
        viewHolder.add_goods.setOnClickListener(this);
        viewHolder.add_goods.setTag(R.id.add_goods, position);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        v.getContext();
        int my_position;
        int cur_num;
        totle_price = Double.valueOf(public_price_tv.getText().toString().split("元")[0]);
        switch (v.getId()) {
            case R.id.reduce_goods:
                my_position = (int) v.getTag(R.id.reduce_goods);
                cur_num = mData.get(my_position).getNum();
//                Toast.makeText(mContext,"我是按钮 " + my_position + cur_num,Toast.LENGTH_SHORT).show();
                if (cur_num != 0) {
                    cur_num--;
                    mData.get(my_position).setNum(cur_num);

//                    viewHolder.goods_card_number.setText(String.valueOf(cur_num));
                    single_price = Double.valueOf(viewHolder.goods_card_price.getText().toString().split("￥")[1]);
                    totle_price -= single_price;
                }
                public_listview.setAdapter(public_adpter);
                public_price_tv.setText("".format("%.2f", totle_price) + "元");
                break;
            case R.id.add_goods:
                my_position = (int) v.getTag(R.id.add_goods);
                cur_num = mData.get(my_position).getNum();
//                Toast.makeText(mContext,"我是按钮 " + my_position + cur_num,Toast.LENGTH_SHORT).show();
                cur_num++;
                mData.get(my_position).setNum(cur_num);
                single_price = Double.valueOf(viewHolder.goods_card_price.getText().toString().split("￥")[1]);
                totle_price += single_price;
//                viewHolder.goods_card_number.setText(String.valueOf(cur_num));
                public_listview.setAdapter(public_adpter);
                public_price_tv.setText("".format("%.2f", totle_price) + "元");
                break;
        }
    }


    private class ViewHolder{
        ImageView goods_card_image;
        TextView goods_card_name;
        TextView goods_card_price;
        TextView goods_card_number;
        ImageButton reduce_goods;
        ImageButton add_goods;
    }
}
