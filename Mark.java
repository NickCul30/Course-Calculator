//Mark.java
//Nicholas Culmone

import java.util.*;
import java.io.*;
import java.lang.*;

public class Mark{
	String name;
	double score;
	double weight;

	public Mark(String n, double s, double w){
		name = n;
		score = s;
		weight = w;
	}

	public String getName(){
		return name;
	}
	public double getScore(){
		return score;
	}
	public double getWeight(){
		return weight;
	}
}