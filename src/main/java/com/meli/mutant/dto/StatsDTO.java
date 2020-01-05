package com.meli.mutant.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsDTO {

    @JsonProperty("count_mutant_dna")
    private Long countMutantDNA;

    @JsonProperty("count_human_dna")
    private Long countHumanDNA;

    private float ratio;

    public Long getCountMutantDNA() {
        return countMutantDNA;
    }

    public void setCountMutantDNA(Long countMutantDNA) {
        this.countMutantDNA = countMutantDNA;
    }

    public Long getCountHumanDNA() {
        return countHumanDNA;
    }

    public void setCountHumanDNA(Long countHumanDNA) {
        this.countHumanDNA = countHumanDNA;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

}
