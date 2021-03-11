package org.example.domain.entity;

import java.util.HashSet;
import java.util.Set;

public class BodyParameters {

    private int id;
    private float weight;
    private float neckGirth;
    private float chestGirth;
    private float underBus;
    private float waist;
    private float abdominalGirth;
    private float hipGirth;
    private float thighGirth;
    private float girthUnderTheKnee;
    private float calfGirth;
    private float ankleGirth;
    private float shoulderGirth;
    private float forearmGirth;
    private float wristGirth;

    private Set<DiaryUser> diaryUserSet = new HashSet<>();


    public BodyParameters(float weight, float neckGirth, float chestGirth, float underBus, float waist, float abdominalGirth, float hipGirth, float thighGirth, float girthUnderTheKnee, float calfGirth, float ankleGirth, float shoulderGirth, float forearmGirth, float wristGirth) {
        this.weight = weight;
        this.neckGirth = neckGirth;
        this.chestGirth = chestGirth;
        this.underBus = underBus;
        this.waist = waist;
        this.abdominalGirth = abdominalGirth;
        this.hipGirth = hipGirth;
        this.thighGirth = thighGirth;
        this.girthUnderTheKnee = girthUnderTheKnee;
        this.calfGirth = calfGirth;
        this.ankleGirth = ankleGirth;
        this.shoulderGirth = shoulderGirth;
        this.forearmGirth = forearmGirth;
        this.wristGirth = wristGirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getNeckGirth() {
        return neckGirth;
    }

    public void setNeckGirth(float neckGirth) {
        this.neckGirth = neckGirth;
    }

    public float getChestGirth() {
        return chestGirth;
    }

    public void setChestGirth(float chestGirth) {
        this.chestGirth = chestGirth;
    }

    public float getUnderBus() {
        return underBus;
    }

    public void setUnderBus(float underBus) {
        this.underBus = underBus;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getAbdominalGirth() {
        return abdominalGirth;
    }

    public void setAbdominalGirth(float abdominalGirth) {
        this.abdominalGirth = abdominalGirth;
    }

    public float getHipGirth() {
        return hipGirth;
    }

    public void setHipGirth(float hipGirth) {
        this.hipGirth = hipGirth;
    }

    public float getThighGirth() {
        return thighGirth;
    }

    public void setThighGirth(float thighGirth) {
        this.thighGirth = thighGirth;
    }

    public float getGirthUnderTheKnee() {
        return girthUnderTheKnee;
    }

    public void setGirthUnderTheKnee(float girthUnderTheKnee) {
        this.girthUnderTheKnee = girthUnderTheKnee;
    }

    public float getCalfGirth() {
        return calfGirth;
    }

    public void setCalfGirth(float calfGirth) {
        this.calfGirth = calfGirth;
    }

    public float getAnkleGirth() {
        return ankleGirth;
    }

    public void setAnkleGirth(float ankleGirth) {
        this.ankleGirth = ankleGirth;
    }

    public float getShoulderGirth() {
        return shoulderGirth;
    }

    public void setShoulderGirth(float shoulderGirth) {
        this.shoulderGirth = shoulderGirth;
    }

    public float getForearmGirth() {
        return forearmGirth;
    }

    public void setForearmGirth(float forearmGirth) {
        this.forearmGirth = forearmGirth;
    }

    public float getWristGirth() {
        return wristGirth;
    }

    public void setWristGirth(float wristGirth) {
        this.wristGirth = wristGirth;
    }

    public Set<DiaryUser> getUserSet() {
        return diaryUserSet;
    }

    public void setUserSet(Set<DiaryUser> diaryUserSet) {
        this.diaryUserSet = diaryUserSet;
    }

    public void addUserSet(DiaryUser diaryUser){
        diaryUserSet.add(diaryUser);
    }

    @Override
    public String toString() {
        return "BodyParameters{" +
                "id=" + id +
                ", weight=" + weight +
                ", neckGirth=" + neckGirth +
                ", chestGirth=" + chestGirth +
                ", underBus=" + underBus +
                ", waist=" + waist +
                ", abdominalGirth=" + abdominalGirth +
                ", hipGirth=" + hipGirth +
                ", thighGirth=" + thighGirth +
                ", girthUnderTheKnee=" + girthUnderTheKnee +
                ", calfGirth=" + calfGirth +
                ", ankleGirth=" + ankleGirth +
                ", shoulderGirth=" + shoulderGirth +
                ", forearmGirth=" + forearmGirth +
                ", wristGirth=" + wristGirth +
                '}';
    }
}
