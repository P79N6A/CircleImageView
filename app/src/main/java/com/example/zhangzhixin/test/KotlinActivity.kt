package com.example.zhangzhixin.test


import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import java.util.ArrayList


class KotlinActivity : AppCompatActivity() {

    private var mList: RecyclerView? = null
    private val personList = ArrayList<Person>()
    private var mTitleBar: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_scroller)

        mList = findViewById(R.id.list)

        mTitleBar = findViewById(R.id.titleBar)
        initData()
        val layoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        layoutInflater.inflate(R.layout.list_item_layout, null)
        mList!!.layoutManager = LinearLayoutManager(this)
        mList!!.adapter = MyListAdapter(personList, this)
        EventBus.getDefault().register(this)
        //        FloatingActionButton fab=findViewById(R.id.fab);
        //        fab.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Snackbar.make(findViewById(R.id.contentView),"test",Snackbar.LENGTH_SHORT).show();
        //            }
        //        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: MessaeEvent) {
        mTitleBar!!.text = event.message

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    inner class MyListAdapter(private val persons: List<Person>?, private val mContext: Context) : RecyclerView.Adapter<MyListAdapter.MyHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListAdapter.MyHolder {
            return MyHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item_layout, parent, false))
        }

        override fun onBindViewHolder(holder: MyListAdapter.MyHolder, position: Int) {
            holder.mTextView.text = persons!![position].getName()
        }

        override fun getItemCount(): Int {
            return persons?.size ?: 0
        }


        inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            internal var mTextView: TextView

            init {
                mTextView = itemView.findViewById<View>(R.id.value) as TextView
            }

        }
    }

    fun initData() {
        for (i in 0..19) {
            personList.add(Person("it is data for $i"))
        }

    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    inner class Myadapter : BaseAdapter() {

        override fun getCount(): Int {
            return 0
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View, parent: ViewGroup): View? {
            return null
        }
    }
}
