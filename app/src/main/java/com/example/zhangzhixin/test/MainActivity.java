package com.example.zhangzhixin.test;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mList;
    private List<Person> personList=new ArrayList<>();
    private TextView mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_scroller);

        mList = findViewById(R.id.list);

        mTitleBar = findViewById(R.id.titleBar);
        initData();
        LayoutInflater layoutInflater= (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.list_item_layout,null);
        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.setAdapter(new MyListAdapter(personList,this));
        EventBus.getDefault().register(this);
//        FloatingActionButton fab=findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(findViewById(R.id.contentView),"test",Snackbar.LENGTH_SHORT).show();
//            }
//        });
    }
     @Subscribe(threadMode = ThreadMode.MAIN)
     public void onEvent(MessaeEvent event){
        mTitleBar.setText(event.getMessage());

     }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyHolder> {
        private List<Person> persons;
        private Context mContext;

        public MyListAdapter(List<Person> persons, Context mContext) {
            this.persons = persons;
            this.mContext = mContext;
        }

        @Override
        public MyListAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_layout,parent,false));
        }

        @Override
        public void onBindViewHolder(MyListAdapter.MyHolder holder, int position) {
             holder.mTextView.setText(persons.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return persons == null ? 0 : persons.size();
        }


        public class MyHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public MyHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.value);
            }

        }
    }
    public void initData(){
        for(int i=0;i<20;i++){
            personList.add(new Person("it is data for "+i));
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    public class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
