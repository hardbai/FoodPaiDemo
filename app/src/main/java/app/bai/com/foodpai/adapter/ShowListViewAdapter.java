package app.bai.com.foodpai.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.bai.com.foodpai.Model.Food;
import app.bai.com.foodpai.R;


/**
 * Created by Administrator on 2016/7/6.
 */
public class ShowListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private  List<Food.FeedsBean> list;
    private OnItemClickListener onItemClickListener;

    public ShowListViewAdapter(List<Food.FeedsBean> list , Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            return new ItemViewHolder(inflater.inflate(R.layout.show_listview_item, parent, false));
        } else {
            return new Item2ViewHolder(inflater.inflate(R.layout.show_listview_item2, parent, false));
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      if(holder instanceof  ItemViewHolder){
          ((ItemViewHolder) holder).tv_source.setText(list.get(position).getSource());
          ((ItemViewHolder) holder).tv_title.setText(list.get(position).getTitle());
          ((ItemViewHolder) holder).tv_tail.setText(list.get(position).getTail());
      } else if(holder instanceof Item2ViewHolder){
          Glide.with(context).load(list.get(position).getBackground()).into(((Item2ViewHolder) holder).im_backGroud);
          ((Item2ViewHolder) holder).tv_source.setText(list.get(position).getSource());
          ((Item2ViewHolder) holder).tv_title.setText(list.get(position).getTitle());
          ((Item2ViewHolder) holder).tv_tail.setText(list.get(position).getTail());
      }
    }

    @Override
    public int getItemViewType(int position) {

        return  list.get(position).getBackground() == "" ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tv_source;
        private final TextView tv_title;
        private final TextView tv_tail;


        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_source = ((TextView) itemView.findViewById(R.id.source));
            tv_title = ((TextView) itemView.findViewById(R.id.title));
            tv_tail = ((TextView) itemView.findViewById(R.id.tail));

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getLayoutPosition(),view);
        }
    }


    public class Item2ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private final TextView tv_title;
        private final TextView tv_tail;
        private final ImageView im_backGroud;
        private final TextView tv_source;


        public Item2ViewHolder(View itemView) {
            super(itemView);
            im_backGroud = ((ImageView) itemView.findViewById(R.id.backGround));
            tv_source = ((TextView) itemView.findViewById(R.id.source));
            tv_title = ((TextView) itemView.findViewById(R.id.title));
            tv_tail = ((TextView) itemView.findViewById(R.id.tail));

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getLayoutPosition(),view);
        }
    }

    public void addAll(List<Food.FeedsBean> dd) {
        list.addAll(dd);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        public void onItemClick(int position, View itemView);
    }
}
