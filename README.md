# darwin
Darwin Genetic Algorithm System  (and example applications)

# Structure

The darwin library is built as a template for executing genetic algorithms.  Basic usage follows (based on the App.java class in the darwinqueens project):

      Date start = new Date();  // Optional, benchmarking timestamp
    	EightQueensStrategy strategy = new EightQueensStrategy(10);  //Implemented strategy interface
    	DarwinConfiguration<ChessBoard> config = new DarwinConfiguration<>(); //Runtime configuration
    	config.setCullTo(8);             // After scoring candidate solutions, darwin will keep the top 8
    	config.setPopulationSize(800);   // Darwin will generate 800 solutions at a time
    	config.setMutantCount(80);       // Each of the unculled solutions from the prior run will be mutated 80 times
    	config.setCrossoverRuns(0);      // No crossover breeding in this configuration
    	config.setStrategy(strategy);    // Apply the above strategy in this run
    	Processor<ChessBoard> proc = new Processor<ChessBoard>(config);  // Configure the darwin processor
    	ChessBoard solution = proc.run();  // Run the above strategy and return the resulting successful candidate
    	Date end = new Date();  // Optional - benchmarking timestamp
      
    	//  Print out the solutions
    	System.out.println("FINISHED:");
		  System.out.println(solution);
		  System.out.println((double) (end.getTime() - start.getTime()) / 1000 + " seconds elapsed");

An EvolutionStrategy looks like this:

    public interface EvolutionStrategy<T> {
	    public double genFitnessScore(T candidate);  //Generate a fitness score for a candidate, between 1 and 0, where 1 is best.
	    public T mutate(T candidate); // Generate a mutant based on the given candidate
	    public List<T> crossover(T parentA, T parentB); // Generate a list of possible children based on qualities of the two given parent solutions
	    public T generateRandom(); // Generate a random solution 
	    public boolean endConditionIsMet(List<ScoredCandidate<T>> scoredPopulation);  // Test to see whether darwin is done
    }

Review the EightQueensStrategy and StringEvolutionStrategy files in their relevant projects for further examples of how to build a solution with Darwin.



The basic process that Darwin follows when running a solution is:

1)  Generate a random population of candidate solutions using the generateRandom() function of the strategy
2)  Score all candidates using genFitnessScore
3)  Test for end condition using endConditionIsMet
4)  (if not complete) cull the population down to the number configured
5)  Run crossovers, if configured (this generally slows down the process more than is useful)
6)  Mutate new population as per configuration and the mutate function above
7)  goto 2

