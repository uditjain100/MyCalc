package udit.programmer.co.mycalc

class Stackcar {

    protected var data: CharArray
    protected var tos: Int = 0

    val isFull: Boolean
        get() = this.size() == this.data.size

    val isEmpty: Boolean
        get() = this.size() == 0

    @Throws(Exception::class)
    constructor() {
        this.data = CharArray(5)
        this.tos = -1
    }

    @Throws(Exception::class)
    constructor(cap: Int) {
        if (cap < 1) {
            throw Exception("Invalid Cap")
        }
        this.data = CharArray(cap)
        this.tos = -1
    }

    @Throws(Exception::class)
    fun push(item: Char) {

        if (isFull) {
            throw Exception("Stack is Full")
        }

        tos++
        this.data[this.tos] = item
    }

    @Throws(Exception::class)
    fun pop(): Char {

        if (isEmpty) {
            throw Exception("Stack is Empty")
        }

        val rv = this.data[this.tos]
        this.data[this.tos] = 0.toChar()
        tos--
        return rv
    }

    @Throws(Exception::class)
    fun top(): Char {

        if (isEmpty) {
            throw Exception("Stack is Empty")
        }

        return data[tos]
    }

    fun size(): Int {
        return this.tos + 1
    }

    fun display() {

        for (i in this.tos downTo 0) {
            print(this.data[i] + "\t")
        }
    }


}
