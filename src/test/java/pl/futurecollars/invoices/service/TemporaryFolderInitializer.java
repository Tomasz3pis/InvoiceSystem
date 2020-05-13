package pl.futurecollars.invoices.service;

import com.google.common.io.Files;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TemporaryFolderInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @TempDir
    public File tempDir = Files.createTempDir();
    private File db;
    private File counter;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        db = new File(tempDir.getAbsolutePath() + "/db.txt");
        counter = new File(tempDir.getAbsolutePath() + "/counter.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(counter))) {
            bufferedWriter.write("0");
        } catch (IOException e) {
            e.printStackTrace();
        }
        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext, "infile.db.path=" + db.getAbsolutePath().replace("\\", "/"));
        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext, "infile.counter.path=" + counter.getAbsolutePath().replace("\\", "/"));
    }
}
