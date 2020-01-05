package com.meli.mutant.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mutant")
public class Mutant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long mutantId;

    @Column(name = "dna", length = 100, nullable = false)
    private String dna;

    @Column(name = "is_mutant", nullable = false)
    private Boolean isMutant;

    public Long getMutantId() {
        return mutantId;
    }

    public void setMutantId(Long mutantId) {
        this.mutantId = mutantId;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public Boolean getIsMutant() {
        return isMutant;
    }

    public void setIsMutant(Boolean isMutant) {
        this.isMutant = isMutant;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mutantId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mutant other = (Mutant) obj;
        return Objects.equals(mutantId, other.getMutantId());
    }

}
