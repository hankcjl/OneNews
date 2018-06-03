package com.example.booksun.onenews.custom;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.booksun.onenews.R;


/**
 *@author booksun
 *@date 2018-5-25
 * 在安卓中，顾名思义就是把数据变成符合界面风格的形式，并且通过ListView显示出来。也就是说适配器是数据和界面之间的桥梁。
 * 适配器在数据库中的数据（后台）和显示页面（前端）中充当一个转换器的角色，
 * 数据库中的数据（如数组，链表，数据库，集合等）通过适配器变成类手机页面能够正常显示的数据。
 * 可以看作是界面数据绑定的一种理解。假设把数据、适配器和ListView（页面）比喻成一个MVC模式的话，
 * 那么适配器（Adapter）在这中间就充当了Controller的角色。
 *

 */

public class CustomSimpleAdapter extends SimpleAdapter
{

	/**
	 * Context context:上下文(当前的Activity)
	 * List<? extends Map<String, ?>> data:一个嵌套Map集合类型的list集合数据源
	 * int resource:这个是你设定用于接收Map-Object类型的Activity,这个布局中必须包括了to中定义的控件id
	 * String[] from:第四个参数通过源码可以看出需要的是一个K值的字符串数组
	 * int[] to:第五个参数通过源码看出是一个与K值匹配的的控件对象
	 * */

	public CustomSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
	{
		super(context, data, resource, from, to);
	}

	//加载新闻类型列表
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = super.getView(position, convertView, parent);
		//更新第一个TextView的背景
		if (position==0)
		{
			TextView categoryTitle = (TextView)v;
			categoryTitle.setBackgroundResource(R.drawable.categorybar_item_background);
			categoryTitle.setTextColor(0XFFFFFFFF);//白色
		}
		return v;
	}
}
