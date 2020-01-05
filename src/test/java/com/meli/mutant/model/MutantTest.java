package com.meli.mutant.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class MutantTest {

    @Test
    public void testHashCode() {
        Mutant x = new Mutant();
        x.setMutantId(1L);
        Mutant y = new Mutant();
        y.setMutantId(1L);
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    public void testEquals() {
        Mutant x = new Mutant();
        x.setMutantId(1L);
        Mutant y = new Mutant();
        y.setMutantId(1L);
        assertEquals(x.equals(y), y.equals(x));
    }

    @Test
    public void testEqualsNull() {
        Mutant x = new Mutant();
        x.setMutantId(1L);
        Mutant y = null;
        assertFalse(x.equals(y));
    }

    @Test
    public void testEqualsClass() {
        Mutant x = new Mutant();
        x.setMutantId(1L);
        Object y = new Object();
        assertFalse(x.equals(y));
    }

    @Test
    public void testEqualsObject() {
        Mutant x = new Mutant();
        x.setMutantId(1L);
        Mutant y = x;
        assertEquals(x.equals(y), y.equals(x));
    }

}
