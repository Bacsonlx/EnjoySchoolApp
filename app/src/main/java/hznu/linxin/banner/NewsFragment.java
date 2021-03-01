package hznu.linxin.banner;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import banner.CustomBanner;


@SuppressLint("ValidFragment")
public class NewsFragment extends Fragment {
    private ArrayList<News> news;
    private ListView list_news;
    private CustomBanner<String> mBanner;

    public NewsFragment(ArrayList<News> news) {
        this.news = news;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fg_content,container,false);

        mBanner = (CustomBanner) view.findViewById(R.id.home_banner);

        ArrayList<String> images = new ArrayList<>();
        images.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3649602130,1883974703&fm=26&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3500124442,2409028548&fm=15&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1061739498,2935740354&fm=26&gp=0.jpg");
        images.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4160409991,1326324068&fm=26&gp=0.jpg");
        images.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3490015197,141915410&fm=26&gp=0.jpg");
        images.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2467068416,1168793873&fm=26&gp=0.jpg");
        images.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3500124442,2409028548&fm=15&gp=0.jpg");
        setBean(images);

        list_news = (ListView) view.findViewById(R.id.list_news);
        NewsAdapter newsAdapter = new NewsAdapter(news, getActivity());
        list_news.setAdapter(newsAdapter);
        list_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 换个颜色
                list_news.setBackgroundColor(list_news.getResources().getColor(R.color.cyan));
                // 获取链接
                News click_news = news.get(position);
                String click_newsTitle = click_news.getNewsTitle();
                String click_newsContent = click_news.getNewsContent();
                String click_newsLink = click_news.getnewsLink();
                Toast.makeText(getActivity(), click_newsTitle + " " + click_newsContent,
                        Toast.LENGTH_SHORT).show();
                // 跳转链接
                Intent intent = new Intent(getActivity(), WebpageActivity.class);
                intent.putExtra("link", click_newsLink);
                startActivity(intent);
            }
        });
        return view;
    }

    //设置普通指示器
    private void setBean(final ArrayList<String> beans) {
        mBanner.setPages(new CustomBanner.ViewCreator<String>() {
            @Override
            public View createView(Context context, int position) {
                ImageView imageView = new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }

            @Override
            public void updateUI(Context context, View view, int position, String entity) {
                Glide.with(context).load(entity).into((ImageView) view);
            }
        }, beans)
//                //设置指示器为普通指示器
                .setIndicatorStyle(CustomBanner.IndicatorStyle.ORDINARY)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setIndicatorRes(R.drawable.shape_point_select, R.drawable.shape_point_unselect)
                //设置指示器的方向
                .setIndicatorGravity(CustomBanner.IndicatorGravity.CENTER)
                //设置指示器的指示点间隔
                .setIndicatorInterval(20)
                //设置自动翻页
                .startTurning(5000)
                // 设置页面点击
                .setOnPageClickListener(new CustomBanner.OnPageClickListener() {
                    @Override
                    public void onPageClick(int position, Object o) {
                        Intent intent = new Intent(getActivity(), WebpageActivity.class);
                        intent.putExtra("link", "https://www.hznu.edu.cn/");
                        startActivity(intent);
                    }
                });
    }


}
