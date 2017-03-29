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
import android.widget.TextView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.bean.TaskBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author shenlingnan
 */
public class TaskDealListAdapter extends BaseAdapter {

    TaskBean data;
    Context mActivity;
    Handler mHandler;

    public TaskDealListAdapter(Context context,TaskBean taskBean, Handler mHanlder) {
        this.data = taskBean;
        this.mActivity = context;
        this.mHandler = mHanlder;
    }

    @Override
    public int getCount() {
        return data.getTask_record().size();
    }

    @Override
    public Object getItem(int position) {
        return data.getTask_record().get(position);
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
                    R.layout.task_deal_list_item_undeal, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final TaskBean.TaskRecordBean bean = data.getTask_record().get(position);

        holder.equipName.setText(bean.getEquipment().getEquipment_name());
        holder.equipLocation.setText(data.getDepot().getDepot_name());
        holder.equipType.setText(data.getTask_type().getTask_type_name());
        holder.code.setText(bean.getEquipment().getCode());
        if (bean.getResult().equals("0")){
            holder.deal.setVisibility(View.VISIBLE);
            holder.finished.setVisibility(View.GONE);
            holder.deal.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.what = 100;
                    msg.obj = bean;
                    mHandler.sendMessage(msg);
                }
            });
        }else{
            holder.deal.setVisibility(View.GONE);
            holder.finished.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.equip_name)
        TextView equipName;
        @Bind(R.id.code)
        TextView code;
        @Bind(R.id.equip_type)
        TextView equipType;
        @Bind(R.id.equip_location)
        TextView equipLocation;
        @Bind(R.id.deal)
        Button deal;
        @Bind(R.id.finished)
                TextView finished;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
