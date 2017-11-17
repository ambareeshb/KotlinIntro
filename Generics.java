public class Generics{ 

public static void main(String[] args){

        Fruits<Object> fruits = new Fruits("green");
        Fruits<String> orages = new Fruits("orange");
}

 class Fruits<T> {
        T t;

        Fruits(T t) {
            this.t = t;
        }

        T getT() {
            return t;
        }
    }
}
