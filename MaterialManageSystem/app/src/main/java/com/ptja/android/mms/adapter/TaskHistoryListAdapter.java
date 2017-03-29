/**
 *
 */
package com.ptja.android.mms.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.bean.TaskBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author shenlingnan
 */
public class TaskHistoryListAdapter extends BaseAdapter {

    List<TaskBean> data;
    Context mActivity;
    Handler mHandler;

    public TaskHistoryListAdapter(Context context, List<TaskBean> unfinishedTaskListItems, Handler mHanlder) {
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
                    R.layout.task_deal_list_item_finished, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final TaskBean bean = data.get(position);

        holder.taskType.setText(bean.getTask_type().getTask_type_name());
        holder.taskLocation.setText(bean.getDepot() != null ? bean.getDepot().getDepot_name() : "");
        holder.taskName.setText(bean.getTask_content());
        holder.taskTime.setText("从" + bean.getStart_time() + "到" + bean.getEnd_time());
        holder.deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = 100;
                msg.obj = bean;
                mHandler.sendMessage(msg);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.task_type)
        TextView taskType;
        @Bind(R.id.task_name_icon)
        ImageView taskNameIcon;
        @Bind(R.id.task_name)
        TextView taskName;
        @Bind(R.id.task_location_icon)
        ImageView taskLocationIcon;
        @Bind(R.id.task_location)
        TextView taskLocation;
        @Bind(R.id.task_time_icon)
        ImageView taskTimeIcon;
        @Bind(R.id.task_time)
        TextView taskTime;
        @Bind(R.id.deal)
        Button deal;
        @Bind(R.id.location_list_view_layout)
        LinearLayout locationListViewLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
