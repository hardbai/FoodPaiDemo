package app.bai.com.foodpai.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import app.bai.com.foodpai.R;

/**
 * Created by Administrator on 16-7-6.
 */
public class MyAdapter extends BaseAdapter{
    private Context context;
    private String[] data;
    public MyAdapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.length;
    }
    @Override
    public Object getItem(int i) {
        return data[i];
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //1.得到布局容器加载器
        LayoutInflater inflater = LayoutInflater.from(context);
        //2.加载布局
        View view1 = inflater.inflate(R.layout.item,null);
        //3.得到布局中的控件
        TextView tv = (TextView) view1.findViewById(R.id.tv_content_id);
        //4.为控件设置内容
        tv.setText(data[i]);
        return view1;
    }
}
