package pl.futurecollars.invoices.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InFileDbConfig {

    @Value("${infile.db.path}")
    private String inFileDbPath;

    @Value("${infile.counter.path}")
    private String inFileCounterPath;

    public String getDbFilePath() {
        return inFileDbPath;
    }

    public String getCounterFilePath() {
        return inFileCounterPath;
    }
}
