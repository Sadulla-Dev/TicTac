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

class MainActivity : AppCompatActivity() , View.OnClickListener {
    private val buttons: Array<Array<Button?>> = Array<Array<Button?>>(3) { arrayOfNulls<Button>(3) }
    private var player1Turn = true
    private var roundCount = 0
    private var player1Points = 0
    private var player2Points = 0
    private lateinit var texviewP1: TextView
    private lateinit var texviewP2: TextView
    private lateinit var card: CardView
    private lateinit var user1: TextView
    private lateinit var user2: TextView
    private var playerFirst = ""
    private var playerSecond = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        texviewP1 = findViewById(R.id.text_viewP1)
        texviewP2 = findViewById(R.id.text_viewP2)
        user2 = findViewById(R.id.user2)
        user1 = findViewById(R.id.user1)
        card = findViewById(R.id.card)
        getPlayerName()
        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById(resID)
                buttons[i][j]!!.setOnClickListener(this)
            }
        }


        val buttonReset: ImageButton = findViewById(R.id.resetBtn)
        buttonReset.setOnClickListener {
            resetGame()
        }

        texviewP1.setOnClickListener {
            changeName()
        }
        texviewP2.setOnClickListener {
            changeName()
        }

        card.setOnClickListener {
            Toast.makeText(this, "$player1Points | $player2Points", Toast.LENGTH_SHORT).show()
        }



    }
    private fun changeName(){
        val mDialogView = LayoutInflater.from(this@MainActivity).inflate(R.layout.player_info,null)
        val player1Name = mDialogView.findViewById<EditText>(R.id.firstPl1)
        val player2Name = mDialogView.findViewById<EditText>(R.id.firstPl2)
        val plDialog = AlertDialog.Builder(this@MainActivity)
        plDialog.setView(mDialogView)

        val mAlertDialog = plDialog.show()
        player1Name.setText(playerFirst)
        player2Name.setText(playerSecond)
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mDialogView.add_btn.setOnClickListener {


            playerFirst = player1Name.text.toString()
            playerSecond = player2Name.text.toString()

            texviewP1.text = playerFirst
            texviewP2.text = playerSecond
            mAlertDialog.dismiss()
        }
        mDialogView.cannel_btn.setOnClickListener {
            playerFirst = playerFirst
            playerSecond = playerSecond

            texviewP1.text = playerFirst
            texviewP2.text = playerSecond
            mAlertDialog.dismiss()
        }

    }

    private fun getPlayerName() {
        val mDialogView = LayoutInflater.from(this@MainActivity).inflate(R.layout.player_info,null)
        val player1Name = mDialogView.findViewById<EditText>(R.id.firstPl1)
        val player2Name = mDialogView.findViewById<EditText>(R.id.firstPl2)
        val add_btn= mDialogView.findViewById<AppCompatButton>(R.id.add_btn)
        val cannel_btn = mDialogView.findViewById<AppCompatButton>(R.id.cannel_btn)
        val plDialog = AlertDialog.Builder(this@MainActivity)
        plDialog.setView(mDialogView)

        val mAlertDialog = plDialog.show()

        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mDialogView.add_btn.setOnClickListener {
            if (player1Name.text.toString().isEmpty()
                && player2Name.text.toString().isEmpty()
            ){
                playerFirst = "Player1"
                playerSecond = "Player2"

                texviewP1.text = playerFirst
                texviewP2.text = playerSecond

            }
            else{
                playerFirst = player1Name.text.toString()
                playerSecond = player2Name.text.toString()

                texviewP1.text = playerFirst
                texviewP2.text = playerSecond
            }
            mAlertDialog.dismiss()
        }
        mDialogView.cannel_btn.setOnClickListener {
            playerFirst = "Player1"
            playerSecond = "Player2"

            texviewP1.text = playerFirst
            texviewP2.text = playerSecond
            mAlertDialog.dismiss()
        }
    }

    override fun onClick(v: View) {
        if (!(v as Button).getText().toString().equals("")) {
            return
        }
        if (player1Turn) {
            (v as Button).setText("X")
            v.setTextColor(resources.getColor(R.color.red))
        } else {
            (v as Button).setText("O")
            v.setTextColor(resources.getColor(R.color.green))
        }
        roundCount++
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins()
            } else {
                player2Wins()
            }
        } else if (roundCount == 9) {
            draw()
        } else {
            player1Turn = !player1Turn
        }
    }

    private fun checkForWin(): Boolean {
        val field = Array(3) { arrayOfNulls<String>(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j]!!.text.toString()
            }
        }
        for (i in 0..2) {
            if (field[i][0] == field[i][1] && field[i][0] == field[i][2] && field[i][0] != "") {
                return true
            }
        }
        for (i in 0..2) {
            if (field[0][i] == field[1][i] && field[0][i] == field[2][i] && field[0][i] != "") {
                return true
            }
        }
        if (field[0][0] == field[1][1] && field[0][0] == field[2][2] && field[0][0] != "") {
            return true
        }
        return if (field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] != "") {
            true
        } else false
    }

    private fun player1Wins() {
        player1Points++
        val mDialogView = LayoutInflater.from(this@MainActivity).inflate(R.layout.player1_win,null)
        val plDialog = AlertDialog.Builder(this@MainActivity)
        plDialog.setView(mDialogView)
        val mAlertDialog = plDialog.show()
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.text_name1.text = playerFirst



        updatePointsText()
        resetBoard()
    }

    private fun player2Wins() {
        player2Points++

        val mDialogView = LayoutInflater.from(this@MainActivity).inflate(R.layout.player2_win,null)
        val plDialog = AlertDialog.Builder(this@MainActivity)
        plDialog.setView(mDialogView)
        val mAlertDialog = plDialog.show()
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mDialogView.text_name2.text = playerSecond


        updatePointsText()
        resetBoard()
    }

    private fun draw() {


        val mDialogView = LayoutInflater.from(this@MainActivity).inflate(R.layout.player_draw,null)
        val plDialog = AlertDialog.Builder(this@MainActivity)
        plDialog.setView(mDialogView)
        val mAlertDialog = plDialog.show()
        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mAlertDialog.ok_btn.setOnClickListener {
            resetBoard()
            mAlertDialog.dismiss()
        }
        mAlertDialog.cannel_btn.setOnClickListener {
            resetBoard()
            mAlertDialog.dismiss()
        }


    }

    private fun updatePointsText() {
        user1.text = player1Points.toString()
        user2.text = player2Points.toString()
    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j]!!.setText("")
            }
        }
        roundCount = 0
        player1Turn = true
    }

    private fun resetGame() {
        player1Points = 0
        player2Points = 0
        updatePointsText()
        resetBoard()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("roundCount", roundCount)
        outState.putInt("player1Points", player1Points)
        outState.putInt("player2Points", player2Points)
        outState.putBoolean("player1Turn", player1Turn)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        roundCount = savedInstanceState.getInt("roundCount")
        player1Points = savedInstanceState.getInt("player1Points")
        player2Points = savedInstanceState.getInt("player2Points")
        player1Turn = savedInstanceState.getBoolean("player1Turn")
    }
}