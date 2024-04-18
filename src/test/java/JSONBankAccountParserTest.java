import org.bank.core.BankAccount;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class JSONBankAccountParserTest {

    @Test
    public void testMissingFields() {
        String filePath = "C:\\internship\\bank-java-project\\src\\test\\resources\\missing_fields.json";

        List<BankAccount> bankAccounts = org.bank.parsing.JSONBankAccountParser.parseJSON2BankAccountList2(filePath);

        assertNotNull(bankAccounts);
        assertEquals(1, bankAccounts.size());

    }

    @Test
    public void testInvalidFields() {
        String filePath = "C:\\internship\\bank-java-project\\src\\test\\resources\\invalid_fields.json";

        List<BankAccount> bankAccounts = org.bank.parsing.JSONBankAccountParser.parseJSON2BankAccountList2(filePath);

        assertNotNull(bankAccounts);
        assertEquals(1, bankAccounts.size());
    }

    @Test
    public void testGoodFieldsBabValues() {

    }

    @Test
    public void testInvalidFileFormat() {
        String filePath = "C:\\internship\\bank-java-project\\src\\test\\resources\\not_json_file.xml";

    }

    @Test(expected = IOException.class)
    public void testNotFoundFileException() {
        String filePath = "C:\\internship\\bank-java-project\\src\\test\\resources\\not_created_file.json";
        List<BankAccount> bankAccounts = org.bank.parsing.JSONBankAccountParser.parseJSON2BankAccountList2(filePath);

    }

    @Test
    public void testNotValidJSONException() {

    }



}
