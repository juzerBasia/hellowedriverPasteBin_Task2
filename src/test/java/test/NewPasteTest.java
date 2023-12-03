package test;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.PastebinPage;


@Slf4j
public class NewPasteTest extends BaseTest {

    @Test(description = "New Pastebin created ", invocationCount = 1)
    public void testRun() {
        PastebinPage pastebinPage = new PastebinPage();

        String code = "first Line\n" +
                "second Line\n" +
                "third Line";
        String pasteExpiration = "10 Minutes";
        String pasteNameTitle = "how to gain dominance among developers";
        String syntaxHighlighting = "Bash";


        pastebinPage.openPage()
                .enterText(code)
                .selectPasteExpiration(pasteExpiration)
                .selectSyntaxHighlighting(syntaxHighlighting)
                .enterPasteNameTitle(pasteNameTitle)
                .createNewPaste();

        Assert.assertEquals(code.replace("\n", ""),pastebinPage.getTextFromWebElements(pastebinPage.getCreatedCode()));
        Assert.assertEquals(syntaxHighlighting, pastebinPage.getCreatedSyntaxHighlighting().getText());
        Assert.assertEquals(pasteNameTitle,pastebinPage.getCreatedPasteNameTitle().getText());

    }
}
