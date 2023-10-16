package gr.qa.api.PetStore.DTOs;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * This is a DTO class that will be used to in PetApiController
 * POST https://petstore.swagger.io/#/pet/
 */
@Data
public class PetDTO {

    private final static Logger logger = LogManager.getLogger(PetDTO.class);

    private int id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private TagsDTO[] tagsDTOs;
    private String status;

    public PetDTO(int id, int categoryId, String categoryName, String name, String[] photoUrls, TagsDTO[] tagsDTOs, String status) {
        this.id = id;
        this.category = new Category(categoryId, categoryName);
        this.name = name;
        this.photoUrls = photoUrls;
        this.tagsDTOs = tagsDTOs;
        this.status = status;
    }

    public class Category {
        int id;
        String name;

        public Category(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    /**
     * Takes any number of String arguments, puts them in a String[] and
     * returns this array
     * @param photoURLStrings : photo urls
     * @return : returns the Strings in String[]
     */
    public static String[] photoURLsArray(String... photoURLStrings) {
        String[] photoURLs = new String[photoURLStrings.length];
        int i = 0;
        for (String photoURLString : photoURLStrings) {
            photoURLs[i++] = photoURLString;
        }
        return photoURLs;
    }

    /**
     * Generates a random pet id, with an upper bound
     * @param upperLimit : upper bound / lomit
     * @return : the new generated random id
     */
    public static int generateRandomPetId(int upperLimit) {
        Random rand = new Random();
        int id = rand.nextInt(upperLimit);
        logger.info("Random id created: " + id);
        return id;
    }

}
