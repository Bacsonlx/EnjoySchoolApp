package hznu.linxin.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private List<News> mData;
    private Context mContext;

    public NewsAdapter(List<News> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.news_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.news_title = (TextView) convertView.findViewById(R.id.news_title);
            viewHolder.new_content = (TextView) convertView.findViewById(R.id.news_content);
            viewHolder.news_image = (ImageView) convertView.findViewById(R.id.news_image);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.news_title.setText(mData.get(position).getNewsTitle());
        viewHolder.new_content.setText(mData.get(position).getNewsContent());
        viewHolder.news_image.setImageResource(mData.get(position).getImageId());
        return convertView;
    }

    private class ViewHolder{
        ImageView news_image;
        TextView news_title;
        TextView new_content;
    }
}
