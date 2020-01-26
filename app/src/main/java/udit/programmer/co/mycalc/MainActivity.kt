package udit.programmer.co.mycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View) {

        when (view.id) {


            R.id.bt01 -> {
                operator("(")
            }

            R.id.bt02 -> {
                operator(")")
            }

            R.id.bt10 -> {
                num("7")
            }

            R.id.bt11 -> {
                num("8")
            }

            R.id.bt12 -> {
                num("9")
            }

            R.id.bt20 -> {
                num("4")
            }

            R.id.bt21 -> {
                num("5")
            }

            R.id.bt22 -> {
                num("6")
            }

            R.id.bt30 -> {
                num("1")
            }

            R.id.bt31 -> {
                num("2")
            }

            R.id.bt32 -> {
                num("3")
            }

            R.id.bt41 -> {
                num("0")
            }

            R.id.bt03 -> {
                operator("/")
            }

            R.id.bt13 -> {
                operator("*")
            }

            R.id.bt23 -> {
                operator("-")
            }

            R.id.bt33 -> {
                operator("+")
            }

            R.id.bt00 -> {
                allclear()
            }

            R.id.bt40 -> {
                backspace()
            }

            R.id.bt42 -> {
                operator(".")
            }

            R.id.bt43 -> {
                equal()
            }
        }

    }

    lateinit var keys: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        keys = arrayOf(
            arrayOf(bt00, bt01, bt02, bt03),
            arrayOf(bt10, bt11, bt12, bt13),
            arrayOf(bt20, bt21, bt22, bt23),
            arrayOf(bt30, bt31, bt32, bt33),
            arrayOf(bt40, bt41, bt42, bt43)
        )

        for (i in 0..4) {
            for (j in 0..3) {

                keys[i][j].setOnClickListener(this)

            }
        }

    }

    fun num(str: String) {

        if (res.text.isNotEmpty()) {
            exp.text = ""
        }

        exp.append(str)
        res.text = ""

    }

    fun operator(str: String) {

        if (res.text.isNotEmpty()) {
            exp.text = ""
        }

        exp.append(res.text)
        exp.append(str)
        res.text = ""


    }

    fun backspace() {

        var ans = exp.text.toString()

        if (ans.isNotEmpty()) {
            exp.text = ans.substring(0, ans.length - 1)
        }

        res.text = ""
    }

    fun allclear() {

        exp.text = ""
        res.text = ""

    }


    fun equal() {

        try {

            val expression = ExpressionBuilder(exp.text.toString()).build()
            val result = expression.evaluate()
            val longres = result.toLong()

            if (result == longres.toDouble()) {

                res.text = longres.toString()

            } else {

                res.text = result.toString()

            }
        } catch (e: Exception) {

            Log.i("Exception", "message : " + e.message)

        }

    }


}
