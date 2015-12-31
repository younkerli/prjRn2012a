package rn2012a.dataPack;

public abstract class DataBox {

	public abstract int getLen();
	
	public abstract String toString();
	
	private boolean isEmpty = true;

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
}
