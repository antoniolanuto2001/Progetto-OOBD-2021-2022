package Model;

import java.util.*;
public class Data {
	
	private int anno;
	private int mese;
	private int giorno;
	
	public Data(int g,int m,int a)
	{
		setAnno(a);
		setMese(m);
		setGiorno(g);
	}

	public int getGiorno() {
		return giorno;
	}

	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	/**
	 * @return the mese
	 */
	public int getMese() {
		return mese;
	}

	/**
	 * @param mese the mese to set
	 */
	public void setMese(int mese) {
		this.mese = mese;
	}

	/**
	 * @return the anno
	 */
	public int getAnno() {
		return anno;
	}

	/**
	 * @param anno the anno to set
	 */
	public void setAnno(int anno) {
		this.anno = anno;
	}
	
	
}
