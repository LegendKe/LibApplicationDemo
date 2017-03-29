package hz.framework.android.View.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import hz.framework.android.R;

public class RadioAdapter extends BaseAdapter {

   private List<String> data;
   private Context c;
   private boolean isMulti;

   public RadioAdapter(Context c, List<String> authors,boolean isMulti) {
       super();
       this.c = c;
       this.data = authors;
       this.isMulti = isMulti;
       resetCheckedList();
   }
   
   private void resetCheckedList(){
	   checkedList = new ArrayList<Boolean>(data.size());
	   for(int i = 0;i<data.size();i++){
		   checkedList.add(false);
	   }
   }

   @Override
   public int getCount() {
       return data.size();
   }

   @Override
   public Object getItem(int arg0) {
       return null;
   }

   @Override
   public long getItemId(int arg0) {
       //返回每一条Item的Id
       return arg0;
   }

   @Override
   public boolean hasStableIds() {
       //getCheckedItemIds()方法要求此处返回为真
       return true;
   }
   @Override
   public View getView(int position, View convertView, ViewGroup arg2) {

	   convertView = LayoutInflater.from(c).inflate(
				R.layout.selectable_listview_item, null);

       TextView text = (TextView) convertView.findViewById(R.id.text);
       ImageView checkable = (ImageView) convertView.findViewById(R.id.checkable);
       
       text.setText(data.get(position));
       if(checkedList.get(position)){
    	   checkable.setImageResource(R.drawable.yes);
       }else{
    	   checkable.setImageBitmap(null);
       }
   return convertView;
   }
   
   List<Boolean> checkedList = new ArrayList<Boolean>();
   public void onClicked(int i ){
	   if(!checkedList.get(i)){
		   if(isMulti){
			   checkedList.set(i,true);
		   }else{
			   resetCheckedList();
			   checkedList.set(i,true);
		   }
	   }else{
		   checkedList.set(i, false);
	   }
	   notifyDataSetChanged();
   }
   public List<Boolean> getChecked(){
	   return checkedList;
   }
}
