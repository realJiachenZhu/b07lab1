import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.io.File;

public class Polynomial {
    double[] myArray;
    int[] exponents;

    public Polynomial(){
        this.myArray = new double[1];
        this.exponents = new int[1]; 
    }

    public Polynomial(double [] anotherArray, int[] exponents){
        this.myArray = anotherArray;
        this.exponents = exponents;
    }
    
    
    public Polynomial(File file) throws IOException {
    	Scanner input = new Scanner(file);
    	String line = input.nextLine();
    	input.close();
    	line = line.replace("-", "+-");
    	String[] terms = line.split("\\+");
    	this.myArray = new double[terms.length];
    	this.exponents = new int[terms.length];
    	int i = 0;
    	for(String term : terms) {
    		String[] tmp = term.split("x");
    		this.myArray[i] = Double.parseDouble(tmp[0]);
    		if(tmp.length == 1) {
    			this.exponents[i] = 0;
    		}else {
    			this.exponents[i] = Integer.parseInt(tmp[1]);
    		}
    		i++;
    	}
    }
    
    public void saveToFile(String thefile) throws IOException {
    	FileWriter fw = new FileWriter(thefile);
    	String tmp = "";
    	int i = 0;
    	int cur_length = this.exponents.length;
    	for(i = 0; i < cur_length; i++) {
    		if(this.exponents[i] == 0) {
    			tmp = tmp + this.myArray[i];
    		}else {
    			tmp = tmp + this.myArray [i] + "x" + this.exponents[i];
    		}
    		if(i < cur_length - 1) {
    			if(this.myArray[i + 1] > 0) {
    				tmp = tmp + "+";
    			}else {
    				continue;
    			}
    		}
    	}
    	fw.write(tmp);
    	fw.close();
    }
    

    public Polynomial add(Polynomial anotherPoly){
        int len1 = this.exponents.length;
        int len2 = anotherPoly.exponents.length;
        int i;
        int count = 0;
        int max_len = Math.max(this.exponents[len1 - 1], anotherPoly.exponents[len2 - 1]);
        double[] newpolylist = new double[max_len + 1];
        for(i = 0; i < len1; i++) {
        	newpolylist[this.exponents[i]] += this.myArray[i];
        }
        for(i = 0; i < len2; i++) {
        	newpolylist[anotherPoly.exponents[i]] += anotherPoly.myArray[i];
        }
        for(i = 0; i < max_len + 1; i++) {
        	if(newpolylist[i] != 0) {
        		count++;
        	}
        }
        double[] finalpolylist = new double[count];
        int[] finalexpolist = new int[count];
        for(i = 0, count = 0; i < max_len + 1; i++) {
        	if(newpolylist[i] != 0) {
        		finalpolylist[count] = newpolylist[i];
        		finalexpolist[count] = i;
        		count++;
        	}
        }
        return new Polynomial(finalpolylist, finalexpolist);
    }

    public double evaluate(double variable){
        int len = this.myArray.length;
        double result = 0;
        int i;
        for(i = 0; i < len; i++){
            result += Math.pow(variable, this.exponents[i]) * this.myArray[i];
        }
        return result;
    }

    public boolean hasRoot(double target){
        return evaluate(target) == 0.0;
    }
    
    public Polynomial multiply(Polynomial anotherPoly){
    	int len1 = this.exponents.length;
        int len2 = anotherPoly.exponents.length;
        int max_len = this.exponents[len1 - 1] + anotherPoly.exponents[len2 - 1] + 1;
        int i;
        int j;
        int count;
        double curpoly1;
        double curpoly2;
        int curexpo1;
        int curexpo2;
        double newpoly;
        int newexpo;
        double[] newpolylist = new double[max_len];
        for(i = 0; i < len1; i++){
        	curpoly1 = this.myArray[i];
        	curexpo1 = this.exponents[i];
        	for(j = 0; j < len2; j++) {
        		curpoly2 = anotherPoly.myArray[j];
        		curexpo2 = anotherPoly.exponents[j];
        		newpoly = curpoly1 * curpoly2;
        		newexpo = curexpo1 + curexpo2;
        		newpolylist[newexpo] += newpoly; 
        	}
        }
        j = 0;
        for(count = 0; count < max_len; count++) {
        	if(newpolylist[count] != (double) 0) {
        		j++;
        	}
        }
        double[] finalpolylist = new double[j];
        int[] finalexpolist = new int[j];
        count = 0;
        for(j = 0; j < max_len; j++) {
        	if(newpolylist[j] != 0) {
        		finalexpolist[count] = j;
        		finalpolylist[count] = newpolylist[j];
        		count++;
        	}
        }
        return new Polynomial(finalpolylist, finalexpolist);
    }
}
