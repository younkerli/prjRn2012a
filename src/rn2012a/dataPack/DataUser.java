package rn2012a.dataPack;

import java.util.Arrays;

public class DataUser extends DataBox {

	private String[] userphone;
	private int count;

	public String[] getUserphone() {
		return userphone;
	}

	public void setUserphone(String[] userphone) {
		this.userphone = userphone;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int getLen() {
		return 11 * 5 + 1;
	}

	@Override
	public String toString() {
		return "DataUser [userphone=" + Arrays.toString(userphone) + ", count=" + count + "]";
	}

}
