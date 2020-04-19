package com.zeitgeist.employee.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 10/2/2017.
 */

public class Measurements {

    @SerializedName("m_id")
    @Expose
    private String measurementId;
    @SerializedName("stance_id")
    @Expose
    private String stanceId;
    @SerializedName("stance")
    @Expose
    private String stance;
    @SerializedName("stance_img")
    @Expose
    private String stanceImg;
    @SerializedName("shoulder_id")
    @Expose
    private String shoulderId;
    @SerializedName("shoulder")
    @Expose
    private String shoulder;
    @SerializedName("shoulder_img")
    @Expose
    private String shoulderImg;
    @SerializedName("chest_id")
    @Expose
    private String chestId;
    @SerializedName("chest")
    @Expose
    private String chest;
    @SerializedName("chest_img")
    @Expose
    private String chestImg;
    @SerializedName("stomach_id")
    @Expose
    private String stomachId;
    @SerializedName("stomach")
    @Expose
    private String stomach;
    @SerializedName("stomach_img")
    @Expose
    private String stomachImg;
    @SerializedName("hip_id")
    @Expose
    private String hipId;
    @SerializedName("hip")
    @Expose
    private String hip;
    @SerializedName("hip_img")
    @Expose
    private String hipImg;
    @SerializedName("neck")
    @Expose
    private String neck;
    @SerializedName("full_chest")
    @Expose
    private String fullChest;
    @SerializedName("shoulder_width")
    @Expose
    private String shoulderWidth;
    @SerializedName("right_sleeve")
    @Expose
    private String rightSleeve;
    @SerializedName("left_sleeve")
    @Expose
    private String leftSleeve;
    @SerializedName("bicep")
    @Expose
    private String bicep;
    @SerializedName("wrist")
    @Expose
    private String wrist;
    @SerializedName("waist_stomach")
    @Expose
    private String waistStomach;
    @SerializedName("hip_m")
    @Expose
    private String hipMeasurement;
    @SerializedName("front_jacket_length")
    @Expose
    private String frontJacketLength;
    @SerializedName("front_chest_width")
    @Expose
    private String frontChestWidth;
    @SerializedName("back_width")
    @Expose
    private String backWidth;
    @SerializedName("half_shoulder_width_left")
    @Expose
    private String halfShoulderWidthLeft;
    @SerializedName("half_shoulder_width_right")
    @Expose
    private String halfShoulderWidthRight;
    @SerializedName("full_back_length")
    @Expose
    private String fullBackLength;
    @SerializedName("half_back_length")
    @Expose
    private String halfBackLength;
    @SerializedName("trouser_waist")
    @Expose
    private String trouserWaist;
    @SerializedName("trouser_outseam")
    @Expose
    private String trouserOutseam;
    @SerializedName("trouser_inseam")
    @Expose
    private String trouserInseam;
    @SerializedName("crotch")
    @Expose
    private String crotch;
    @SerializedName("thigh")
    @Expose
    private String thigh;
    @SerializedName("knee")
    @Expose
    private String knee;
    @SerializedName("right_full_sleeve")
    @Expose
    private String rightFullSleeve;
    @SerializedName("left_full_sleeve")
    @Expose
    private String leftFullSleeve;

    public Measurements() {
    }

    public Measurements(String measurementId, String stanceId, String stance, String stanceImg, String shoulderId, String shoulder, String shoulderImg, String chestId, String chest, String chestImg, String stomachId, String stomach, String stomachImg, String hipId, String hip, String hipImg, String neck, String fullChest, String shoulderWidth, String rightSleeve, String leftSleeve, String bicep, String wrist, String waistStomach, String hipMeasurement, String frontJacketLength, String frontChestWidth, String backWidth, String halfShoulderWidthLeft, String halfShoulderWidthRight, String fullBackLength, String halfBackLength, String trouserWaist, String trouserOutseam, String trouserInseam, String crotch, String thigh, String knee, String rightFullSleeve, String leftFullSleeve) {
        this.measurementId = measurementId;
        this.stanceId = stanceId;
        this.stance = stance;
        this.stanceImg = stanceImg;
        this.shoulderId = shoulderId;
        this.shoulder = shoulder;
        this.shoulderImg = shoulderImg;
        this.chestId = chestId;
        this.chest = chest;
        this.chestImg = chestImg;
        this.stomachId = stomachId;
        this.stomach = stomach;
        this.stomachImg = stomachImg;
        this.hipId = hipId;
        this.hip = hip;
        this.hipImg = hipImg;
        this.neck = neck;
        this.fullChest = fullChest;
        this.shoulderWidth = shoulderWidth;
        this.rightSleeve = rightSleeve;
        this.leftSleeve = leftSleeve;
        this.bicep = bicep;
        this.wrist = wrist;
        this.waistStomach = waistStomach;
        this.hipMeasurement = hipMeasurement;
        this.frontJacketLength = frontJacketLength;
        this.frontChestWidth = frontChestWidth;
        this.backWidth = backWidth;
        this.halfShoulderWidthLeft = halfShoulderWidthLeft;
        this.halfShoulderWidthRight = halfShoulderWidthRight;
        this.fullBackLength = fullBackLength;
        this.halfBackLength = halfBackLength;
        this.trouserWaist = trouserWaist;
        this.trouserOutseam = trouserOutseam;
        this.trouserInseam = trouserInseam;
        this.crotch = crotch;
        this.thigh = thigh;
        this.knee = knee;
        this.rightFullSleeve = rightFullSleeve;
        this.leftFullSleeve = leftFullSleeve;
    }

    public String getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(String measurementId) {
        this.measurementId = measurementId;
    }

    public String getStanceId() {
        return stanceId;
    }

