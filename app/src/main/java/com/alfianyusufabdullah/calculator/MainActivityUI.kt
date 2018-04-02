package com.alfianyusufabdullah.calculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.jetbrains.anko.*
import java.text.DecimalFormat

/**
 * Created by jonesrandom on 3/24/18.
 *
 * @site www.androidexample.web.id
 * @github @alfianyusufabdullah
 *
 */
class MainActivityUI : AnkoComponent<MainActivity>, View.OnClickListener {

    private val ID_LAYOUT = 1
    private val ID_TEXT_MAIN = 2
    private val ID_TEXT_DETAIL = 3

    private val marginButton = 7
    private var textMain = "0"
    private var textOperator = ""
    private var isOperatorSelected = false

    private val decimalFormat = DecimalFormat("0.##")

    private lateinit var tvMain: TextView
    private lateinit var tvDetail: TextView

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        val calculator = v as Button
        when (calculator.text) {
            "DEL" -> {
                textMain = if (textMain.length > 1) {
                    textMain.substring(0, textMain.length - 1)
                } else {
                    "0"
                }

                textMain = decimalFormat.format(textMain.toDouble())
                tvMain.text = "$textOperator $textMain"

                if (tvMain.text.length > 12){
                    tvMain.textSize = 40f
                } else {
                    tvMain.textSize = 60f
                }
            }

            "AC" -> {
                if (isOperatorSelected) {
                    isOperatorSelected = false
                    tvDetail.visibility = View.GONE
                }

                textOperator = ""
                textMain = "0"
                tvMain.text = textMain
                tvMain.textSize = 60f
            }

            "/", "+", "*", "-" -> {

                if (textMain != "0") {
                    textOperator = calculator.text.toString()

                    if (isOperatorSelected) {
                        tvMain.text = "$textOperator $textMain"
                    } else {
                        tvDetail.visibility = View.VISIBLE
                        tvDetail.text = textMain

                        tvMain.text = textOperator

                        isOperatorSelected = true
                        textMain = "0"
                    }
                }
            }

            "=" -> {

                if (isOperatorSelected) {
                    val numberOne = tvDetail.text.toString().toDouble()
                    val numberTwo = textMain.toDouble()
                    val result = when (textOperator) {
                        "/" -> numberOne.div(numberTwo)
                        "*" -> numberOne.times(numberTwo)
                        "+" -> numberOne.plus(numberTwo)
                        else -> numberOne.minus(numberTwo)
                    }

                    textOperator = ""
                    textMain = decimalFormat.format(result)
                    tvMain.text = textMain

                    if (tvMain.text.length > 12){
                        tvMain.textSize = 40f
                    } else {
                        tvMain.textSize = 60f
                    }

                    tvDetail.visibility = View.GONE
                    isOperatorSelected = false
                }
            }

            else -> {
                if (textMain.length < 10) textMain += calculator.text

                textMain = decimalFormat.format(textMain.toDouble())
                tvMain.text = "$textOperator $textMain"

                if (tvMain.text.length > 12){
                    tvMain.textSize = 40f
                } else {
                    tvMain.textSize = 60f
                }
            }
        }

    }

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {

        val btnSize = resources.displayMetrics.widthPixels / 6
        relativeLayout {

            tvDetail = textView {
                id = ID_TEXT_DETAIL
                text = "0"
                textSize = 20f
                this.visibility = View.GONE

            }.lparams {
                alignParentEnd()
                above(ID_TEXT_MAIN)
                marginEnd = 15
            }
            tvMain = textView {
                id = ID_TEXT_MAIN
                text = "0"
                textSize = 60f
                this.gravity = Gravity.END
            }.lparams {
                alignParentEnd()
                above(ID_LAYOUT)
                margin = 10
            }

            verticalLayout {
                id = ID_LAYOUT

                linearLayout {
                    button("AC") {
                        textSize = 17f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("DEL") {
                        textSize = 17f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("/") {
                        textSize = 17f
                        textColor = Color.WHITE
                        backgroundResource = R.drawable.bg_button_operator
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                }.lparams(width = wrapContent, height = wrapContent) {
                    this.gravity = Gravity.END
                }
                linearLayout {
                    button("7") {
                        textSize = 25f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("8") {
                        textSize = 25f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("9") {
                        textSize = 25f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("*") {
                        textSize = 25f
                        textColor = Color.WHITE
                        backgroundResource = R.drawable.bg_button_operator
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                }.lparams(width = wrapContent, height = wrapContent)
                linearLayout {
                    button("4") {
                        textSize = 25f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("5") {
                        textSize = 25f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("6") {
                        textSize = 25f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("-") {
                        textSize = 25f
                        textColor = Color.WHITE
                        backgroundResource = R.drawable.bg_button_operator
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                }.lparams(width = wrapContent, height = wrapContent)
                linearLayout {
                    button("1") {
                        textSize = 25f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("2") {
                        textSize = 25f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("3") {
                        textSize = 25f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("+") {
                        textSize = 25f
                        textColor = Color.WHITE
                        backgroundResource = R.drawable.bg_button_operator
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                }.lparams(width = wrapContent, height = wrapContent)
                linearLayout {

                    button("0") {
                        textSize = 25f
                        textColorResource = R.color.colorPrimaryDark
                        backgroundResource = R.drawable.bg_button_angka
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("no") {
                        visibility = View.INVISIBLE
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                    button("=") {
                        textSize = 25f
                        textColor = Color.WHITE
                        backgroundResource = R.drawable.bg_button_operator
                        setOnClickListener(this@MainActivityUI)
                    }.lparams(width = btnSize, height = btnSize) {
                        margin = marginButton
                    }

                }.lparams(width = wrapContent, height = wrapContent) {
                    this.gravity = Gravity.END
                }

            }.lparams(width = wrapContent, height = wrapContent) {
                alignParentBottom()
                centerHorizontally()
                margin = 10
            }
        }
    }
}
