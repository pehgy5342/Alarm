package com.example.alarm

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        tv_date2.setOnClickListener(listener)
        tv_time2.setOnClickListener(listener)
        btn_go.setOnClickListener(listener)


    }

    val listener = View.OnClickListener {

        when (it) {
            tv_date2 -> {
                getDate()
            }
            tv_time2 -> {
                getTime()
            }
            btn_go->{

                btnGo()
            }

        }


    }


    val dateListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

        calendar.set(year, month, dayOfMonth)
        format("yyyy/MM/dd", tv_date2)
    }


    fun getDate() {

        DatePickerDialog(
            this, dateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()

    }

    val timeListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        format("HH:mm", tv_time2)
    }

    fun getTime() {

        TimePickerDialog(
            this,
            timeListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE), true
        ).show()

    }

    fun format(format: String, view: View) {
        val time = SimpleDateFormat(format, Locale.TAIWAN)
        (view as TextView).text = time.format(calendar.time)

    }


    fun btnGo() {

        AlertDialog.Builder(this).setTitle("Party time confirm").setMessage("${tv_date2.text}   ${tv_time2.text}")
            .setPositiveButton("OK") { dialog, which -> }
            .setNegativeButton("CANCEL") { dialog, which -> dialog.cancel() }.create().show()

    }
}
