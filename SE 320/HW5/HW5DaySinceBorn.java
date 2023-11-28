import java.text.SimpleDateFormat;
import java.util.Date;

public class HW5DaySinceBorn{
    
    private Day birthDay;
    private Day toDay;
    public static void main(String[] args){
       
        HW5DaySinceBorn me = new HW5DaySinceBorn();
        me.birthDay = new Day(2003, 02, 05);
        me.toDay = me.convertToDay(new SimpleDateFormat("MM/dd/yyyy").format(new Date()));
        System.err.println("Number of days since born: " + me.toDay.daysFrom(me.birthDay));

    }

    private Day convertToDay(String day) {
       
        String[] splitString = day.split("/");
        int[] dateArray = new int[splitString.length];

        for (int i = 0; i < splitString.length; i++) {
            dateArray[i] = Integer.parseInt(splitString[i]);
        }
        return new Day(dateArray[2], dateArray[0], dateArray[1]);
    }
}

