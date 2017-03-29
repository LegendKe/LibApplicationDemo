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
import android.widget.TextView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.bean.EquipApproveBean;
import com.ptja.android.mms.bean.TaskBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author shenlingnan
 */
public class EquipApproveListAdapter extends BaseAdapter {

    List<EquipApproveBean.ResponseBean.DataBean> data;
    Context mActivity;
    Handler mHandler;

    public EquipApproveListAdapter(Context context, List<EquipApproveBean.ResponseBean.DataBean> unfinishedTaskListItems, Handler mHanlder) {
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
                    R.layout.equip_approve_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final EquipApproveBean.ResponseBean.DataBean bean = data.get(position);

        holder.usement.setText(bean.getEquipment_remark());
        holder.dept.setText((CharSequence) bean.getFrom_troop_info().getTroop_name());
        holder.equipName.setText(bean.getEquipment_name());
        StringBuffer sb = new StringBuffer();
//        for (int i = 0;i<bean.gete().size();i++){
//            sb.append(bean.getEquipment_detail().get(i).getEquipment_name()+"\r\n");
//        }
        holder.equip.setText(bean.getEquipment_name());
        holder.requester.setText(bean.getFrom_troop_info().getTroop_name());

        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = 101;
                msg.arg1 = 1;
                msg.obj = bean.getApplication_id();
                mHandler.sendMessage(msg);
            }
        });

        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.what = 101;
                msg.arg1 = 2;
                msg.obj = bean.getApplication_id();
                mHandler.sendMessage(msg);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.equip_name)
        TextView equipName;
        @Bind(R.id.code)
        TextView code;
        @Bind(R.id.requester)
        TextView requester;
        @Bind(R.id.dept)
        TextView dept;
        @Bind(R.id.equip)
        TextView equip;
        @Bind(R.id.usement)
        TextView usement;
        @Bind(R.id.no)
        Button no;
        @Bind(R.id.yes)
        Button yes;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
