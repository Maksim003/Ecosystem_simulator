package com.cenla.interfaces;

import com.cenla.models.Conditions;

public interface Organism {
    String getType();
    Conditions getCondition();
    public boolean eat(Organism organism);
}
