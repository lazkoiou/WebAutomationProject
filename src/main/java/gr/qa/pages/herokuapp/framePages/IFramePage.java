package gr.qa.pages.herokuapp.framePages;

import gr.qa.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IFramePage extends BasePage {

    @FindBy(css = "#tinymce")
    private WebElement editorText;

    public WebElement getEditorText() {
        return editorText;
    }

    public void inputTextIntoIFrameEditor(String text) {
        editorText.clear();
        editorText.sendKeys(text);
    }

}
