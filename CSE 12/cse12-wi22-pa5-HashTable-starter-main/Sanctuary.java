/**
 * TODO: Add your file header
 * Name: Merrick Qiu
 * ID: A16839273
 * Email: myqiu@ucsd.edu
 * Sources used: None
 * 
 * A class that stores information about the animals in a sanctuary.
 * It keeps track of the maximum number of animals and species allowed.
 */
import java.util.HashMap;
import java.util.Set;

/**
 * A class that stores information about the animals in a sancutuary.
 * It using a HashMap to keep track of how many animals belong to each species.
 */
public class Sanctuary {
    
    HashMap<String, Integer> sanctuary;
    int maxAnimals;
    int maxSpecies;

    /**
     * Sanctuary Constructor
     * @param maxAnimals The maximum number of animals per species allowed
     * @param maxSpecies The maximum number of species allowed
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if (maxAnimals < 0 || maxSpecies < 0) {
            throw new IllegalArgumentException();
        }
        sanctuary = new HashMap<>();
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    /**
     * Gets the number of animals for the species
     * @param species The species to query
     * @return The number of animals for the species
     */
    public int getNum(String species) {
        if (species == null) {
            throw new IllegalArgumentException();
        }
        if (!sanctuary.containsKey(species)) {
            return 0;
        }
        return sanctuary.get(species);
    }
    
    /**
     * Get the total number of animals at the zoo
     * @return The total number of animals at the zoo
     */
    public int getTotalAnimals() {
        int totalAnimals = 0;
        for (int number : sanctuary.values()) {
            totalAnimals += number;
        }
        return totalAnimals;
    }
    
    /**
     * The number of unique species at the zoo
     * @return The number of species at the zoo
     */
    public int getTotalSpecies() {
        return sanctuary.size();
    }
    /**
     * Adds new animals to the zoo up to the allowed capacity
     * @param species The species to add
     * @param num The number of animals to add
     * @return The number of animals that couldn't be rescued
     */
    public int rescue(String species, int num) {
        if (num <= 0 || species == null) {
            throw new IllegalArgumentException();
        }

        // Deals with new species
        if (!sanctuary.containsKey(species)) {
            if (maxSpecies == getTotalSpecies()) {
                return num;
            }
            sanctuary.put(species, 0);
        }

        int newCount = sanctuary.get(species) + num;
        // Deals with animal overflow
        if (newCount > maxAnimals) {
            sanctuary.replace(species, maxAnimals);
            return newCount - maxAnimals;
        }

        // No overflow
        sanctuary.replace(species, newCount);
        return 0;
    }

    /**
     * Releases animals from the sanctuary
     * @param species The species to release
     * @param num The number of animals to release
     */
    public void release(String species, int num) {
        if (species == null || num <= 0 || num > sanctuary.get(species)) {
            throw new IllegalArgumentException();
        }

        int newCount = sanctuary.get(species) - num;
        if (newCount == 0) {
            sanctuary.remove(species);
        }
        else {
            sanctuary.replace(species, newCount);
        }
        
    }
}
