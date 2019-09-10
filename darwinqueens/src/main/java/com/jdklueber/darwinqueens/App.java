package com.jdklueber.darwinqueens;

import java.util.Date;

import com.jdklueber.darwin.DarwinConfiguration;
import com.jdklueber.darwin.Processor;

public class App 
{
    public static void main( String[] args )
    {
    	Date start = new Date();
    	EightQueensStrategy strategy = new EightQueensStrategy(10);
    	DarwinConfiguration<ChessBoard> config = new DarwinConfiguration<>();
    	config.setCullTo(8);
    	config.setPopulationSize(800);
    	config.setMutantCount(80);
    	config.setCrossoverRuns(0);
    	config.setStrategy(strategy);
    	Processor<ChessBoard> proc = new Processor<ChessBoard>(config);
    	ChessBoard solution = proc.run();
    	Date end = new Date();
    	
    	System.out.println("FINISHED:");
		System.out.println(solution);
		System.out.println((double) (end.getTime() - start.getTime()) / 1000 + " seconds elapsed");

    }
}
