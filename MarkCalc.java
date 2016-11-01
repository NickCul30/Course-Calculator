//Mark Calculator v1.00
//Nicholas Culmone

import java.util.*;
import java.io.*;
import java.lang.*;

public class MarkCalc{
	private static ArrayList<Course> courses = new ArrayList<Course>();
	private static PrintWriter saveWrite;


	public static void main(String[]args) throws IOException{
		Scanner kb = new Scanner(System.in);
		Scanner saveFile = new Scanner(new BufferedReader(new FileReader("saveFile.txt")));

		if(saveFile.hasNextLine()){
			int numC = Integer.parseInt(saveFile.nextLine());

			for(int i=0;i<numC;i++){
				String n = saveFile.nextLine();
				int numM = Integer.parseInt(saveFile.nextLine());
				Course tmp = new Course(n);

				for(int j=0;j<numM;j++){
					String[]stats = saveFile.nextLine().split(",");
					String nTmp = stats[0];
					double mTmp = Double.parseDouble(stats[1]);
					double wTmp = Double.parseDouble(stats[2]);
					tmp.addMark(nTmp,mTmp,wTmp);
				}
				courses.add(tmp);
			}
		}
		saveFile.close();

		int user = -1;
		int screen = 0;
		Course cur = null;

		while(user != 0){
			if(screen == 0){
				space();
				System.out.println("Mark Calculator v1.00\nNicholas Culmone 2016\n\n0. Exit\n1. New Course");
				int cnt = 1;

				for(Course c : courses){
					cnt ++;
					System.out.println(cnt + ". " + c.getName() + " - " + round(c.getWeightedAvg(),2) + "%");
				}
				System.out.println("");

				user = kb.nextInt();
				kb.nextLine();
				if(user != 1) screen = 1;
				else screen = 4;
			}
			else if(screen == 1){
				space();
				cur = courses.get(user-2);

				System.out.println(cur.getName() + "\n\nWeighted Average: " + round(cur.getWeightedAvg(),2) + "%\nPercentage of Mark Accounted for: " + cur.completed() + "%\nAssignments Handed in: " + cur.getMarks().size() + "\n");
				for(Mark m : cur.getMarks()){
					System.out.println("   " + m.getName() + "   Mark: " + round(m.getScore(),2) + "%    Weight: " + m.getWeight() + "%");
				}
				System.out.println("\n0. Exit\n1. Back\n2. Enter Mark\n3. Remove Mark\n");

				user = kb.nextInt();
				kb.nextLine();

				if(user == 1) screen = 0;
				else screen = user;
			}
			else if(screen == 2){
				space();
				System.out.print("Enter Name of Evaluation: ");
				String tmpN = kb.nextLine();
				System.out.print("Enter Mark (%): ");
				double tmpM = Double.parseDouble(kb.nextLine());
				System.out.print("Enter Weight (%): ");
				double tmpW = Double.parseDouble(kb.nextLine());

				cur.getMarks().add(new Mark(tmpN,tmpM,tmpW));
				screen = 0;
			}
			else if(screen == 3){
				space();
				int cnt = 0;

				System.out.println("0. Back");
				for(Mark m : cur.getMarks()){
					cnt ++;
					System.out.println(cnt + ". " + m.getName() + "   Mark: " + m.getScore() + "%    Weight: " + m.getWeight() + "%");
				}
				System.out.print("\nEnter mark you wish to remove: ");
				int tmp = kb.nextInt();
				kb.nextLine();

				if(tmp != 0){
					cur.getMarks().remove(tmp-1);
					if(cur.getMarks().size() == 0) courses.remove(cur);
				}
				screen = 0;
			}
			else if(screen == 4){
				space();
				System.out.print("Enter name of Course: ");
				String tmp = kb.nextLine();
				courses.add(new Course(tmp));
				screen = 0;
			}


		}



		saveWrite = new PrintWriter("saveFile.txt", "UTF-8");
		saveWrite.println("" + courses.size());

		for(Course c : courses){
			saveWrite.println(c.getName());
			saveWrite.println("" + c.getMarks().size());

			for(Mark m : c.getMarks()){
				saveWrite.println(m.getName() + "," + m.getScore() + "," + m.getWeight());
			}
		}
		saveWrite.close();
	}

	public static void space(){
		for(int i=0;i<60;i++){
			System.out.println("");
		}
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}