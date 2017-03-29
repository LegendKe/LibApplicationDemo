/**
 *
 */
package com.ptja.android.mms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ptja.android.mms.R;
import com.ptja.android.mms.bean.EquipTypeBean;
import com.ptja.android.mms.bean.EquipmentBean;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author shenlingnan
 */
public class BottomMenuAdapter extends BaseAdapter {

    List<EquipTypeBean.ResponseBean> data;
    Context mActivity;

    public BottomMenuAdapter(Context context, List<EquipTypeBean.ResponseBean> data) {
        this.data = data;
        this.mActivity = context;
        this.selected = new boolean[data.size()];
        if (selected.length>0){
            selected[0] = true;
        }

    }
    private boolean [] selected;

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
                    R.layout.bottom_menu_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final EquipTypeBean.ResponseBean bean = data.get(position);

        if (selected[position]){
            holder.location_list_view_layout.setBackgroundColor(0xffff0000);
        }else{
            holder.location_list_view_layout.setBackgroundColor(0xfff2f2f2);
        }
        holder.menu.setText(bean.getYear()+"-"+bean.getMonth());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.menu)
        TextView menu;
        @Bind(R.id.location_list_view_layout)
        LinearLayout location_list_view_layout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setSelected(int index){
        Arrays.fill(selected,false);
        selected[index] = true;
        notifyDataSetChanged();
    }
}
