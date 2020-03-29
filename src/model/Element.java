package model;

public class Element {
	/**
	 * value corresponds to a String of length 1 which is the value of the Element.
	 * nextElement is a reference to the next Element of itself.
	 * prevElement is a reference to the previous Element of itself.
	 */
	private String value;
	private Element nextElement;
	private Element prevElement;
	
	public Element(String value) {
		this.value = value;
		nextElement = null;
		prevElement = null;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * @return the nextElement
	 */
	public Element getNextElement() {
		return nextElement;
	}
	
	/**
	 * @param nextElement the nextElement to set
	 */
	public void setNextElement(Element nextElement) {
		this.nextElement = nextElement;
	}

	/**
	 * @return the prevElement
	 */
	public Element getPrevElement() {
		return prevElement;
	}

	/**
	 * @param prevElement the prevElement to set
	 */
	public void setPrevElement(Element prevElement) {
		this.prevElement = prevElement;
	}
}