    public void setStanceId(String stanceId) {
        this.stanceId = stanceId;
    }

    public String getStance() {
        return stance;
    }

    public void setStance(String stance) {
        this.stance = stance;
    }

    public String getStanceImg() {
        return stanceImg;
    }

    public void setStanceImg(String stanceImg) {
        this.stanceImg = stanceImg;
    }

    public String getShoulderId() {
        return shoulderId;
    }

    public void setShoulderId(String shoulderId) {
        this.shoulderId = shoulderId;
    }

    public String getShoulder() {
        return shoulder;
    }

    public void setShoulder(String shoulder) {
        this.shoulder = shoulder;
    }

    public String getShoulderImg() {
        return shoulderImg;
    }

    public void setShoulderImg(String shoulderImg) {
        this.shoulderImg = shoulderImg;
    }

    public String getChestId() {
        return chestId;
    }

    public void setChestId(String chestId) {
        this.chestId = chestId;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getChestImg() {
        return chestImg;
    }

    public void setChestImg(String chestImg) {
        this.chestImg = chestImg;
    }

    public String getStomachId() {
        return stomachId;
    }

    public void setStomachId(String stomachId) {
        this.stomachId = stomachId;
    }

    public String getStomach() {
        return stomach;
    }

    public void setStomach(String stomach) {
        this.stomach = stomach;
    }

    public String getStomachImg() {
        return stomachImg;
    }

    public void setStomachImg(String stomachImg) {
        this.stomachImg = stomachImg;
    }

    public String getHipId() {
        return hipId;
    }

    public void setHipId(String hipId) {
        this.hipId = hipId;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getHipImg() {
        return hipImg;
    }

    public void setHipImg(String hipImg) {
        this.hipImg = hipImg;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getFullChest() {
        return fullChest;
    }

    public void setFullChest(String fullChest) {
        this.fullChest = fullChest;
    }

    public String getShoulderWidth() {
        return shoulderWidth;
    }

    public void setShoulderWidth(String shoulderWidth) {
        this.shoulderWidth = shoulderWidth;
    }

    public String getRightSleeve() {
        return rightSleeve;
    }

    public void setRightSleeve(String rightSleeve) {
        this.rightSleeve = rightSleeve;
    }

    public String getLeftSleeve() {
        return leftSleeve;
    }

    public void setLeftSleeve(String leftSleeve) {
        this.leftSleeve = leftSleeve;
    }

    public String getBicep() {
        return bicep;
    }

    public void setBicep(String bicep) {
        this.bicep = bicep;
    }

    public String getWrist() {
        return wrist;
    }

    public void setWrist(String wrist) {
        this.wrist = wrist;
    }

    public String getWaistStomach() {
        return waistStomach;
    }

    public void setWaistStomach(String waistStomach) {
        this.waistStomach = waistStomach;
    }

    public String getHipMeasurement() {
        return hipMeasurement;
    }

    public void setHipMeasurement(String hipMeasurement) {
        this.hipMeasurement = hipMeasurement;
    }

    public String getFrontJacketLength() {
        return frontJacketLength;
    }

    public void setFrontJacketLength(String frontJacketLength) {
        this.frontJacketLength = frontJacketLength;
    }

    public String getFrontChestWidth() {
        return frontChestWidth;
    }

    public void setFrontChestWidth(String frontChestWidth) {
        this.frontChestWidth = frontChestWidth;
    }

    public String getBackWidth() {
        return backWidth;
    }

    public void setBackWidth(String backWidth) {
        this.backWidth = backWidth;
    }

    public String getHalfShoulderWidthLeft() {
        return halfShoulderWidthLeft;
    }

    public void setHalfShoulderWidthLeft(String halfShoulderWidthLeft) {
        this.halfShoulderWidthLeft = halfShoulderWidthLeft;
    }

    public String getHalfShoulderWidthRight() {
        return halfShoulderWidthRight;
    }

    public void setHalfShoulderWidthRight(String halfShoulderWidthRight) {
        this.halfShoulderWidthRight = halfShoulderWidthRight;
    }

    public String getFullBackLength() {
        return fullBackLength;
    }

    public void setFullBackLength(String fullBackLength) {
        this.fullBackLength = fullBackLength;
    }

    public String getHalfBackLength() {
        return halfBackLength;
    }

    public void setHalfBackLength(String halfBackLength) {
        this.halfBackLength = halfBackLength;
    }

    public String getTrouserWaist() {
        return trouserWaist;
    }

    public void setTrouserWaist(String trouserWaist) {
        this.trouserWaist = trouserWaist;
    }

    public String getTrouserOutseam() {
        return trouserOutseam;
    }

    public void setTrouserOutseam(String trouserOutseam) {
        this.trouserOutseam = trouserOutseam;
    }

    public String getTrouserInseam() {
        return trouserInseam;
    }

    public void setTrouserInseam(String trouserInseam) {
        this.trouserInseam = trouserInseam;
    }

    public String getCrotch() {
        return crotch;
    }

    public void setCrotch(String crotch) {
        this.crotch = crotch;
    }

    public String getThigh() {
        return thigh;
    }

    public void setThigh(String thigh) {
        this.thigh = thigh;
    }

    public String getKnee() {
        return knee;
    }

    public void setKnee(String knee) {
        this.knee = knee;
    }

    public String getRightFullSleeve() {
        return rightFullSleeve;
    }

    public void setRightFullSleeve(String rightFullSleeve) {
        this.rightFullSleeve = rightFullSleeve;
    }

    public String getLeftFullSleeve() {
        return leftFullSleeve;
    }

    public void setLeftFullSleeve(String leftFullSleeve) {
        this.leftFullSleeve = leftFullSleeve;
    }
}
