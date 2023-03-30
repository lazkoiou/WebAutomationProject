package com.tests.api.petStore;

import gr.qa.api.PetStore.DTOs.DeletePetDTO;
import gr.qa.api.PetStore.DTOs.PetDTO;
import gr.qa.api.PetStore.DTOs.TagsDTO;
import gr.qa.api.PetStore.DTOs.enums.CategoryEnum;
import gr.qa.api.PetStore.DTOs.enums.StatusEnum;
import gr.qa.api.PetStore.DTOs.enums.TagsEnum;
import gr.qa.api.PetStore.contollers.PetApiController;
import gr.qa.helperClasses.PropertiesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PetApiTests {

    private final static Logger logger = LogManager.getLogger(PetApiTests.class);
    private PetApiController petApiController;

    PetDTO petDTO;
    int id;
    int upperLimit = 10000;

    @BeforeClass
    public void initialize() {
        PropertiesManager.loadProperties();
        petApiController = new PetApiController();
    }

    @Test (priority = 1)
    public void addNewPetToStore_expect_canBeFoundById() {
        // create JSON for POST request
        id = PetDTO.generateRandomPetId(upperLimit);
        String[] photoURLs = PetDTO.photoURLsArray("https://photoUrl1", "https://photoUrl2", "https://photoUrl3");
        TagsDTO[] tagsDTOs = TagsDTO.tagsDTOArray(new TagsDTO(TagsEnum.BEST_FRIEND.getId(), TagsEnum.BEST_FRIEND.getCategoryName()));
        petDTO = new PetDTO(
                id,
                CategoryEnum.HUSKY.getId(), CategoryEnum.HUSKY.getCategoryName(),
                "Hermes",
                photoURLs,
                tagsDTOs,
                StatusEnum.AVAILABLE.getStatus()
        );

        // Execute a POST request to add new pet to store
        petApiController.postAddPet(petDTO);
        
        // Assert pet can be found using its id with a GET request
        PetDTO returnedPetDTO = petApiController.getFindPetById(id, petDTO);
        assertEquals(returnedPetDTO.getName(), "Hermes");
    }

    @Test(priority = 2, dependsOnMethods = "addNewPetToStore_expect_canBeFoundById")
    public void sellPet_expect_statusHasChangedToSold() {
        // Make the change to the previously created pet's name
        petDTO.setStatus(StatusEnum.SOLD.getStatus());

        // PUT request
        PetDTO returnedPetDTO = petApiController.putUpdateExistingPet(petDTO);
        assertEquals(returnedPetDTO.getStatus(), StatusEnum.SOLD.getStatus());
    }


    @Test(priority = 3, dependsOnMethods = "addNewPetToStore_expect_canBeFoundById")
    public void deletePet_expect_petDeleted() {
        // DELETE request
        DeletePetDTO deletePetDTO = petApiController.deletePet(petDTO.getId());
        assertEquals(deletePetDTO.getCode(), 200); // assert status code in response is 200
        assertEquals(deletePetDTO.getMessage(), Integer.toString(petDTO.getId())); // assert deleted id is the one from our pet
        // Try to delete again - response be null because the pet will not exist anymore
        assertNull(petApiController.deletePet(petDTO.getId()));
    }
}
