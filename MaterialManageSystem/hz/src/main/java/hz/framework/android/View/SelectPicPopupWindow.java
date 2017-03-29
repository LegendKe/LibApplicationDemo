package hz.framework.android.View;

import java.util.List;
import android.app.Activity;
import android.content.Context;  
import android.graphics.drawable.ColorDrawable;  
import android.util.DisplayMetrics;
import android.view.LayoutInflater;  
import android.view.MotionEvent;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.View.OnTouchListener;  
import android.view.ViewGroup.LayoutParams;  
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;  
import android.widget.ListView;
import android.widget.PopupWindow;  
import android.widget.TextView;
import android.widget.Toast;

import hz.framework.android.R;
import hz.framework.android.View.adapter.RadioAdapter;

public class SelectPicPopupWindow extends PopupWindow {  
  
  
    private View mMenuView;  
  
    public SelectPicPopupWindow(final Activity context,final OnConfirmListener onConfirmListener,String title,final List<String> data,final boolean isMulti) {  
        super(context);  
        LayoutInflater inflater = (LayoutInflater) context  
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        mMenuView = inflater.inflate(R.layout.alert_dialog, null); 
        final ListView listview = (ListView) mMenuView.findViewById(R.id.listview);
        TextView titleView = (TextView) mMenuView.findViewById(R.id.diaolog_title_tv);
        titleView.setText(title);
        
        	final RadioAdapter adapter = new RadioAdapter(context, data,isMulti);
            listview.setAdapter(adapter); 
            listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					if(isMulti){
						adapter.onClicked(position);
					}else{
						if(onConfirmListener!=null){
			            	onConfirmListener.onConfirm(data.get(position));
			            }
					  dismiss();
					}
					
				}
			});
            Button confirm = (Button) mMenuView.findViewById(R.id.confirm_btn);
            if(isMulti){
                confirm.setOnClickListener(new OnClickListener() {
        			
        			@Override
        			public void onClick(View v) {
        				String name = "";
        				List<Boolean>checked = adapter.getChecked();
        				boolean hasCheck = false;
        				  for (int i = 0; i < checked.size(); i++) {
        					  if(checked.get(i)){
        						  hasCheck = true;
        						  if(isMulti){
        							  if(name.equals("")){
        								  name = data.get(i);
        							  }else{
        								  name += "," + data.get(i);
        							  }
        						  }else{
        							  name = data.get(i);
        						  }
        					  }
        				  }
        				  if(hasCheck){
        					  if(onConfirmListener!=null){
        			            	onConfirmListener.onConfirm(name);
        			            }
        					  dismiss();
        				  }else{
        					  Toast.makeText(context, "请至少选择一项", Toast.LENGTH_SHORT).show();
        				  }
        			}
        		});
            }else{
            	confirm.setVisibility(View.INVISIBLE);
            }
        
        
      //设置按钮监听
      		//设置SelectPicPopupWindow的View
      		this.setContentView(mMenuView);
      		//设置SelectPicPopupWindow弹出窗体的宽
      		this.setWidth(LayoutParams.FILL_PARENT);
      		 DisplayMetrics dm = new DisplayMetrics();

      		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
      		
      		//设置SelectPicPopupWindow弹出窗体的高
      		this.setHeight(dm.heightPixels/3);
      		//设置SelectPicPopupWindow弹出窗体可点击
      		this.setFocusable(true);
      		//设置SelectPicPopupWindow弹出窗体动画效果
      		this.setAnimationStyle(R.style.AnimBottom);
      		//实例化一个ColorDrawable颜色为半透明
      		ColorDrawable dw = new ColorDrawable(0xffffffff);
      		//设置SelectPicPopupWindow弹出窗体的背景
      		this.setBackgroundDrawable(dw);
    }  
    
    public interface OnConfirmListener{
    	public void onConfirm(String result);
    }
    
    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
    	super.showAtLocation(parent, gravity, x, y);
    }
  
}  
