package com.saltwater2233.systemview;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.saltwater2233.systemview.widget.EditTextActivity;
import com.saltwater2233.systemview.widget.TextViewActivity;

import java.util.List;

/**
 * <pre>
 *     author : wenxin
 *     e-mail : wenxin2233@outlook.com
 *     time   : 2018/07/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder> {
    private List<String> mNameList;


    public NameAdapter(List<String> nameList){
        this.mNameList = nameList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //第三个参数attachToRoot为true的情况下，这个布局会被解析并加载在root下面，
        // 如果为false，则会依照root去解析该xml并返回view，但是这个view不会被加载到root里。
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view,viewGroup,false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                switch (mNameList.get(position)){
                    case "TextView":
                        view.getContext().startActivity(new Intent(view.getContext(), TextViewActivity.class));
                        break;
                    case "EditText":
                        view.getContext().startActivity(new Intent(view.getContext(), EditTextActivity.class));
                        break;
                }
            }
        });
        return viewHolder;
    }

    /**这个方法设置控件参数
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(mNameList.get(i));
    }

    @Override
    public int getItemCount() {
        return mNameList.size();
    }

    /**
     * 这里绑定控件，新建一个类避免重复绑定
     */
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);

        }
    }
}
