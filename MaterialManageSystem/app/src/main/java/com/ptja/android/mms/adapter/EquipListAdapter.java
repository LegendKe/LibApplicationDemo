/**
 *
 */
package com.ptja.android.mms.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.bean.EquipmentBean;
import com.ptja.android.mms.bean.TaskBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author shenlingnan
 */
public class EquipListAdapter extends BaseAdapter {

    List<EquipmentBean> data;
    Context mActivity;
    Handler mHandler;

    public EquipListAdapter(Context context, List<EquipmentBean> unfinishedTaskListItems, Handler mHanlder) {
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
                    R.layout.equip_list_item_search, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final EquipmentBean bean = data.get(position);
        holder.equipName.setText(bean.getEquipment_name());
        holder.number.setText(bean.getCurrent_number());
        holder.produceDate.setText(bean.getPro_date());
        holder.type.setText(bean.getEquipment_type().getEquipment_type_name());
        holder.unit.setText(bean.getUnit());
        holder.mode.setText(bean.getModel());


//        holder.equipName.setText(bean.get().getTask_type_name());
//        holder.taskLocation.setText(bean.getDepot() != null ? bean.getDepot().getDepot_name() : "");
//        holder.taskName.setText(bean.getTask_content());
//        holder.taskTime.setText("从" + bean.getStart_time() + "到" + bean.getEnd_time());
//        holder.deal.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Message msg = new Message();
//                msg.what = 100;
//                msg.obj = bean;
//                mHandler.sendMessage(msg);
//            }
//        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.equip_name)
        TextView equipName;
        @Bind(R.id.mode)
        TextView mode;
        @Bind(R.id.type)
        TextView type;
        @Bind(R.id.unit)
        TextView unit;
        @Bind(R.id.number)
        TextView number;
        @Bind(R.id.produceDate)
        TextView produceDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
