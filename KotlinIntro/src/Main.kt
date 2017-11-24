import test.*
import test.Functions.Static.toDate
import java.util.*


    fun main(args: Array<String>) {
        //Variables
        var mutableVariable = 2
        var mutableVariableDynamic = 2
        var mutableOptionalVariable:Int? = null
        val immutableVariable = 3
        System.out.println("$mutableOptionalVariable,$mutableVariable,$mutableVariableDynamic,$immutableVariable")


        //Infix
        printSpacesAndTitle(2,"Infix")
        val now = Date()
        val future = Date(now.time + 10000)
        val diffTo = now toDate future
        val diffFrom = future fromDate now
        System.out.println("The difference is $diffTo \n difference type is ${diffTo::class.qualifiedName}")
        System.out.println("The difference is $diffFrom \n difference type is ${diffFrom::class.qualifiedName}")

        //Companions
        printSpacesAndTitle(2,"Comapanions")
        Functions.simpleComp()

        //Lambda
        printSpacesAndTitle(2,"Lambda")
        val adder = Functions().adder
        val add5 = adder(5,4)
        System.out.println("Lambda curry add add5(3) : ${add5(3)}")

        //Map testing
        val immutableMap = mapOf(1 to 1,2 to 2,3 to 3)
        val mutableMap = mutableMapOf(1 to 1, 2 to 2,3 to 3)

        //Sealed and Enum
        printSpacesAndTitle(2,"Sealed And Enum")
        val winterTournament = listOf(Tournaments.Manager,
                Tournaments.Player("Unni","Bamboo boys"),
                Tournaments.Team("Bamboo boys"))
        println("Sealed type is ${winterTournament::class.qualifiedName}")
        for (value in winterTournament) {
            println("Sealed type is ${value::class.qualifiedName}")
            println(value)
        }

        //Delegates
        printSpacesAndTitle(2,"Delegates")
        TestDelegate().userName = "second value" //Observable
        val lazyVal = TestDelegate().userLazyRole //Lazy Delegate
        println("Lazy propery after initialization is $lazyVal ")
        TestDelegate().mapIt(mapOf("name" to "ruky","age" to 21)) //Map Delegate
        var customDelegate by CustomDelegate()
        customDelegate = "The white car with blue paint"
        printSpacesAndTitle(2,"Delegates")

    }

/**
 *print number of spaces given in terminal.
 */
fun printSpacesAndTitle(spaces:Int,title: String){
   for(spaceCount in 0 until spaces) println("\n")
    println("$title \n")
}
