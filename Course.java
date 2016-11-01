//Course.java
//Nicholas Culmone

import java.util.*;
import java.io.*;
import java.lang.*;

public class Course{
	private String name;
	private ArrayList<Mark> marks;

	public Course(String n){
		name = n;
		marks = new ArrayList<Mark>();
	}

	public ArrayList<Mark> getMarks(){
		return marks;
	}
	public String getName(){
		return name;
	}
	public double getWeightedAvg(){ //weighted avg
		double total = 0, outOf = 0;

		for(Mark m : marks){
			total += m.getWeight() * (m.getScore() / 100);
			outOf += m.getWeight();
		}
		if(outOf == 0) return 0;
		else return (total / outOf) * 100;
	}

	public double completed(){ //percntage completed of the course
		double total = 0;

		for(Mark m : marks){
			total += m.getWeight();
		}
		return total;
	}

	public void addMark(String n, double s, double w){
		marks.add(new Mark(n,s,w));
	}
}