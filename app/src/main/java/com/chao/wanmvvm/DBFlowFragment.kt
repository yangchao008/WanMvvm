package com.chao.wanmvvm

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chao.wanmvvm.Person_Table.age
import com.chao.wanmvvm.Person_Table.name
import com.dbflow5.query.list
import com.dbflow5.query.select
import kotlinx.android.synthetic.main.fragment_dbflow.*

/**
 * Date: 2019/8/14 17:27
 * Author: hans yang
 * Description:
 */
class DBFlowFragment : Fragment(){

    private val mAdapter by lazy { MyAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dbflow,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter.run {
            bindToRecyclerView(recyclerView)
            setOnItemChildClickListener { adapter, view, position ->
                when(view.id){
                    R.id.btnDelete ->{
                        (adapter.getItem(position) as Person).delete()
                        mAdapter.notifyItemRemoved(position)
                    }
                }
            }
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()

            val persons = (select
                    from Person::class.java
                    where (Person_Table.name eq name)).list

            val person = if (persons.isNotEmpty()){
                persons[0]
            }else {
                Person().apply {
                    this.name = name
                    this.age = age
                }
            }
            val isSave = person.save()
            Snackbar.make(view,"保存成功：$isSave",Snackbar.LENGTH_LONG).show()
        }

        btnSeeAll.setOnClickListener {
            val list = (select from Person::class.java).list
            mAdapter.setNewData(list)
            Snackbar.make(view,"一共有：${list.size}条",Snackbar.LENGTH_LONG).show()
        }

        btnSearch.setOnClickListener {
            val list: List<Person> = if (!etName.text?.toString().isNullOrEmpty() && !etAge.text?.toString().isNullOrEmpty()){
                (select from Person::class.java where (name eq etName.text?.toString())and (age eq etAge.text?.toString()!!.toInt())).list
            }else if(!etName.text?.toString().isNullOrEmpty()){
                (select from Person::class.java where (name eq etName.text?.toString())).list
            }else {
                (select from Person::class.java).list
            }
            mAdapter.setNewData(list)
            Snackbar.make(view,"一共有：${list.size}条",Snackbar.LENGTH_LONG).show()
        }
    }

    class MyAdapter : BaseQuickAdapter<Person,BaseViewHolder>(R.layout.item_dbflow){
        override fun convert(helper: BaseViewHolder?, item: Person) {
            helper!!
                .setText(R.id.tvName,item.name)
                .setText(R.id.tvAge,item.age.toString())
                .addOnClickListener(R.id.btnDelete)
        }

    }
}