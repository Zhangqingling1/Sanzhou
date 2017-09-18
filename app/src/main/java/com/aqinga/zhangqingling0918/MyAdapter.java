package com.aqinga.zhangqingling0918;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/9/189:00
 */

public class MyAdapter extends XRecyclerView.Adapter<MyAdapter.ViewHolder> {

    //获得bean中的数据，并给图片文字赋值
    List<Bean.StoriesBean> list;
    Context context;

    public MyAdapter(List<Bean.StoriesBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    //获得他的布局d
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(inflate);
    }

    //赋值过程
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        holder.image.setImageResource(list.get(position).getImages());
        holder.text.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImages().get(0)).into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MyActivity.class);
                intent.putExtra("image",list.get(position).getImages().get(0));
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //长按点击事件发生异常 异常为空指针
                Dialog dialog = null;
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView text;
        private final View itemView;

        //获得控件ID
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            image = (ImageView) itemView.findViewById(R.id.image_view);
            text = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
