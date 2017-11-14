

fun main(args:Array<String>){
	print(args.length())
val sorted =  args.map{it.toInt()}.quickSort()
print (sorted)
	
}
fun <T : Comparable<T>> List<T>.quickSort(): List<T> = when {
    size < 2 -> this
    else -> {
        val pivot = first()
        val (smaller, greater) = drop(1).partition { it <= pivot }
        smaller.quickSort() + pivot + greater.quickSort()
    }
}
