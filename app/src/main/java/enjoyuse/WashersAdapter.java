package enjoyuse;

import android.app.Activity;
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

public class WashersAdapter extends RecyclerView.Adapter<WashersAdapter.ViewHolder> {
    private List<Washers> mWasherList;
    private PrintersAdapter.OnItemClickListener mOnItemClickListener;

    // 自定义onItemClickListener监听器,为adapter的内部接口
    public interface OnItemClickListener{
        void onClick(int position);
    }
    //为adapter设置监听器
    public void setOnItemClickListener(PrintersAdapter.OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener=onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View washerView;
        ImageView deleteImage;
        ImageView washerImage;
        TextView washerName;
        TextView washerAddress;
        TextView washerUsage;
        TextView washerTime;
        ImageView modifyImage;
        public ViewHolder(View view) {
            super(view);
            washerView = view;
            deleteImage = (ImageView) view.findViewById(R.id.delete);
            washerImage = (ImageView) view.findViewById(R.id.washer_image);
            washerName = (TextView) view.findViewById(R.id.washer_name);
            washerAddress = (TextView) view.findViewById(R.id.washer_address);
            washerUsage = (TextView) view.findViewById(R.id.washer_usage);
            washerTime = (TextView) view.findViewById(R.id.washer_time);
            modifyImage = (ImageView) view.findViewById(R.id.modify);
        }
    }

    public WashersAdapter(List<Washers> washersList) {
        mWasherList = washersList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.washers_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.washerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Washers washers = mWasherList.get(position);
                Toast.makeText(v.getContext(), "" + washers.getWasher_id() + washers.getWasher_address(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.washerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Washers washers = mWasherList.get(position);
                Toast.makeText(v.getContext(), "" + washers.getWasher_id() + washers.getWasher_address(), Toast.LENGTH_SHORT).show();
            }
        });
        // 点击删除事件
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "删除", Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("删除");
                builder.setMessage("确定删除这台机器吗?");
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
                                Washers washers = mWasherList.get(position);
                                db.delete("Washer", "wash_id=?", new String[] { washers.getWasher_id() });
                            }
                        });
                builder.show();

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Washers washers = mWasherList.get(position);
        holder.washerImage.setImageResource(washers.getWasher_imageId());
        holder.washerName.setText("机器号：" + washers.getWasher_id());
        holder.washerAddress.setText("地址: " + washers.getWasher_address());
        if (washers.isWasher_usage()) {
            holder.washerUsage.setText("状态: 使用中");
        } else {
            holder.washerUsage.setText("状态: 空闲中");
        }
        holder.washerTime.setText("等待时间: " + washers.getWasher_time());

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
        return mWasherList.size();
    }
}
