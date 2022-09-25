public class Polynomial {
    double[] myArray;

    public Polynomial(){
        this.myArray = new double[1];
        this.myArray[0] = 0.0; 
    }

    public Polynomial(double [] anotherArray){
        this.myArray = anotherArray;
    }

    public Polynomial add(Polynomial anotherPoly){
        int len1 = this.myArray.length;
        int len2 = anotherPoly.myArray.length;
        double[] newArray;
        if(len1 > len2) {
        	newArray = new double[len1];
        }else {
        	newArray = new double[len2];
        }
        int i;
        for(i = 0; i < len1; i++){
            newArray[i] += this.myArray[i];
        }
        for(i = 0; i < len2; i++){
            newArray[i] += anotherPoly.myArray[i];
        }
        return new Polynomial(newArray);
    }

    public double evaluate(double variable){
        int len = this.myArray.length;
        double result = 0;
        int i;
        for(i = 0; i < len; i++){
            result += Math.pow(variable, i) * this.myArray[i];
        }
        return result;
    }

    public boolean hasRoot(double target){
        return evaluate(target) == 0.0;
    }
}
