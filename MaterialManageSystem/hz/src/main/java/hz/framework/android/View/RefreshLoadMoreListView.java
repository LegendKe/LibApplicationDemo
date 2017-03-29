package hz.framework.android.View;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import hz.framework.android.R;


/**
 * Created by yanbli on 4/7/2016.
 */
public class RefreshLoadMoreListView extends RelativeLayout implements SwipeRefreshLayout.OnRefreshListener, LoadMoreListView.OnLoadMore {
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreListView loadMoreListView;
    private BaseAdapter adapter;
    private RefreshLoadMoreListviewListener refreshLoadMoreListviewListener;
    public RefreshLoadMoreListView(Context context) {
        super(context);
        init(context);
    }

    public RefreshLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RefreshLoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setAdapter(BaseAdapter adapter, AdapterView.OnItemClickListener onItemClickListener)
    {
        this.adapter = adapter;
        this.loadMoreListView.setAdapter(this.adapter);
        this.loadMoreListView.setOnItemClickListener(onItemClickListener);
    }

    public void setRefreshLoadmoreListenr(RefreshLoadMoreListviewListener refreshLoadmoreListenr)
    {
        this.refreshLoadMoreListviewListener = refreshLoadmoreListenr;
    }

    public void disableLoadmore(){
        this.loadMoreListView.disableLoadmore();
    }

    public void notifyDataSetChanged()
    {
        adapter.notifyDataSetChanged();
    }

    private void init(final Context context)
    {
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.view_refreshloadmorelistview, this);
        this.swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.view_refreshloadmorelistview_swiperefreshlayout);
        this.swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        this.swipeRefreshLayout.setOnRefreshListener(this);

        this.loadMoreListView = (LoadMoreListView) rootView.findViewById(R.id.view_refreshloadmorelistview_listView);
        this.loadMoreListView.setLoadMoreListen(this);
    }

    @Override
    public void loadMore() {
        if (refreshLoadMoreListviewListener != null)
        {
            refreshLoadMoreListviewListener.loadMore(new RefreshUIListener() {
                @Override
                public void onUpdate() {
                    loadMoreListView.onLoadComplete();
//                    Toast.makeText(context, "加载完成", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onRefresh() {
        if (refreshLoadMoreListviewListener != null)
        {
//            Toast.makeText(context, "开始刷新啦", Toast.LENGTH_SHORT).show();
            refreshLoadMoreListviewListener.onRefrsh(new RefreshUIListener() {
                @Override
                public void onUpdate() {
                    if(swipeRefreshLayout.isShown()){
                        swipeRefreshLayout.setRefreshing(false);
                    }
//                    Toast.makeText(context, "刷新完成了", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public interface RefreshLoadMoreListviewListener{
        void onRefrsh(RefreshUIListener listener);
        void loadMore(RefreshUIListener listener);
    }

    public interface RefreshUIListener {
        void onUpdate();
    }
}
