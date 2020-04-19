package com.zeitgeist.employee.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 9/28/2017.
 */

public class Consumption {

    @SerializedName("fabric_consumption")
    @Expose
    private String fabricConsumption;
    @SerializedName("lining_consumption")
    @Expose
    private String liningConsumption;

    public Consumption() {
    }

    public Consumption(String fabricConsumption, String liningConsumption) {
        this.fabricConsumption = fabricConsumption;
        this.liningConsumption = liningConsumption;
    }

    public String getFabricConsumption() {
        return fabricConsumption;
    }

    public void setFabricConsumption(String fabricConsumption) {
        this.fabricConsumption = fabricConsumption;
    }

    public String getLiningConsumption() {
        return liningConsumption;
    }

    public void setLiningConsumption(String liningConsumption) {
        this.liningConsumption = liningConsumption;
    }
}
