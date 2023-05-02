package edu.northeastern.cs5500.starterbot.service;

import java.util.Map;
import lombok.Data;

@Data
public class PokemonShowdownEntry {
    long num;
    String name;
    String baseForme;
    String baseSpecies;
    String forme;
    String[] types;
    String gender;
    Map<String, Double> genderRatio;
    Map<String, Integer> baseStats;
    Integer maxHP;
    Map<String, String> abilities;
    Double heightm;
    Double weightkg;
    String color;
    String prevo;
    String evoType;
    String evoMove;
    String evoItem;
    Integer evoLevel;
    String evoCondition;
    String[] evos;
    String[] tags;
    String[] eggGroups;
    String requiredItem;
    String[] requiredItems;
    String[] otherFormes;
    String[] cosmeticFormes;
    String[] formeOrder;
    String canGigantamax;
    String changesFrom;
    Boolean canHatch;
    String requiredAbility;
    String requiredMove;
    String[] battleOnly;
    Boolean cannotDynamax;
    Integer gen;
    String tier;
    String isNonstandard;
}
