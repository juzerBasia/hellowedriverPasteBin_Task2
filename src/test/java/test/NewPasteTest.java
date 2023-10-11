package test;

import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.PastebinPage;


@Slf4j
public class NewPasteTest extends BaseTest {

    @Test(description = "New Pastebin created ", invocationCount = 5)
    public void testRun() {
        PastebinPage pastebinPage = new PastebinPage();

        /*    private final String code = "git config --global user.name  New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m Legacy code)\n" +
                "git push origin master --force\n";*/
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
