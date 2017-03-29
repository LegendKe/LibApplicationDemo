/**
 *
 */
package com.ptja.android.mms.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.bean.EquipApplyBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author shenlingnan
 */
public class EquipApplyListAdapter extends BaseAdapter {

    List<EquipApplyBean> data;
    Context mActivity;
    Handler mHandler;

    public EquipApplyListAdapter(Context context, List<EquipApplyBean> unfinishedTaskListItems, Handler mHanlder) {
        this.data = unfinishedTaskListItems;
        this.mActivity = context;
        this.mHandler = mHanlder;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(
                    R.layout.equip_apply_list_item_ing, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final EquipApplyBean bean = data.get(position);

        holder.title.setText(bean.getTitle());
        holder.time.setText(bean.getDate());
        holder.equips.setText("领用装备:"+bean.getEquipName());
        holder.numbers.setText("领用数量:"+bean.getNumber());
        holder.deal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Message msg = new Message();
//                msg.what = 100;
//                msg.obj = bean;
//                mHandler.sendMessage(msg);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.task_name_icon)
        ImageView taskNameIcon;
        @Bind(R.id.equips)
        TextView equips;
        @Bind(R.id.task_location_icon)
        ImageView taskLocationIcon;
        @Bind(R.id.numbers)
        TextView numbers;
        @Bind(R.id.deal)
        Button deal;
        @Bind(R.id.location_list_view_layout)
        LinearLayout locationListViewLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
