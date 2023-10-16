package gr.qa.api.PetStore.DTOs;

import lombok.Data;

@Data
public class DeletePetDTO {

    private int code;
    private String type;
    private String message;

    public DeletePetDTO(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

}
