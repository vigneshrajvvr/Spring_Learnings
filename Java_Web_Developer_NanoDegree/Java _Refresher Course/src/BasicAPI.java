import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BasicAPI {

    public static void main(String[] args) {

        /* Math library functions - java.lang package */
        System.out.println("Math library");
        System.out.println(Math.ceil(5.3));
        System.out.println(Math.floor(5.6));
        System.out.println(Math.max(5,6));
        System.out.println(Math.min(5,6));
        System.out.println(Math.abs(-10));
        System.out.println(Math.pow(2,3));
        System.out.println(Math.sqrt(9));
        System.out.println(Math.random());
        System.out.println(Math.rint(5.4));
        System.out.println(Math.round(1.6));


        // DATE API
        System.out.println("DATE API");
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate + " " + localDate.getYear());
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        // Character wrapper class
        Character ch = 'a';
        System.out.println(ch + " " + Character.toString('a') + ( 'A' + 32));
    }

}
