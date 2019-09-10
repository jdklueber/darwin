package com.jdklueber.darwin_string;

import com.jdklueber.darwin.DarwinConfiguration;
import com.jdklueber.darwin.EvolutionStrategy;
import com.jdklueber.darwin.Processor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	DarwinConfiguration<String> config = new DarwinConfiguration<String>();
    	
    	config.setMutantCount(200);
    	config.setPopulationSize(10000);
    	config.setCullTo(10);
    	config.setCrossoverRuns(2);
    	
    	EvolutionStrategy<String> strategy = new StringEvolutionStrategy("Welcome to the darwin genetic algorithm system", .1);
    	config.setStrategy(strategy);
    	
    	Processor<String> proc = new Processor<String>(config);
    	
    	String winner = proc.run();

    	System.out.println("++++++++++++++++++++++++++++++++++");
    	System.out.println("FINAL");
    	System.out.println("++++++++++++++++++++++++++++++++++");
    	System.out.println(winner);
    	System.out.println("++++++++++++++++++++++++++++++++++");
    	
    }
}
