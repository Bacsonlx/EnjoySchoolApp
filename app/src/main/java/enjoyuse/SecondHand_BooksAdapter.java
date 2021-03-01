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

public class SecondHand_BooksAdapter extends RecyclerView.Adapter<SecondHand_BooksAdapter.ViewHolder> {
    private List<SecondHand_Books> mSecondHand_BoosList;
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
        View secondHandView;
        ImageView SecondHand_BookImage;
        TextView SecondHand_BookId;
        TextView SecondHand_BookName;
        TextView SecondHand_BookPrice;
        ImageView deleteImage;
        ImageView modifyImage;
        public ViewHolder(View view) {
            super(view);
            secondHandView = view;
            deleteImage = (ImageView) view.findViewById(R.id.delete);
            SecondHand_BookImage = (ImageView) view.findViewById(R.id.secondHandBookImage);
            SecondHand_BookId = (TextView) view.findViewById(R.id.secondHandBookId);
            SecondHand_BookName = (TextView) view.findViewById(R.id.secondHandBookName);
            SecondHand_BookPrice = (TextView) view.findViewById(R.id.secondHandBookPrice);
            modifyImage = (ImageView) view.findViewById(R.id.modify);
        }
    }

    public SecondHand_BooksAdapter(List<SecondHand_Books> mSecondHand_BoosList) {
        this.mSecondHand_BoosList = mSecondHand_BoosList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.secondhandbook_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.secondHandView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SecondHand_Books secondHand_books = mSecondHand_BoosList.get(position);
                Toast.makeText(v.getContext(), "" + secondHand_books.getBook_id() + secondHand_books.getBook_name(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.SecondHand_BookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SecondHand_Books secondHand_books = mSecondHand_BoosList.get(position);
                Toast.makeText(v.getContext(), "" + secondHand_books.getBook_id() + secondHand_books.getBook_name(), Toast.LENGTH_SHORT).show();
            }
        });
        // 点击删除事件
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                SecondHand_Books secondHand_books = mSecondHand_BoosList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("删除");
                builder.setMessage("确定删除这本" + secondHand_books.getBook_name() +"吗?");
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
                                SecondHand_Books secondHand_books = mSecondHand_BoosList.get(position);
                                db.delete("SecondHandBooks", "book_id=?", new String[] { secondHand_books.getBook_id() });
                            }
                        });
                builder.show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        SecondHand_Books secondHand_books = mSecondHand_BoosList.get(position);
        holder.SecondHand_BookImage.setImageResource(secondHand_books.getBook_imageId());
        holder.SecondHand_BookId.setText("图书编号：" + secondHand_books.getBook_id());
        holder.SecondHand_BookName.setText("书名：" + secondHand_books.getBook_name());
        holder.SecondHand_BookPrice.setText("价格： ￥" + secondHand_books.getBook_price());

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
        return mSecondHand_BoosList.size();
    }
}
