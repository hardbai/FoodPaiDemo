package app.bai.com.foodpai.MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.bai.com.foodpai.R;
import app.bai.com.foodpai.bean.Collect;

/**
 * Created by Administrator on 16-7-11.
 */
public class MyPaperAdapter extends BaseAdapter {

    private List<Collect> datas;
    private Context context;

    public MyPaperAdapter(List<Collect> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.collect_paper, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.paper.setText(datas.get(i).getTitle());
        return view;
    }

    public class ViewHolder {

        private TextView paper;

        public ViewHolder(View view) {
            paper = (TextView) view.findViewById(R.id.tv_collect_paper_id);
        }
    }
}
