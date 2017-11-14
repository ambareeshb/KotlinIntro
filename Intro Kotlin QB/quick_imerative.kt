package quick_sort_imperative

fun main(args:Array<String>){
	var array = arrayOf(3,4,5,1,6)
	quickSort(array,0,4)
	array.forEach{print("$it ,")}
}

fun quickSort(array:Array<Int>,start:Int,end:Int){
	
	if(end - start < 1) return

	var pivot = array[start]
	var j = start + 1

	for(i in start .. end){
		if(array[i] < pivot){
			var temp = array[i]
			array[j] = array[i]
			array[i] = temp
			++j
		}
	}

	array[start] = array[j-1]
	array[j-1] = pivot
	quickSort(array,start,j-1)
	quickSort(array,j,end)
	
}


