package model;

import java.util.Set;
import java.util.TreeSet;

/**
 * Holds all the Technology information for a Civilization
 *
 * @author Ryan Voor
 * @version 2.0
 */
public class Technology {

    private int techPoints;
    private Set<Skill> skills;
    private int techPointsToAdd;

    /**
     * Constructs a new Technology object
     * initializes techPoints to 0 and
     */
    public Technology() {
        techPoints = 0;
        techPointsToAdd = 0;
        skills = new TreeSet<>((Skill a, Skill b) ->
                a.toString().compareTo(b.toString()));
    }

    /**
     * Increments techPoints and adds a Skill to skills
     */
    public void gainATech() {

        techPointsToAdd++;
        //there are only 20 possible skills
        while (skills.size() < 20 && (techPointsToAdd >= 40)) {
            boolean notAddedYet = true;
            while (notAddedYet && !skills.add(Skill.getRandomSkill())) {
                if (skills.add(Skill.getRandomSkill())) {
                    notAddedYet = false;
                }
            } //Attempt to add a Skill until a unique Skill is added
            techPointsToAdd -= 40;
        }
        techPoints++;
    }

    /**
     * Getter for the techPoints of this Civilization
     * @return int the techPoints of this Civilization
     */
    public int getTechPoints() {
        return techPoints;
    }

    /**
     * Getter for the skills of this Civilization
     * @return Set of the skills of this Civilization
     */
    public Set<Skill> getSkills() {
        return skills;
    }


    /**
     * Returns whether this Civilization has achieved a Technology win
     * @return boolean whether this Civilization has achieved 20 techPoints
     */
    public boolean hasTechnologyWin() {
        return techPoints >= 800;
    }

    /**
     * @return an int representing the amount of understanding.
     */
    public int getUnderstanding() {
        return getTechPoints();
    }

    /**
     * @return an int representing the amount of build experience.
     */
    public int getBuildExperience() {
        return getTechPoints();
    }

    /**
     * Adds a technical skill.
     */
    public void increaseBuildExperience() {
        for (int i = 0; i < 25; i++) {
            gainATech();
        }
    }

    /**
     * Adds a technical skill.
     *
     * @param i HAH
     */
    public void increaseBuildExperience(int i) {
        for (int j = 0; j < i; j++) {
            gainATech();
        }
    }

    /**
     * @return a boolean representing whether or not the player has built a
     * nifty tourist attraction.
     */
    public boolean builtWonderOfTheWorld() {
        return techPoints >= 800;
    }

    /**
     * Increases understanding by a specified amount.
     *
     * @param increase
     */
    public void increaseUnderstanding(int i) {
        for (int j = 0; j < i; j++) {
            gainATech();
        }
    }

    /**
     * Adds a technical skill.
     */
    public void philosophize() {
        for (int i = 0; i < 25; i++) {
            gainATech();
        }
    }

    /**
     * Adds a technical skill.
     */
    public void improveWriting() {
        for (int i = 0; i < 10; i++) {
            gainATech();
        }
    }

}
