package com.example.tribunomobile.view.adapter


import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.tribunomobile.R
import com.example.tribunomobile.service.model.OperationDetailModel


class OperationDetailAdapter( plistOperationDetail: List<OperationDetailModel>, pActivity: Activity ) : BaseAdapter() {

    private var listOperationDetail: List<OperationDetailModel> = plistOperationDetail

    private var activity: Activity = pActivity

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =  activity.layoutInflater.inflate(R.layout.list_operation_detail, parent, false)
        val operationDetailModel: OperationDetailModel = listOperationDetail[position]

        val number =  view.findViewById<View>(R.id.text_Number) as TextView
        val value =  view.findViewById<View>(R.id.text_Value) as TextView
        val dueDate =  view.findViewById<View>(R.id.text_dateDue) as TextView

        number.text = operationDetailModel.getNumber()
        value.text = operationDetailModel.getValue()
        dueDate.text = operationDetailModel.getDate()

        return view
    }

    override fun getItem(position: Int): Any {
        return listOperationDetail[position]
    }

    override fun getItemId(position: Int): Long {
        return  listOperationDetail[position].id.toLong()
    }

    override fun getCount(): Int {
        return listOperationDetail.size
    }
}