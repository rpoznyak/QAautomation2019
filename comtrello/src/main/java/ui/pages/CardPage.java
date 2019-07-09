package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import ui.core.BrowserFactory;
import ui.core.Elem;

public class CardPage extends BrowserFactory {
    private By cardName = By.cssSelector(".mod-card-back-title.js-card-detail-title-input");
    private By closeMember = By.cssSelector(".pop-over-header-close-btn.icon-sm.icon-close");
    private By membersUsername = By.cssSelector(".quiet.u-bottom");
    private By closeLabels = By.cssSelector(".pop-over-header-close-btn.icon-sm.icon-close");
    private By saveLabel = By.cssSelector(".primary.wide.js-submit");
    private By nameLabel = By.cssSelector(".mod-card-detail>.label-text");
    private By saveDescription = By.cssSelector(".primary.confirm.mod-submit-edit.js-save-edit");
    private By descriptionText = By.cssSelector(".js-fill-card-detail-desc .markeddown.js-show-with-desc>p");
    private By saveChecklist = By.cssSelector(".primary.wide.confirm.js-add-checklist");
    private By checklistName = By.cssSelector(".editable.non-empty.checklist-title>.current.hide-on-edit");
    private By closeChecklistItem = By.cssSelector(".icon-lg.icon-close.dark-hover.cancel.js-cancel-checklist-item");
    private By saveDueDate = By.cssSelector(".primary.wide.confirm");
    private By valueDueDate = By.cssSelector(".js-date-text.card-detail-due-date-text.js-details-edit-due-date");

    public Elem openMembers(){
        return new Elem(By.cssSelector("[title='Members']"));
    }
    public Elem setMembers(){
        return new Elem(By.cssSelector(".full-name"));
    }
    public Elem getUsernameMembers(){
        return new Elem(By.cssSelector(".js-card-detail-members-list>.member.js-member-on-card-menu"));
    }
    public Elem openLabels(){
        return new Elem(By.cssSelector("[title='Labels']"));
    }
    public Elem editLabel(){
        return new Elem(By.xpath("//*[@class='edit-labels-pop-over js-labels-list']/li[1]/a"));
    }

    public Elem inputLabelName(){
        return new Elem(By.cssSelector("#labelName"));
    }

    public Elem setLabel(){
        return new Elem(By.cssSelector(".card-label.mod-selectable.card-label-green"));
    }

    public Elem cardByUrlName(String urlName){
        return new Elem(By.cssSelector("[href='"+urlName.substring(18)+"']"), urlName);
    }
    public Elem selectDescriptionText(){
        return new Elem(By.cssSelector(".js-fill-card-detail-desc .description-fake-text-area"));
    }

    public Elem addDescriptionText(){
        return new Elem(By.cssSelector(".field.field-autosave.js-description-draft.description.card-description"));
    }

    public Elem openChecklist(){
        return new Elem(By.cssSelector("[title='Checklist']"));
    }

    public Elem inputChecklistName(){
        return new Elem(By.cssSelector("#id-checklist"));
    }

    public Elem openDueDate(){
        return new Elem(By.cssSelector("[title='Due Date']"));
    }
    public Elem inputDueDate(){
        return new Elem(By.cssSelector(".datepicker-select-input.js-dpicker-date-input.js-autofocus"));
    }
    public Elem inputDueTime(){
        return new Elem(By.cssSelector(".datepicker-select-input.js-dpicker-time-input"));
    }

    @Step("Open card")
    public void openCard(String urlName){
        cardByUrlName(urlName).click();
    }

    @Step
    public String getCardName(){
        return driver.findElement(cardName).getText();
    }

    @Step
    public void addMembers(){
        openMembers().click();
        setMembers().click();
        driver.findElement(closeMember).click();
    }

    @Step
    public String getMembersUsername(){
        getUsernameMembers().click();
        return driver.findElement(membersUsername).getText().substring(1);
    }

    @Step
    public void addLabel(){
        openLabels().click();
        editLabel().click();
        inputLabelName().type("test_label_name");
        driver.findElement(saveLabel).click();
        setLabel().click();
        driver.findElement(closeLabels).click();
    }

    @Step
    public String getLabel(){
        return driver.findElement(nameLabel).getText();
    }

    @Step
    public void addDescription(){
        selectDescriptionText().click();
        addDescriptionText().type("test_description_name");
        driver.findElement(saveDescription).click();
    }

    @Step
    public String getDescription(){
        return driver.findElement(descriptionText).getText();
    }

    @Step
    public void addChecklist(){
        openChecklist().click();
        inputChecklistName().type("test_checklist_name");
        driver.findElement(saveChecklist).click();
        driver.findElement(closeChecklistItem).click();

    }

    @Step
    public String getChecklist(){
        return driver.findElement(checklistName).getText();
    }

    @Step
    public void addDueDate(){
        openDueDate().click();
        inputDueDate().type("1/1/2020");
        inputDueTime().click();
        inputDueTime().type("06:00");
        driver.findElement(saveDueDate).click();
    }
    @Step
    public String getDueDate(){
        return driver.findElement(valueDueDate).getText();
    }

}

