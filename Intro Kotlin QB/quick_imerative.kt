package quick_sort_imperative

fun main(args:Array<String>){
        var array = args.map{it.toInt()}
                        .toTypedArray()

        val arra_length =  array.size -1
	quickSort(array,0,arra_length)

        println("\rSORTED \n")
	array.forEach{print("$it ,")}
        println("\n")
}

//Recursive sort.
fun quickSort(array:Array<Int>,start:Int,end:Int){
	
	if(end - start < 1) return

        var pivot = array[start]
	var j = start 

	for(i in start+1 .. end){
		if(array[i] < pivot){
			++j
			var temp = array[i]
			array[i] = array[j]
			array[j] = temp
		}
	}


        array[start] = array[j]
	array[j] = pivot

        quickSort(array,start,j-1)
	quickSort(array,j+1,end)
	
}


