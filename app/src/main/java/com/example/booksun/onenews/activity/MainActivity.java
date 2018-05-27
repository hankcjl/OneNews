package com.example.booksun.onenews.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.booksun.onenews.custom.CustomSimpleAdapter;
import com.example.booksun.onenews.util.DensityUtil;
import com.example.booksun.onenews.R;

public class MainActivity extends Activity
{
    private final int COLUMNWIDTHPX = 500;
    private final int FLINGVELOCITYPX =800; //滚动距离

    private int mColumnWidthDip;
    private int mFlingVelocityDip;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //把px转换成dip
        mColumnWidthDip = DensityUtil.px2dip(this, COLUMNWIDTHPX);
        mFlingVelocityDip = DensityUtil.px2dip(this, FLINGVELOCITYPX);

        //获取新闻分类
        String[] categoryArray = getResources().getStringArray(R.array.categories);
        //把新闻分类保存到List中
        List<HashMap<String, String>> categories = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<categoryArray.length;i++)
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("category_title", categoryArray[i]);
            categories.add(hashMap);
        }
        //创建Adapter，指明映射字段
        CustomSimpleAdapter categoryAdapter = new CustomSimpleAdapter(this, categories, R.layout.category_title, new String[]{"category_title"}, new int[]{R.id.category_title});

        GridView category = new GridView(this);
        category.setColumnWidth(mColumnWidthDip);//每个单元格宽度
        category.setNumColumns(GridView.AUTO_FIT);//单元格数目
        category.setGravity(Gravity.CENTER);//设置对其方式
        //设置单元格选择是背景色为透明，这样选择时就不现实黄色北京
        category.setSelector(new ColorDrawable(Color.TRANSPARENT));
        //根据单元格宽度和数目计算总宽度
        int width = mColumnWidthDip * categories.size();
        LayoutParams params = new LayoutParams(width, LayoutParams.FILL_PARENT);
        //更新category宽度和高度，这样category在一行显示
        category.setLayoutParams(params);
        //设置适配器
        category.setAdapter(categoryAdapter);
        //把category加入到容器中
        LinearLayout categoryList = (LinearLayout) findViewById(R.id.category_layout);
        categoryList.addView(category);
        //添加单元格点击事件
        category.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TextView categoryTitle;
                //恢复每个单元格背景色
                for (int i = 0; i < parent.getChildCount(); i++)
                {
                    categoryTitle = (TextView) (parent.getChildAt(i));
                    categoryTitle.setBackgroundDrawable(null);
                    //categoryTitle.setTextColor(R.color.category_title_normal_background);
                    categoryTitle.setTextColor(0XFFADB2AD);
                }
                //设置选择单元格的背景色
                categoryTitle = (TextView) (parent.getChildAt(position));
                categoryTitle.setBackgroundResource(R.drawable.categorybar_item_background);
                //categoryTitle.setTextColor(R.color.white);
                categoryTitle.setTextColor(0XFFFFFFFF);

                Toast.makeText(MainActivity.this, categoryTitle.getText(), Toast.LENGTH_LONG).show();
            }
        });

        // 箭头
        final HorizontalScrollView categoryScrollview = (HorizontalScrollView) findViewById(R.id.category_scrollview);
        Button categoryArrowRight = (Button) findViewById(R.id.category_arrow_right);
        categoryArrowRight.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                categoryScrollview.fling(DensityUtil.px2dip(MainActivity.this, mFlingVelocityDip));
            }
        });
        //显示新闻列表
        List<HashMap<String, String>> newsData = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<10;i++)
        {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("newslist_item_title","若水新闻客户端教程发布啦" );
            hashMap.put("newslist_item_digest","若水新闻客户端教程发布啦" );
            hashMap.put("newslist_item_source", "来源：若水工作室");
            hashMap.put("newslist_item_ptime", "2012-03-12 10:21:22");
            newsData.add(hashMap);
        }
        SimpleAdapter newsListAdapter = new SimpleAdapter(this, newsData, R.layout.newslist_item,
                new String[]{"newslist_item_title","newslist_item_digest","newslist_item_source","newslist_item_ptime"},
                new int[]{R.id.newslist_item_title,R.id.newslist_item_digest,R.id.newslist_item_source,R.id.newslist_item_ptime});
        ListView newslist = (ListView)findViewById(R.id.news_list);
        newslist.setAdapter(newsListAdapter);

        newslist.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(MainActivity.this, NewsDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}