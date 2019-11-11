package udit.programmer.co.mycalc

import android.graphics.Color
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

                //equallogic(exp.text.toString(), Stackcar(), Stack())

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

       /* val abc: String = exp.text.substring(exp.length() - 1)

        if (abc.equals("+") || abc.equals("-") || abc.equals("*") || abc.equals("/")) {
            return
        } */
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

    fun equallogic(str: String, stack: Stackcar, stack1: Stack) {


        var ans: String = ""
        var flag: Int = 0

        for (i in 0..str.length - 1) {
            var ch: Char = str.elementAt(i)
            if (Character.isLetterOrDigit(ch)) {
                ans += ch
            } else if (ch == '(') {
                stack.push(ch)
            } else if (ch == ')') {
                while (!stack.isEmpty && stack.top() != '(') {
                    var rv: Char = stack.pop()
                    ans += rv
                }
            } else {
                if (flag == 0) {
                    stack.push(ch)
                    flag = 1
                } else {
                    if (stack.top() == '(') {
                        stack.push(ch)
                    } else if (Prec(stack.top()) < Prec(ch)) {
                        stack.push(ch)
                    } else if (Prec(stack.top()) >= Prec(ch)) {
                        while (!stack.isEmpty && Prec(stack.top()) >= Prec(ch)) {
                            var rv: Char = stack.pop()
                            ans += rv
                        }
                        stack.push(ch)
                    }
                }
            }

        }

        while (!stack.isEmpty) {
            var rv: Char = stack.pop()
            if (rv != '(') {
                ans += rv
            }
        }

        postfix(ans, stack1)
        //res.text = ans

    }

    fun Prec(ch: Char): Int {
        if (ch == '+' || ch == '-') {
            return 1
        } else if (ch == '*' || ch == '/') {
            return 2
        } else if (ch == '^') {
            return 3
        } else {
            return -1
        }
    }

    fun postfix(str: String, stack: Stack) {

        var i: Int = 0

        for (i in 0..str.length - 1) {

            var ch: Char = str.elementAt(i)

            if (ch >= '0' && ch <= '9') {
                stack.push(cti(ch))
            } else if (ch == '^' || ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                var a: Int = stack.pop()
                var b: Int = stack.pop()

                if (ch == '+') {
                    stack.push(a + b)
                } else if (ch == '-') {
                    stack.push(a - b)
                } else if (ch == '*') {
                    stack.push(a * b)
                } else if (ch == '/') {
                    stack.push(a / b)
                } else if (ch == '^') {
                    stack.push(Math.pow(a.toDouble(), b.toDouble()).toInt())
                }
            }
        }
        if (i == 0) {
            res.text = stack.data[0].toString()
        }
    }

    fun cti(c: Char): Int {

        if (c == '0') {
            return 0
        } else if (c == '1') {
            return 1
        } else if (c == '2') {
            return 2
        } else if (c == '3') {
            return 3
        } else if (c == '4') {
            return 4
        } else if (c == '5') {
            return 5
        } else if (c == '6') {
            return 6
        } else if (c == '7') {
            return 7
        } else if (c == '8') {
            return 8
        } else {
            return 9
        }


    }

}
