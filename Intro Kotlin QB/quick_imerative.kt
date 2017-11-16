package quick_sort_imperative

fun main(args:Array<String>){
	var array = arrayOf(3,4,5,1,6,34,23,4,5,12,23)
	array.forEach{print("$it ,")}
        val arra_length =  array.size -1
        println("aray length is $arra_length")
	quickSort(array,0,0)
        println("\n The sorted array is")
	array.forEach{print("$it ,")}
}

fun quickSort(array:Array<Int>,start:Int,end:Int){
	
	if(end - start < 1) return

        var pivot = array[start]
	var j = start 

	for(i in start+1 .. end+1){
		if(array[i] < pivot){
			++j
			var temp = array[i]
			array[i] = array[j]
			array[j] = temp
		}
	}

//        println("\nAfter partition start $start")
 //       array.forEach{print("$it,")}

        array[start] = array[j]
	array[j] = pivot

  //      println("Calling quick sort")
        quickSort(array,start,j)
   //     println("Calling quick sort")
	quickSort(array,j+1,end)
	
}


