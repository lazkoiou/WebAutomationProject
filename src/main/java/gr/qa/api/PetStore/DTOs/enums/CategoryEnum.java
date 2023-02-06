package gr.qa.api.PetStore.DTOs.enums;

public enum  CategoryEnum {

    GOLDEN_RETRIEVER(1, "Golden Retriever"),
    GREAT_DANE(2, "Great Dane"),
    HUSKY(3, "Husky")
    ;

    int id;
    String categoryName;

    CategoryEnum (int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public int getId() {
        return this.id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

}
