package ui;
import java.util.*;
import model.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Scanner sc = new Scanner(System.in);
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/in_turing.txt"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("data/output.txt")));
			while (true) {
				TuringMachine tm = new TuringMachine();
				String input = br.readLine();
				if (input==null) {
					break;
				}
				for (int i=0; i<input.length(); ) {
					int c = Integer.valueOf(String.valueOf(input.charAt(i)));
					int op = Integer.valueOf(String.valueOf(input.charAt(i+1)));
					if (op==0) {
						bw.write(tm.readElement(c)+"\n");
						i += 2;
					}
					else if (op==1) {
						String letter = String.valueOf(input.charAt(i+2));
						tm.addElement(c, letter);
						i += 3;
					}
					else if (op==2) {
						tm.deleteElement(c);
						i += 2;
					}
				}
			}
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Operation time: "+(System.currentTimeMillis()-start));
	}

}
