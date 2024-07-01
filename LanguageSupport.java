Step 2: Implement the Language Support in Java
Create a new class LanguageSupport.java to handle compilation and execution of the new languages.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LanguageSupport {

    public static String compileAndRun(String language, String sourceFile, String executable) throws IOException {
        String compileCommand;
        String runCommand;

        switch (language.toLowerCase()) {
            case "ruby":
                compileCommand = "ruby -c " + sourceFile;
                runCommand = "ruby " + sourceFile;
                break;
            case "php":
                compileCommand = "php -l " + sourceFile;
                runCommand = "php " + sourceFile;
                break;
            case "swift":
                compileCommand = "swiftc " + sourceFile + " -o " + executable;
                runCommand = "./" + executable;
                break;
            default:
                throw new IllegalArgumentException("Language not supported: " + language);
        }

        String compileResult = executeCommand(compileCommand);
        if (!compileResult.isEmpty()) {
            return "Compilation Error:\n" + compileResult;
        }

        return executeCommand(runCommand);
    }

    private static String executeCommand(String command) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        return output.toString();
    }
}
