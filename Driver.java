public class Driver {
    public static void main(String [] args) {
    	double [] coef1 = {1, -2, 3};
        int [] expo1 = {0, 1, 5};
        Polynomial p1 = new Polynomial(coef1, expo1);

        double[] coef2 = {1, -3, 5, 7};
        int[] expo2 = {1, 2, 3, 5};
        Polynomial p2 = new Polynomial(coef2, expo2);

        System.out.println("TEST ADD FUNCTION");
        System.out.println();
        Polynomial tmp_p = p1.add(p2);
        for (int i = 0; i < tmp_p.myArray.length; i++)
            System.out.print(tmp_p.myArray[i] + " ");
        System.out.println();
        System.out.println("Result for coefficients");

         for (int i = 0; i < tmp_p.exponents.length; i++)
             System.out.print(tmp_p.exponents[i] + " ");
         System.out.println();
         System.out.println("Result for exponents");

         System.out.println("TEST MULTIPLY FUNCTION");
         System.out.println();
         tmp_p = p1.multiply(p2);
         for (int i = 0; i < tmp_p.myArray.length; i++)
             System.out.print(tmp_p.myArray[i] + " ");
         System.out.println();
         System.out.println("Result for coefficients");

         for (int i = 0; i < tmp_p.exponents.length; i++)
             System.out.print(tmp_p.exponents[i] + " ");
         System.out.println();
         System.out.println("Result for exponents");
         
         System.out.println("TEST HASROOT FUNCTION");
         System.out.println();
         System.out.println(p1.hasRoot(-1));
         System.out.println(p2.hasRoot(0));
         System.out.println();
    }
}