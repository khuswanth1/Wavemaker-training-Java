public class Main {

    public void meth1(){
        int a =547;
        byte b= 20;
        long l =83910L;
        short s =4200;
        float f= 547.22f;
        double d= 329.33d;
        char c ='a';
        boolean var = false;
        String str = "Hi Am khuswanth";
        System.out.println("Enter the integer:"+a);
        System.out.println("Enter the bytes:"+b);
        System.out.println("Enter the long:"+l);
        System.out.println("Enter the short:"+s);
        System.out.println("Enter the floating point no:"+f);
        System.out.println("Enter the double the no:"+d);
        System.out.println("Enter the char:"+c);
        System.out.println("Enter the boolean:"+var);
        System.out.println("Enter the string"+str);
    }
    public static void main(String[] args) {
        System.out.println("Start");
        Main obj = new Main();
         obj.meth1();
        System.out.println("Ends");
    }}

