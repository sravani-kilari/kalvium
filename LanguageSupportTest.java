Step 3: 
Add Tests for the New Languages
Create a test class LanguageSupportTest.java to test the new languages.

import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageSupportTest {

    @Test
    public void testRubyCode() throws IOException {
        String code = "puts 'Hello, World!'";
        String fileName = "test.rb";
        createFile(fileName, code);

        String output = LanguageSupport.compileAndRun("ruby", fileName, null);
        assertEquals("Hello, World!\n", output);
    }

    @Test
    public void testPHPCode() throws IOException {
        String code = "<?php echo 'Hello, World!'; ?>";
        String fileName = "test.php";
        createFile(fileName, code);

        String output = LanguageSupport.compileAndRun("php", fileName, null);
        assertEquals("Hello, World!\n", output);
    }

    @Test
    public void testSwiftCode() throws IOException {
        String code = "print(\"Hello, World!\")";
        String fileName = "test.swift";
        String executable = "test";
        createFile(fileName, code);

        String output = LanguageSupport.compileAndRun("swift", fileName, executable);
        assertEquals("Hello, World!\n", output);
    }

    private void createFile(String fileName, String content) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        }
    }
}
