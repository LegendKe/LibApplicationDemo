/**
 *
 */
package com.ptja.android.mms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
public class EquipmentListAdapter extends BaseAdapter {

    List<EquipmentBean> data;
    Context mActivity;

    public EquipmentListAdapter(Context context, List<EquipmentBean> unfinishedTaskListItems) {
        this.data = unfinishedTaskListItems;
        this.mActivity = context;
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
        if (convertView == null){
            convertView = LayoutInflater.from(mActivity).inflate(
                    R.layout.equip_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final EquipmentBean bean = data.get(position);

        holder.equipName.setText(bean.getEquipment_name());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.equip_name)
        TextView equipName;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
