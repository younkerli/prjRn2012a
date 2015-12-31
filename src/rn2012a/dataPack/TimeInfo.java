package rn2012a.dataPack;

public class TimeInfo extends DataBox{

	private int week;
	private int year;
	private int month;
	private int date;
	private int hour;
	private int minute;
	private int second;
	private int ampm;

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public int getAmpm() {
		return ampm;
	}

	public void setAmpm(int ampm) {
		this.ampm = ampm;
	}

	public int getLen() {
		return 8;
	}

	@Override
	public String toString() {
		return "TimeInfo [week=" + week + ", year=" + year + ", month=" + month + ", date=" + date + ", hour=" + hour
				+ ", minute=" + minute + ", second=" + second + ", ampm=" + ampm + "]";
	}

}
