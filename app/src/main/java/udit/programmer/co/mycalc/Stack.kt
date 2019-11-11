package udit.programmer.co.mycalc

class Stack {

    public var data: IntArray
    public var tos: Int = 0

    val isFull: Boolean
        get() = this.size() == this.data.size

    val isEmpty: Boolean
        get() = this.size() == 0

    @Throws(Exception::class)
    constructor() {
        this.data = IntArray(5)
        this.tos = -1
    }

    @Throws(Exception::class)
    constructor(cap: Int) {
        if (cap < 1) {
            throw Exception("Invalid Cap")
        }
        this.data = IntArray(cap)
        this.tos = -1
    }

    @Throws(Exception::class)
    fun push(item: Int) {

        if (isFull) {
            throw Exception("Stack is Full")
        }

        tos++
        this.data[this.tos] = item
    }

    @Throws(Exception::class)
    fun pop(): Int {

        if (isEmpty) {
            throw Exception("Stack is Empty")
        }

        val rv = this.data[this.tos]
        this.data[this.tos] = 0
        tos--
        return rv
    }

    @Throws(Exception::class)
    fun top(): Int {

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
            print(this.data[i].toString() + "\t")
        }
    }
}

