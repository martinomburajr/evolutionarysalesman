import java.util.*;

class Evolution{
	/**
	 * The method used to generate a mutant of a chromosome
	 * @param original The chromosome to mutate.
	 * @param cityList list of cities, needed to instantiate the new Chromosome.
	 * @return Mutated chromosome.
	 */
	public static Chromosome Mutate(Chromosome original, City [] cityList){
      int [] cityIndexes = null;
      City [] newCities = new City[cityIndexes.length];
      
      for (int i = 0; i<cityIndexes.length; ++i){
         newCities[i] = cityList[cityIndexes[i]];
      }
      
      return new Chromosome(newCities);
   }
	
	/**
	 * Breed two chromosomes to create a offspring
	 * @param parent1 First parent.
	 * @param parent2 Second parent.
	 * @param cityList list of cities, needed to instantiate the new Chromosome.
	 * @return Chromosome resuling from breeding parent.
	 */
	public static Chromosome Breed(Chromosome parent1, Chromosome parent2, City [] cityList){
//	      int [] cityIndexes = parent1.getCities();
//	      int [] cityIndexes2 = parent2.getCities();
//
//	      City [] newCities = new City[cityIndexes.length];
//
//	      for (int i = 0; i<cityIndexes.length; ++i){
//	         newCities[i] = cityList[cityIndexes[i]];
//	      }
	      
//	      return new Chromosome(newCities);
		return  null;
	   }

	/**
	 * Evolve given population to produce the next generation.
	 * @param population The population to evolve.
	 * @param cityList List of ciies, needed for the Chromosome constructor calls you will be doing when mutating and breeding Chromosome instances
	 * @return The new generation of individuals.
	 */
   public static Chromosome [] Evolve(Chromosome [] population, City [] cityList){
      Chromosome [] newPopulation = new Chromosome [population.length];
      for (int i = 0; i<population.length-1; ++i){
         newPopulation[i] = Breed(population[i], population[i+1], cityList);
      }
      newPopulation[population.length -1] = Breed(population[population.length-1], population[0], cityList);
      
      return newPopulation;
   }

	/**
	 * Evolve given population to produce the next generation.
	 * @param population The population to evolve.
	 * @param cityList List of ciies, needed for the Chromosome constructor calls you will be doing when mutating and breeding Chromosome instances
	 * @return The new generation of individuals.
	 */
	public static Chromosome [] Evolve2(Chromosome [] population, City [] cityList, double currentBest){
//		if(population[0].cityList[0] % 2 == 0) {
//			return translocation(population, cityList);
//		}else{
//			return inversionMutation(population,cityList);
//		}

//		return inverOverCrossover(population, cityList);

//		return Crossover.evolveGreedy(population,cityList);

		return GA.evolveChromosome(population, cityList, currentBest);
		//return Crossover.micro(population, cityList);
		//return pointExchange(population, cityList,4);
	}




//	/**
//	 * Evolve given population to produce the next generation.
//	 * @param population The population to evolve.
//	 * @param cityList List of ciies, needed for the Chromosome constructor calls you will be doing when mutating and breeding Chromosome instances
//	 * @return The new generation of individuals.
//	 */
//	public static Chromosome [] Evolve(Chromosome [] population, City [] cityList){
//		Chromosome [] newPopulation = new Chromosome [population.length];
//		for (int i = 0; i<population.length; ++i){
//			newPopulation[i] = Mutate(population[i], cityList);
//		}
//
//		return newPopulation;
//	}


	public static void crossover(Chromosome[] chromosomes) {
		Random random = new Random();
		int randInt1 = random.nextInt(chromosomes.length);
		int randInt2 = random.nextInt(chromosomes.length);

		Chromosome testChrom1 = chromosomes[randInt1];
		Chromosome testChrom2 = chromosomes[randInt2];

		if(testChrom1.getCost() > testChrom2.getCost()) {
			//if testChrom1 fitness is less than that off testChrom2
			int startIndex = random.nextInt(24);
			int endIndex = random.nextInt(24);

			int[] testChrom1GeneSegment = getGeneSegment(testChrom1, startIndex, startIndex+endIndex);
			int[] testChrom2GeneSegment = getGeneSegment(testChrom2, startIndex, startIndex+endIndex);

			satisfiesInverOverCrossover(testChrom1, testChrom1GeneSegment, testChrom2, testChrom2GeneSegment);
		}
	}


	public static boolean satisfiesInverOverCrossover(Chromosome testChrom1, int[] testChrom1GeneSegment, Chromosome testChrom2, int[] testChrom2GeneSegment) {
		if(testChrom1.cityList[0] == testChrom2.cityList[0]) {
			return true;
		}
		return false;
	}

	public static void performInverOverCrossover(Chromosome testChrom1, int[] testChrom1GeneSegment, Chromosome testChrom2, int[] testChrom2GeneSegment) {

	}
	/**
	 * Returns a gene segment
	 * @param chromosome
	 * @param startIndexIncluding
	 * @param stopIndexExcluding
	 * @return
	 */
	public static int[] getGeneSegment(Chromosome chromosome, int startIndexIncluding, int stopIndexExcluding) {
		int range = stopIndexExcluding - startIndexIncluding;
		int [] arr = new int[range];

		for (int i = 0; i < range; i++) {
			arr[i] = chromosome.cityList[startIndexIncluding+i];
		}
		return arr;
	}
}