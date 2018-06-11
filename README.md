# EC-TSP-UCT
Starting code for the Evolutionary Computing TSP Assignment (UCT)

Original code by Geoff Nitschke, with anti-cheating modifications by Steven Tupper.

## Instructions
See the assignment instructions on your Vula tab for the full set of instructions. The following deals with the code of the assignment. You must do all your coding within the **Evolution.java** file. The code counts how many times you call the **Chromosome.calculateCost()** method. You may call the method up to **100\*(numRuns+1)\*populationSize\*2** times during the entire experiment. This means that you can only call **Chromosome.calculateCost()** up to **populationSize\*2** times per round. You do not have to do a equal amount of **Chromosome.calculateCost()** calls per Chromosome instance.

##Solution
The solution runs quite well using some greedy crossover and exploitative mutation to converge rapidly on local maxima. 
After a successfull convergence, the mutation respreads the population across the solution space to find another maxima. 
Will often find a very very good solution by the 30th generation.
Lowest value recorded of 50 cities, 100 generations, 100 runs was 2671. Best Average 2970.

All code in the following files was done by *Martin Ombura Jr*
<ul>
  <li>SurvivorSelection.java</li>
  <li>ParentSelection.java</li>
  <li>Utility</li>
  <li>Evolution</li>
  <li>Crossover</li>
  <li>Mutation</li>
<ul>
~The Chromosome class was partly modified by Martin Ombura Jr.~

All other classes belongs to Geoff Nitschke and University of Cape Town
