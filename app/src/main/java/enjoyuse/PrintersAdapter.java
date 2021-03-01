package enjoyuse;

import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import hznu.linxin.banner.R;
import static hznu.linxin.banner.Public_Function.db;
import static hznu.linxin.banner.Public_Function.public_printer_adpter;
import static hznu.linxin.banner.Public_Function.public_printersList;

public class PrintersAdapter extends RecyclerView.Adapter<PrintersAdapter.ViewHolder> {
    private List<Printers> mPrintersList;
    private OnItemClickListener mOnItemClickListener;

    // 自定义onItemClickListener监听器,为adapter的内部接口
    public interface OnItemClickListener{
        void onClick(int position);
    }
    //为adapter设置监听器
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener=onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View printerView;
        ImageView printerImage;
        TextView printerName;
        TextView printerAddress;
        TextView printerUsage;
        ImageView deleteImage;
        ImageView modifyImage;
        public ViewHolder(View view) {
            super(view);
            printerView = view;
            deleteImage = (ImageView) view.findViewById(R.id.delete);
            printerImage = (ImageView) view.findViewById(R.id.printer_image);
            printerName = (TextView) view.findViewById(R.id.printer_name);
            printerAddress = (TextView) view.findViewById(R.id.printer_address);
            printerUsage = (TextView) view.findViewById(R.id.printer_usage);
            modifyImage = (ImageView) view.findViewById(R.id.modify);
        }
    }

    public PrintersAdapter(List<Printers> printersList) {
        mPrintersList = printersList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.printers_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.printerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Printers printers = mPrintersList.get(position);
                Toast.makeText(v.getContext(), "" + printers.getPrinter_id() + printers.getPrinter_address(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.printerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Printers printers = mPrintersList.get(position);
                Toast.makeText(v.getContext(), "" + printers.getPrinter_id() + printers.getPrinter_address(), Toast.LENGTH_SHORT).show();
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
                                Printers printers = mPrintersList.get(position);
                                db.delete("Printer", "printer_id=?", new String[] { printers.getPrinter_id() });
                                initPrinters();
                            }
                        });
                builder.show();

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Printers printers = mPrintersList.get(position);
        holder.printerImage.setImageResource(printers.getPrinter_imageId());
        holder.printerName.setText("机器号：" + printers.getPrinter_id());
        holder.printerAddress.setText("地址： " + printers.getPrinter_address());
        if (printers.isPrinter_usage()) {
            holder.printerUsage.setText("状态: 使用中");
        } else {
            holder.printerUsage.setText("状态: 空闲中");
        }

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
        return mPrintersList.size();
    }

    public void initPrinters() {
        public_printersList.clear();
        Cursor cursor = db.query("Printer", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String printer_id = cursor.getString(cursor
                        .getColumnIndex("printer_id"));
                String printer_address = cursor.getString(cursor
                        .getColumnIndex("printer_address"));
                String printer_imageId = cursor.getString(cursor
                        .getColumnIndex("printer_imageId"));
                String printer_usage = cursor.getString(cursor
                        .getColumnIndex("printer_usage"));
                if(printer_usage.equals("use") || printer_usage.equals("使用中")){
                    Printers printers = new Printers(printer_id, printer_address, R.mipmap.print_machine_img, true);
                    public_printersList.add(printers);
                }
                else {
                    Printers printers = new Printers(printer_id, printer_address, R.mipmap.print_machine_img, false);
                    public_printersList.add(printers);
                }
            }
            cursor.close();
        }
        public_printer_adpter.notifyDataSetChanged();
    }
}
