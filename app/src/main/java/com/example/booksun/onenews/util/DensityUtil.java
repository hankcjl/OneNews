package com.example.booksun.onenews.util;
 
import android.content.Context;


/**
 *@author booksun
 *@date 2018-5-30
 * 工具类 转换单位dp和px互相转换
 */

public class DensityUtil
{
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 * Context 提供安卓上下文环境
	 */
	public static int px2dip(Context context, float pxValue)
	{
		final float scale = context.getResources().getDisplayMetrics().density;//这个得到的不应该叫做密度，应该是密度的一个比例。
		// 不是真实的屏幕密度，而是相对于某个值的屏幕密度。
		//也可以说是相对密度
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 显示器的逻辑密度，这是【独立的像素密度单位（首先明白dip是个单位）】的一个缩放因子，
	 * 在屏幕密度大约为160dpi的屏幕上，一个dip等于一个px,这个提供了系统显示器的一个基线（这句我实在翻译不了）。
	 * 例如：屏幕为240*320的手机屏幕，其尺寸为 1.5"*2"  也就是1.5英寸乘2英寸的屏幕
	 * 它的dpi（屏幕像素密度，也就是每英寸的像素数，dpi是dot per inch的缩写）大约就为160dpi，
	 * 所以在这个手机上dp和px的长度（可以说是长度，最起码从你的视觉感官上来说是这样的）是相等的。
	 * 因此在一个屏幕密度为160dpi的手机屏幕上density的值为1，而在120dpi的手机上为0.75等等
	 * （这里有一句话没翻译，实在读不通顺，不过通过下面的举例应该能看懂）
	 * 例如：一个240*320的屏幕尽管他的屏幕尺寸为1.8"*1.3",（我算了下这个的dpi大约为180dpi多点）
	 * 但是它的density还是1(也就是说取了近似值)
	 * 然而，如果屏幕分辨率增加到320*480 但是屏幕尺寸仍然保持1.5"*2" 的时候（和最开始的例子比较）
	 * 这个手机的density将会增加（可能会增加到1.5）
	 */

}
