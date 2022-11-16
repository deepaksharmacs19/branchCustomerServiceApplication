package com.branch.app;
import java.io.*;
import java.util.*;
public class ReadCSV {
public static Scanner scan;
	public ReadCSV()
{
try {
	scan=new Scanner(new File("GeneralistRails_Project_MessageData.csv"));
		
}catch(Exception e)
{
	System.out.println(e.getMessage());
	}
}
	
	public String getLine()
	{
		String str="";
		if(scan.hasNext()) str=scan.nextLine();
		return str;		
	}
}
