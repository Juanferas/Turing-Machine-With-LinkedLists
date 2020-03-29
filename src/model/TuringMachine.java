package model;

public class TuringMachine {
	/**
	 * C0 is a reference to the first Element of the tape.
	 * C1 is a reference to the middle Element of the tape.
	 * C2 is a reference to the last Element of the tape.
	 * length corresponds to the length of the tape.
	 */
	private Element C0;
	private Element C1;
	private Element C2;
	private int length;
	
	public TuringMachine() {
		C0 = null;
		C1 = null;
		C2 = null;
		length = 0;
	}
	
//	if (length%2==0) {
//		if (add izq -> mid NO cambia)
//		if (add derecha -> mid 1 derecha)
//	}
//	else if (length%2!=0) {
//		if (add izq -> mid 1 izq)
//		if (add derecha -> mid NO cambia)
//	}
	
	/**
	 * This method adds an Element to the tape with a given value through a head of the machine.
	 * <b>pre: </b>Three heads, C0 pointing to the start of the tape, C1 pointing to the middle and C2 pointing to the end have already been created.
	 * <b>post: </b> A new Element has been added to the tape.
	 * @param c is an int that indicates the head in which is going to take place the addition.
	 * @param letter is a String that corresponds to the value of the Element to add to the tape.
	 */
	public void addElement(int c, String letter) {
		Element newElement = new Element (letter);
		if (length==0) {
			C0 = newElement;
			C1 = newElement;
			C2 = newElement;
			length++;
		}
		else {
			switch (c) {
				case 0:
					C0.setPrevElement(newElement);
					newElement.setNextElement(C0);
					C0 = newElement;
					if (length==1) {
						C1 = newElement;
						C2.setPrevElement(C1);
					}
					else if (length==2){
						C1 = C0.getNextElement();
						C1.setPrevElement(C0);
						C1.setNextElement(C2);
					}
					if (length%2!=0 && length>2) {
						C1 = C1.getPrevElement();
					}
					length++;
					break;
				case 1:
					if (length==1) {
						newElement.setNextElement(C2);
						C2.setPrevElement(newElement);
						C0 = newElement;
						C1 = newElement;
					}
					else if (length%2==0) {
						Element next = C1.getNextElement();
						if (next!=null) {
							next.setPrevElement(newElement);
						}
						C1.setNextElement(newElement);
						newElement.setPrevElement(C1);
						newElement.setNextElement(next);
						C1 = newElement;
					}
					else {
						Element prev = C1.getPrevElement();
						if (prev!=null) {
							prev.setNextElement(newElement);
						}
						C1.setPrevElement(newElement);
						newElement.setNextElement(C1);
						newElement.setPrevElement(prev);
						C1 = newElement;
					}
					length++;
					break;
				case 2:
					C2.setNextElement(newElement);
					newElement.setPrevElement(C2);
					C2 = newElement;
					if (length%2==0) {
						C1 = C1.getNextElement();
					}
					length++;
					break;
			}
		}
	}
	
	/**
	 * This method reads the value of the Element of a given head.
	 * <b>pre: </b>Three heads, C0 pointing to the start of the tape, C1 pointing to the middle and C2 pointing to the end have already been created.
	 * <b>post: </b> The value of the element has been correctly read.
	 * @param c is an int that indicates the head in which is going to take place the reading.
	 * @return a String with the value of the Element in the chosen head.
	 */
	public String readElement(int c) {
		String letter = "#";
		if (length!=0) {
			switch(c) {
				case 0:
					letter = C0.getValue();
					break;
				case 1:
					letter = C1.getValue();
					break;
				case 2:
					letter = C2.getValue();
					break;
			}
		}
		return letter;
	}
	
	/**
	 * This method deletes an Element of the tape through a head of the machine.
	 * <b>pre: </b>Three heads, C0 pointing to the start of the tape, C1 pointing to the middle and C2 pointing to the end have already been created.
	 * <b>post: </b> The value of the element has been correctly read.
	 * @param c is an int that indicates the head in which is going to take place the elimination.
	 */
	public void deleteElement(int c) {
		if (length!=0) {
			switch (c) {
				case 0:
					if (length==1) {
						C0 = null;
						C1 = null;
						C2 = null;
					}
					else if (length==2) {
						C0 = C2;
						C1 = C2;
						C2.setPrevElement(null);
					}
					else if (length%2==0) {
						C0 = C0.getNextElement();
						C0.setPrevElement(null);
						C1 = C1.getNextElement();
					}
					else {
						C0 = C0.getNextElement();
						C0.setPrevElement(null);
					}
					length--;
					break;
				case 1:
					if (length==1) {
						C0 = null;
						C1 = null;
						C2 = null;
					}
					else if (length==2) {
						C0 = C2;
						C1 = C2;
						C2.setPrevElement(null);
					}
					else if (length==3) {
						C1 = C0;
						C2.setPrevElement(C1);
						C1.setNextElement(C2);
						C1.setPrevElement(null);
					}
					else {
						C1.getPrevElement().setNextElement(C1.getNextElement());
						C1.getNextElement().setPrevElement(C1.getPrevElement());
						if (length%2==0) {
							C1 = C1.getNextElement();
						}
						else {
							C1 = C1.getPrevElement();
						}
					}
					length--;
					break;
				case 2:
					if (length==1) {
						C0 = null;
						C1 = null;
						C2 = null;
					}
					else if (length==3) {
						C2 = C1;
						C2.setNextElement(null);
						C1 = C0;
					}
					else if (length%2!=0) {
						C2 = C2.getPrevElement();
						C2.setNextElement(null);
						C1 = C1.getPrevElement();
					}
					else {
						C2 = C2.getPrevElement();
						C2.setNextElement(null);
					}
					length--;
					break;
			}
		}
	}
	
	/**
	 * @return a String with the information contained in the Turing machine.
	 */
	public String toString(){
		String msj = "";
		Element node = C0;
		if (length==0) {
			msj = "null\n";
		} else {
			while (node!=null) {
				msj += node.getValue();
				node = node.getNextElement();
			}
			msj += "\nC0: "+C0.getValue()+" C1: "+C1.getValue()+" C2: "+C2.getValue();
		}
		return msj;
	}
}
