package framework.model;

import lombok.*;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class EstimateModel {
    private String numberOfInstances;
    private String operatingSystem;
    private String virtualMachineClass;
    private String machineType;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String addGpus;
    private String numberOfGpus;
    private String gpuType;
    private String localSsd;
    private String datacenterLocation;
    private String committedUsage;
    private String estimatedCost;

    public String getValueOfAddGpuCheckbox() {
        return addGpus;
    }

    public void getValueOfAddGpuCheckbox(String addGpus) {
        this.addGpus = addGpus;
    }

    public void setEstimatedCost(String estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    //    public EstimateModel(String numberOfInstances, String operatingSystem, String virtualMachineClass,
//                    String machineType, String numberOfGpus, String gpuType,
//                    String localSsd, String datacenterLocation, String committedUsage) {
//        this.numberOfInstances = numberOfInstances;
//        this.operatingSystem = operatingSystem;
//        this.virtualMachineClass = virtualMachineClass;
//        this.machineType = machineType;
//        this.numberOfGpus = numberOfGpus;
//        this.gpuType = gpuType;
//        this.localSsd = localSsd;
//        this.datacenterLocation = datacenterLocation;
//        this.committedUsage = committedUsage;
//    }
}
