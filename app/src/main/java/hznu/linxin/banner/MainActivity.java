package hznu.linxin.banner;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private long exit_time;
    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;
    // 商品列表
    private ArrayList<Goods> goodsList = null;
    // 新闻列表
    private ArrayList<News> newsList = null;
    private FragmentManager fManager = null;
    //Fragment Object
    private PlayFragment fg1;
    private NewsFragment fg2;
    private BuyFragment fg3;
    private MyFragment fg4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 隐藏MainActivity自带的标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.hide();
        }
        fManager = getFragmentManager();
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        rb_channel = (RadioButton) findViewById(R.id.rb_use);
        rb_channel.setChecked(true);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (checkedId){
            case R.id.rb_use:
                findViewById(R.id.ly_top_bar).setVisibility(View.VISIBLE);
                if(fg1 == null){
                    fg1 = new PlayFragment();
                    fTransaction.add(R.id.ly_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.rb_fun:
                findViewById(R.id.ly_top_bar).setVisibility(View.VISIBLE);
                if(fg2 == null){
                    newsList = new ArrayList<News>();
                    initNews();
                    fg2 = new NewsFragment(newsList);
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.rb_shop:
                findViewById(R.id.ly_top_bar).setVisibility(View.VISIBLE);
                if(fg3 == null){
                    goodsList = new ArrayList<Goods>();
                    initGoods();
                    fg3 = new BuyFragment(goodsList);
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
            case R.id.rb_setting:
                findViewById(R.id.ly_top_bar).setVisibility(View.VISIBLE);
                if(fg4 == null){
                    fg4 = new MyFragment();
                    fTransaction.add(R.id.ly_content,fg4);
                }else{
                    fTransaction.show(fg4);
                }
                break;
        }
        fTransaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }

    // 加载商品信息
    public void initGoods() {
        goodsList.clear();
        Goods goods1 = new Goods("旺仔牛奶", "￥8.5", R.mipmap.goods_image01);
        goodsList.add(goods1);
        Goods goods2 = new Goods("佳得乐", "￥4.00", R.mipmap.goods_image02);
        goodsList.add(goods2);
        Goods goods3 = new Goods("乐事薯片", "￥6.5", R.mipmap.goods_image03);
        goodsList.add(goods3);
        Goods goods4 = new Goods("奥利奥", "￥6.00", R.mipmap.goods_image04);
        goodsList.add(goods4);
        Goods goods5 = new Goods("巧克力", "￥23.5", R.mipmap.goods_image05);
        goodsList.add(goods5);
        Goods goods6 = new Goods("牛肉干", "￥16.5", R.mipmap.goods_image06);
        goodsList.add(goods6);
        Goods goods7 = new Goods("面包", "￥8.5", R.mipmap.goods_image07);
        goodsList.add(goods7);
        Goods goods8 = new Goods("红烧牛肉面", "￥4.5", R.mipmap.goods_image08);
        goodsList.add(goods8);
        Goods goods9 = new Goods("饼干", "￥4.5", R.mipmap.goods_image09);
        goodsList.add(goods9);
        Goods goods10 = new Goods("QQ糖", "￥3.5", R.mipmap.goods_image10);
        goodsList.add(goods10);
    }

    // 加载新闻信息
    public void initNews() {
        newsList.clear();
        for (int i = 0; i <= 5; i++) {
            News news1 = new News("杭州师范大学2021年接收推荐免试硕士研究生办法", "为深化研究生招生制度改革，加大拔尖创新人才选拔培养力度，我校根据教育部有关接收推荐免试攻读硕士研究生（以下简称推免生）工作管理办法，结合我校实际情况，特制定本办法。\n" +
                    "合成绩均名列本科所学专业前茅，取得所在学校推荐免试生资格。", R.mipmap.news_image, "https://www.hznu.edu.cn/c/2020-08-24/2442764.shtml");
            newsList.add(news1);
            News news2 = new News("【科研速递】材化学院/有机硅实验室叶飞博士等研究成果在Nature", "近日，杭师大材料与化学化工学院/有机硅化学及材料技术教育部重点实验室叶飞博士与德国莱布尼茨催化研究所Matthias Beller教授、Helfried Neumann博士合作在快速构....\n",
                    R.mipmap.news_image, "https://www.hznu.edu.cn/c/2020-12-11/2488624.shtml");
            newsList.add(news2);
            News news3 = new News("关于开展“我为学校“十四五”规划献金点”建议征集活动的通知", "各学院、部门：\n" +
                    "\n" +
                    "“十四五”事业发展规划是学校未来五年加强内涵建设、实现科学发展的战略指南，是全校上下凝聚共识、戮力同心实现学校“建成实力强劲、特色鲜明、文化厚重的全国一流大学”共同奋斗目标的行动纲领。为科学编制“十四五”规划，进一步提高规划编制的师生参与度，集思广益、充分汲取凝聚民智民意，有....\n",
                    R.mipmap.news_image, "https://www.hznu.edu.cn/c/2020-09-11/2447344.shtml");
            newsList.add(news3);
            News news4 = new News("杭师大召开冬春季疫情防控和安全稳定专题会", "12月10日上午，杭师大召开冬春季疫情防控和安全稳定专题会，就疫情防控和安全稳定工作进行部署。校党委书记陈春雷出席会议并讲话，全体在校党政领导班子成员出席会议。....\n",
                    R.mipmap.news_image, "https://www.hznu.edu.cn/c/2020-12-10/2493581.shtml");
            newsList.add(news4);
            News news5 = new News("杭师大在省第九届大学生统计调查方案设计大赛中取得新突破", "12月4日至5日，浙江省第九届“民生民意杯”大学生统计调查方案设计大赛在杭师大举行。我校学生获一等奖5项、二等奖4项、三等奖2项，创历史最佳成绩。学校获优秀组织奖。\n" +
                    "\n" +
                    "本届比赛有来自全省74所高校（含独立学院）的566件作品报名参加，经过双向匿名评审，来自40所院校的132支队伍500多名师生进入决赛。经过激烈角逐，最终评选出一等奖48项、二等奖84项....\n",
                    R.mipmap.news_image, "https://www.hznu.edu.cn/c/2020-12-08/2491884.shtml");
            newsList.add(news5);
        }
    }

    @Override
    //按两次back键退出
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //获取按键并比较两次按back的时间大于2s不退出，否则退出
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exit_time > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                exit_time = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
