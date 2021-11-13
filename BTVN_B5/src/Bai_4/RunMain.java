package Bai_4;

public class RunMain {
    public static void main(String[] args) {
        // Kiểu Integer
        Sum<Integer> integerNumber = new Sum(); 
        integerNumber.setSoA(28);
        integerNumber.setSoB(11); 
        System.out.println("Tong kieu Integer: " + (integerNumber.getSoA() + integerNumber.getSoB()));

        // Kiểu Long
        Sum<Long> longNumber = new Sum(); 
        longNumber.setSoA(1001010101010L);
        longNumber.setSoB(1010101001L);
        System.out.println("Tong kieu Long: " + (longNumber.getSoA() + longNumber.getSoB()));

        // Kiểu Float
        Sum<Float> floatNumber = new Sum(); 
        floatNumber.setSoA(12.2f);
        floatNumber.setSoB(1.6f);
        System.out.println("Tong kieu Float: " + (floatNumber.getSoA()+floatNumber.getSoB()));

        // Kiểu Double
        Sum<Double> doubleNumber = new Sum(); 
        doubleNumber.setSoA(28.11);
        doubleNumber.setSoB(20.02);
        System.out.println("Tong kieu Double: " + (doubleNumber.getSoA()+doubleNumber.getSoB()));
     }
}
