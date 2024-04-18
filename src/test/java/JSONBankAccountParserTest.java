import org.bank.core.BankAccount;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class JSONBankAccountParserTest {

    @Test
    public void testMissingFields() {
        String filePath = getClass().getResource("/missing_fields.json").getPath();


        List<BankAccount> bankAccounts = org.bank.parsing.JSONBankAccountParser.parseJSON2BankAccountList2(filePath);

        assertNotNull(bankAccounts);
        assertEquals(1, bankAccounts.size());

    }

    @Test
    public void testInvalidFields() {
        String filePath = getClass().getResource("/invalid_fields.json").getPath();


        List<BankAccount> bankAccounts = org.bank.parsing.JSONBankAccountParser.parseJSON2BankAccountList2(filePath);

        assertNotNull(bankAccounts);
        assertEquals(1, bankAccounts.size());
    }

    @Test
    public void testGoodFieldsBadValues() {

    }

    @Test
    public void testInvalidFileFormat() {


    }

    @Test(expected = IOException.class)
    public void testNotFoundFileException() {
        String filePath = "/not_created_file.json";
        List<BankAccount> bankAccounts = org.bank.parsing.JSONBankAccountParser.parseJSON2BankAccountList2(filePath);

    }

    @Test
    public void testNotValidJSONException() {

    }

}