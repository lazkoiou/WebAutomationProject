package gr.qa.api.PetStore.DTOs.enums;

public enum TagsEnum {

    DOG(1, "Dog"),
    FOUR_LEGGED(2, "Four legged"),
    BEST_FRIEND(3, "Best friend")
    ;

    int id;
    String tagName;

    TagsEnum(int id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public int getId() {
        return this.id;
    }

    public String getCategoryName() {
        return this.tagName;
    }

}
