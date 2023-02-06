package gr.qa.api.PetStore.DTOs;

/**
 * DTO for tags
 */
public class TagsDTO {

    int id;
    String name;

    public TagsDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Takes any number of TagDTO arguments, puts them in a TagDTO[] and
     * returns this array
     * @param tagsDTOs : any number of tagDTOs
     * @return : returns the TagsDTO in TagsDTO[]
     */
    public static TagsDTO[] tagsDTOArray(TagsDTO... tagsDTOs) {
        TagsDTO[] tagsDTOArray = new TagsDTO[tagsDTOs.length];
        int i = 0;
        for (TagsDTO tagsDTO : tagsDTOs) {
            tagsDTOArray[i++] = tagsDTO;
        }
        return tagsDTOArray;
    }

}
