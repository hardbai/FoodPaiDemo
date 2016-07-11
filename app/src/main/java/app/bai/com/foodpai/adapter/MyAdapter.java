package app.bai.com.foodpai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/15.
 */
public abstract class MyAdapter<T> extends BaseAdapter {
    private List<T> data;
    //布局导入器
    private LayoutInflater inflater;
    //
    private int layoutRes;
    public int getCount() {
        return data!=null?data.size():0;
    }
    public MyAdapter(List<T> data, Context context, int layoutRes) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutRes = layoutRes;
        if (data == null) {
           this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }
    /*
    *
    *
    *
    * */
    public  void updateRes(List<T> data){
        if(data!=null&&data.size()>0){
            this.data.clear();
            this.data.addAll(data);
            this.notifyDataSetChanged();
        }
    }
    public  void addRes(List<T> data){
        if(data!=null&&data.size()>0){
            this.data.addAll(data);
            this.notifyDataSetChanged();
        }
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh=null;
        if(convertView==null){
            convertView=inflater.inflate(layoutRes,parent,false);
            vh=new ViewHolder(convertView);
            convertView.setTag(vh);

        }else{
            vh= (ViewHolder) convertView.getTag();
        }
        //数据加载
        bindData(vh,getItem(position));

        return convertView;
    }

    public abstract  void bindData(ViewHolder vh,T t);

    protected static class ViewHolder{
        //convertView的引用
        private  View layout;
        private Map<Integer,View> cacheView;
        //通过构造将convetView传递过来
        public ViewHolder(View convertView) {
            layout = convertView;
            cacheView=new HashMap<>();
        }
        //对外提供获取convertView中子View的方法
        public View getView(int resId){
            View view=null;
            //如果MAP中包含当前要获取的资源id

             if(cacheView.containsKey(resId)){
                 //直接从map中根据key，获取想要的View
                 view=cacheView.get(resId);
             }else{
                 //不包含的话，我们实例化id所对应的view，并添加到map缓存中
                 view=layout.findViewById(resId);
                 cacheView.put(resId,view);
             }
            return view;
        }
    }
    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }
}
