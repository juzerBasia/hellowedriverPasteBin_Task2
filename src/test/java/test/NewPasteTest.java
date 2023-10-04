package test;

import org.junit.jupiter.api.Assertions;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import page.PastebinPage;

import java.util.Map;


@Slf4j
public class NewPasteTest extends BaseTest {

    /*    private final String code = "git config --global user.name  New Sheriff in Town\"\n" +
                "git reset $(git commit-tree HEAD^{tree} -m Legacy code)\n" +
                "git push origin master --force\n";*/
    private final String code = "first Line\n" +
            "second Line\n" +
            "third Line";
    private final String pasteExpiration = "10 Minutes";
    private final String pasteNameTitle = "how to gain dominance among developers";
    private final String syntaxHighlighting = "Bash";


    @Test(description = "New Pastebin created ", invocationCount = 5)
    public void testRun() {
        PastebinPage pastebinPage = new PastebinPage();

        pastebinPage.openPage()
                .enterText(code)
                .selectPasteExpiration(pasteExpiration)
                .selectSyntaxHighlighting(syntaxHighlighting)
                .enterPasteNameTitle(pasteNameTitle)
                .createNewPaste();


        Map<String, String> results = pastebinPage.collectResults(pastebinPage.SYNTAX_HIGHLIGHTING, pastebinPage.PASTE_NAME_TITLE, pastebinPage.CODE);
        Assertions.assertEquals(code.replace("\n", ""), results.get(pastebinPage.CODE));
        Assertions.assertEquals(syntaxHighlighting, results.get(pastebinPage.SYNTAX_HIGHLIGHTING));
        Assertions.assertEquals(pasteNameTitle, results.get(pastebinPage.PASTE_NAME_TITLE));
        Assertions.assertEquals(pasteExpiration, results.get(pastebinPage.PASTE_EXPIRATION));
    }


}
