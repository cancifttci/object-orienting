package application;
import java.time.LocalDate;

//Day Enum Type
public enum Day {
	//Day Enums
	MONDAY(true,1,"Monday"),TUESDAY(true,2,"Tuesday"),WEDNESDAY(true,3,"Wednesday"),THURSDAY(true,4,"Thursday"),FRIDAY(true,5,"Friday"),SATURDAY(true,6,"Saturday"),SUNDAY(false,7,"Sunday");

	//Private Fields
	private String dayName;
	private boolean isWorkday;
	private int dayIndex;

	//Enum constructor
	Day(boolean isWorkday,int dayIndex,String dayName){
		this.isWorkday = isWorkday;
		this.dayIndex = dayIndex;
		this.dayName = dayName;
	}

	//Check if enum is a workday
	public boolean isWorkday() {
		return isWorkday;
	}

	//Get day index of enum 1 to 7 
	public int getDayIndex() {
		return dayIndex;
	}

	//Find the current day and get the index of this day
	public static int getCurrentDayIndex() {	
		LocalDate date = LocalDate.now();
		int day = date.getDayOfWeek().getValue();
		return day;
	}

	//Get Day enum by ID
	public static Day getDayByID(int dayIndex) {	
		if (dayIndex>0&&dayIndex<8) {
			return Day.values()[dayIndex-1];
		}else {
			return null;
		}
	}

	//Print enum name
	public String toString() {
		return this.dayName;
	}


}
