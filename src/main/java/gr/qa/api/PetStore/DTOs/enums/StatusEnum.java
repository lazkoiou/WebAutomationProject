package gr.qa.api.PetStore.DTOs.enums;

public enum  StatusEnum {
    
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold")
    ;

    String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
