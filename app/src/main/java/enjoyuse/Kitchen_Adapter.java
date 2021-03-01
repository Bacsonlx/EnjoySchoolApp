package enjoyuse;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hznu.linxin.banner.R;

import static hznu.linxin.banner.Public_Function.db;

public class Kitchen_Adapter extends RecyclerView.Adapter<Kitchen_Adapter.ViewHolder>{
    private List<Kitchen> mKitchenList;
    private Kitchen_Adapter.OnItemClickListener mOnItemClickListener;

    // 自定义onItemClickListener监听器,为adapter的内部接口
    public interface OnItemClickListener{
        void onClick(int position);
    }
    //为adapter设置监听器
    public void setOnItemClickListener(Kitchen_Adapter.OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener=onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View kitchenView;
        ImageView deleteImage;
        ImageView kitchenImage;
        TextView kitchenId;
        TextView kitchenAddress;
        TextView kitchenWait;
        TextView kitchenTime;
        ImageView modifyImage;
        public ViewHolder(View view) {
            super(view);
            kitchenView = view;
            deleteImage = (ImageView) view.findViewById(R.id.delete);
            kitchenImage = (ImageView) view.findViewById(R.id.kitchen_image);
            kitchenId = (TextView) view.findViewById(R.id.kitchen_id);
            kitchenAddress = (TextView) view.findViewById(R.id.kitchen_address);
            kitchenWait = (TextView) view.findViewById(R.id.kitchen_wait);
            kitchenTime = (TextView) view.findViewById(R.id.kitchen_time);
            modifyImage = (ImageView) view.findViewById(R.id.modify);
        }
    }

    public Kitchen_Adapter(List<Kitchen> kitchenList) {
        mKitchenList = kitchenList;
    }

    @Override
    public Kitchen_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kitchen_item, parent, false);
        final Kitchen_Adapter.ViewHolder holder = new Kitchen_Adapter.ViewHolder(view);
        holder.kitchenView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Kitchen kitchen = mKitchenList.get(position);
                Toast.makeText(v.getContext(), "" + kitchen.getKitchen_id() + kitchen.getKitchen_time(), Toast.LENGTH_SHORT).show();
            }
        });
        // 点击删除事件
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "删除", Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("删除");
                builder.setMessage("确定删除该厨房吗?");
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
                                int position = holder.getAdapterPosition();
                                Kitchen kitchen = mKitchenList.get(position);
                                db.delete("Kitchen", "kitchen_id=?", new String[] { kitchen.getKitchen_id() });
                            }
                        });
                builder.show();

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Kitchen_Adapter.ViewHolder holder, final int position) {
        Kitchen kitchen = mKitchenList.get(position);
        holder.kitchenImage.setImageResource(kitchen.getKitchen_imageId());
        holder.kitchenId.setText("厨房编号：" + kitchen.getKitchen_id());
        holder.kitchenAddress.setText("地址: " + kitchen.getKitchen_address());
        holder.kitchenWait.setText("等待人数： " + kitchen.getKitchen_wait());
        holder.kitchenTime.setText("等待时间: " + kitchen.getKitchen_time());

        //监听holder.itemView的点击事件
        if(mOnItemClickListener!=null){
            holder.modifyImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //回调到我们设置的监听器的onClick方法
                    mOnItemClickListener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mKitchenList.size();
    }
}
