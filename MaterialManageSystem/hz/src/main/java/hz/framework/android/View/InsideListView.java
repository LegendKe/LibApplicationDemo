package hz.framework.android.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class InsideListView extends ListView {

	public InsideListView(Context context) {
		super(context);
	}

	public InsideListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public InsideListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	public void setAdapter(ListAdapter adapter) {
		super.setAdapter(adapter);
		setListViewHeight();
	}

	private void setListViewHeight(){  
		  try {
			  int totalHeight = 0;//总的高度
			  for (int i = 0; i < getAdapter().getCount(); i++) { //initData(0).size()数据的行数
			  View listItem = getAdapter().getView(i, null, this); //list1,当前listview
			  listItem.measure(0, 0);
			  totalHeight += listItem.getMeasuredHeight();
			  }

			  ViewGroup.LayoutParams params = getLayoutParams();
			  params.height = totalHeight
			  + (getDividerHeight() * (getAdapter().getCount() - 1));
			  setLayoutParams(params); 
		} catch (Exception e) {
		}   
  }   
	
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
//				MeasureSpec.AT_MOST);
//		super.onMeasure(widthMeasureSpec, expandSpec);
//	}
}
