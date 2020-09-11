package framework.model;

import lombok.*;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class EstimateModel {
    private String numberOfInstances;
    private String instancesAssignment;
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
}
