import test.Functions
import test.Functions.Static.toDate
import test.Teams
import test.Tournaments
import test.fromDate
import java.util.*


    fun main(args: Array<String>) {
        //Variables
        var mutableVariable = 2
        var mutableVariableDynamic = 2
        var mutableOptionalVariable:Int? = null
        val immutableVariable = 3
        System.out.println("$mutableOptionalVariable,$mutableVariable,$mutableVariableDynamic,$immutableVariable")


        //Infix
        val now = Date()
        val future = Date(now.time + 10000)
        val diffTo = now toDate future
        val diffFrom = future fromDate now
        System.out.println("The difference is $diffTo \n difference type is ${diffTo::class.qualifiedName}")
        System.out.println("The difference is $diffFrom \n difference type is ${diffFrom::class.qualifiedName}")
        //Companions
        Functions.simpleComp()
        //Lambda
        val adder = Functions().adder
        val add5 = adder(5,4)
        System.out.println("Lambda curry add add5(3) : ${add5(3)}")
       //Map testing
        val immutableMap = mapOf(1 to 1,2 to 2,3 to 3)
        val mutableMap = mutableMapOf(1 to 1, 2 to 2,3 to 3)
        //Sealed and Enum
        val winterTournament = listOf(Tournaments.Manager,
                Tournaments.Player("Unni","Bamboo boys"),
                Tournaments.Team("Bamboo boys"))
        println("Sealed type is ${winterTournament::class.qualifiedName}")
        for (value in winterTournament) {
            println("Sealed type is ${value::class.qualifiedName}")
            println(value)
        }

        Teams.TEAM_A

}
