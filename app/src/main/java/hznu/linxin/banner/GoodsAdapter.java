package hznu.linxin.banner;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LongDef;

import java.util.List;

import enjoyuse.PrintersAdapter;

public class GoodsAdapter extends BaseAdapter {
    private List<Goods> mData;
    private Context mContext;
    private GoodsAdapter.OnItemClickListener mOnItemClickListener;

    public GoodsAdapter(List<Goods> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    // 自定义onItemClickListener监听器,为adapter的内部接口
    public interface OnItemClickListener{
        void onClick(int position);
    }
    //为adapter设置监听器
    public void setOnItemClickListener(GoodsAdapter.OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener=onItemClickListener;
    }

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
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.goods_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.goods_name = (TextView) convertView.findViewById(R.id.goods_name);
            viewHolder.goods_price = (TextView) convertView.findViewById(R.id.goods_price);
            viewHolder.goods_image = (ImageView) convertView.findViewById(R.id.goods_image);
            viewHolder.goods_btn = (Button) convertView.findViewById(R.id.goods_btn);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.goods_name.setText(mData.get(position).getGoodsName());
        viewHolder.goods_price.setText(mData.get(position).getGoodsPrice());
        viewHolder.goods_image.setImageResource(mData.get(position).getImageId());

        //监听holder.itemView的点击事件
        if(mOnItemClickListener!=null){
            viewHolder.goods_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //回调到我们设置的监听器的onClick方法
                    mOnItemClickListener.onClick(position);
                }
            });
            // 如果还有其他点击事件 可以继续添加
        }

        return convertView;
    }

    private class ViewHolder{
        ImageView goods_image;
        TextView goods_name;
        TextView goods_price;
        Button goods_btn;
    }
}
