package com.example.achievelist

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private var db: AppDatabase? = null
    private var contactsList = mutableListOf<Contacts>()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioBtn1 = findViewById<RadioButton>(R.id.radioBtn1)!!
        val radioBtn2 = findViewById<RadioButton>(R.id.radioBtn2)!!
        val radioBtn3 = findViewById<RadioButton>(R.id.radioBtn3)!!
        val recyclerView: RecyclerView = findViewById(R.id.mainToDoRecyclerView)

        // TODO 05 : ID가 mainToDoButton인 뷰를 찾아서, 변수에 값을 넣어주세요.

        // TODO 06 : ID가 mainToDoEdittext인 뷰를 찾아서, 변수에 값을 넣어주세요.

        db = AppDatabase.getInstance(this)

        val removeFunction = { c: Contacts -> db!!.contactsDao().delete(c) }

        val readFunction = { Log.d(TAG, "onCreate: ${db!!.contactsDao().getAll().size }")}
        val adapter = RecyclerViewAdapter(contactsList, removeFunction, readFunction)

        val savedContacts = db!!.contactsDao().getAll()
        recyclerView.layoutManager = LinearLayoutManager(this)


        recyclerView.adapter = adapter
        if (savedContacts.isNotEmpty()) {
            contactsList.addAll(savedContacts)
        }

        // TODO 07 : 버튼이 클릭되었을 때 실행되도록 중괄호 { 기호 앞에 올바른 내용을 작성하세요.
         {
            var pr = 3

            /* TODO 08
                * radioBtn1이 체크되었다면 pr을 0으로 설정한다
                * radioBtn2이 체크되었다면 pr을 1로 설정한다
                * radioBtn3이 체크되었다면 pr을 2로 설정한다
             */

            // TODO 09 : 위에서 설정한 Edittext의 변수를 아래에 넣어주세요
            val contact = Contacts(변수명.text.toString(), pr, false)
            runBlocking {
                db!!.contactsDao().insertAll(contact)
            }
            contactsList.clear()
            runBlocking {
                contactsList.addAll(db!!.contactsDao().getAll())
            }
            adapter.notifyDataSetChanged()
        }
    }
}