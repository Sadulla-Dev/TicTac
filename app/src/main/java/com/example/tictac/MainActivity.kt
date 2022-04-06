package com.example.tictac

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import kotlinx.android.synthetic.main.player1_win.view.*
import kotlinx.android.synthetic.main.player2_win.view.*
import kotlinx.android.synthetic.main.player_draw.*
import kotlinx.android.synthetic.main.player_info.view.*

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}