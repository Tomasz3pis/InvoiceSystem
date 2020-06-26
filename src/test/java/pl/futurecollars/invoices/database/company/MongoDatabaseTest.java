package pl.futurecollars.invoices.database.company;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.futurecollars.invoices.model.Company;
import pl.futurecollars.invoices.providers.TestCompanyProvider;

import java.util.Optional;

class MongoDatabaseTest {

    private MongodExecutable mongodExe;
    private MongodProcess mongod;
    private MongoClient mongo;

    @Autowired
    private MongoDatabase database;

    @BeforeEach
    public void beforeEach() throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        String bindIp = "localhost";
        int port = 12345;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .build();
        this.mongodExe = starter.prepare(mongodConfig);
        this.mongod = mongodExe.start();
        this.mongo = new MongoClient(bindIp, port);
    }

    @AfterEach
    public void afterEach() {
        if (this.mongod != null) {
            this.mongod.stop();
            this.mongodExe.stop();
        }
    }

    @Test
    void shouldSaveCompany() {
        //given
        Company company = TestCompanyProvider.futureCollars();

        //when
        long id = database.saveCompany(company);

        //then
        assertThat(database.getCompanyById(id), is(Optional.of(company)));
    }

//    @Test
//    void shouldReturnSavedCompanies() {
//        //given
//        Company company = TestCompanyProvider.futureCollars();
//
//        //when
//        database.saveCompany(company);
//        database.saveCompany(differentCompany);
//
//        //then
//        assertThat(database.getCompanies(), is(List.of(company, differentCompany)));
//    }
//
//    @Test
//    void shouldDeleteCompany() {
//        //given
//        Company company = TestCompanyProvider.futureCollars();
//        Company diffe = TestCompanyProvider.futureCollars();
//        long idOne = database.saveCompany(company);
//        long idTwo = database.saveCompany(differentCompany);
//
//        //when
//        database.deleteCompany(idOne);
//
//        //then
//        assertThat(database.getCompanies(), is(List.of(differentCompany)));
//        assertThat(database.getCompanyById(idOne), is(Optional.empty()));
//    }
//
//    @Test
//    void shouldUpdateCompany() {
//        //given
//        long idOne = database.saveCompany(company);
//
//        //when
//        database.updateCompany(idOne, differentCompany);
//
//        //then
//        assertThat(database.getCompanyById(idOne), is(Optional.of(differentCompany)));
//    }
//
//    @Test
//    void convertCompanyToDBObject() {
//    }
//
//    @Test
//    void convertDBObjectToCompany() {
//    }
}