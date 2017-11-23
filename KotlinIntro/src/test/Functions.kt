package test

import java.util.*

infix fun Date.fromDate(from: java.util.Date) = this.time - from.time
class Functions {
    //private variable
    private var value = ""
    //Date infix notation
    companion object Static {
        infix fun Date.toDate(to: java.util.Date) = to.time - this.time
        fun simpleComp() {
            System.out.println("Hi companions ")
        }


    }

    //Simple lambda.
    val adder = { firstNum: Int,finalNum: Int -> { secondNum: Int -> firstNum + secondNum + finalNum } }

}