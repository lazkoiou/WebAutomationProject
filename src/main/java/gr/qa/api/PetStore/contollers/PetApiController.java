package gr.qa.api.PetStore.contollers;

import gr.qa.api.PetStore.DTOs.DeletePetDTO;
import gr.qa.api.PetStore.DTOs.PetDTO;
import gr.qa.helperClasses.PropertiesManager;
import gr.qa.helperClasses.GenericRestApiCalls;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The controller for all Pet API tests
 * https://petstore.swagger.io/#/
 */
public class PetApiController extends GenericRestApiCalls {

    private final static Logger logger = LogManager.getLogger(PetApiController.class);
    private final String petApiEndpoint;

    /**
     * Constructor of the controller
     */
    public PetApiController() {
        petApiEndpoint = PropertiesManager.properties.getProperty("petApiEndpoint");
    }

    /**
     * Add a new pet
     * POST https://petstore.swagger.io/#/pet/
     * @param petDTO : DTO to be converted to JSON
     */
    public void postAddPet(PetDTO petDTO) {
        String petJSON = serializeDtoToJson(petDTO);
        postRequest(petApiEndpoint, petJSON);
    }

    /**
     * Finds a pet by using the id and returns the name of the pet corresponding to that id
     * GET https://petstore.swagger.io/#/pet/{id}
     * @param id : id of the pet
     * @param petDTO : a DTO for the pet
     * @return : the petDTO received from the request
     */
    public PetDTO getFindPetById(int id, PetDTO petDTO) {
        String endpoint = petApiEndpoint + id;
        String responseJSON = getRequest(endpoint);
        return deSerializeJsonToDto(responseJSON, petDTO.getClass());
    }

    /**
     * Finds a pet by using the id and returns the name of the pet corresponding to that id
     * PUT https://petstore.swagger.io/#/pet/
     * @param petDTO : a DTO for the pet
     * @return : the petDTO received from the request
     */
    public PetDTO putUpdateExistingPet(PetDTO petDTO) {
        String petJSON = serializeDtoToJson(petDTO);
        String responseJSON = putRequest(petApiEndpoint, petJSON);
        return deSerializeJsonToDto(responseJSON, petDTO.getClass());
    }

    /**
     * Deletes a pet by using the id and returns the response
     * DELETE https://petstore.swagger.io/#/pet/{id}
     * @param id : id of the pet to be deleted
     * @return : the deletePetDTO received from the request
     */
    public DeletePetDTO deletePet(int id) {
        String endpoint = petApiEndpoint + id;
        String responseJson = deleteRequest(endpoint);
        return deSerializeJsonToDto(responseJson, DeletePetDTO.class);
    }

}
