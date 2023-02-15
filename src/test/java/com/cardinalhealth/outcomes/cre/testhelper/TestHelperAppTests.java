package com.cardinalhealth.outcomes.cre.testhelper;

import com.cardinalhealth.outcomes.cre.testhelper.config.AppTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = AppTestConfig.class)
@ActiveProfiles({"test"})
class TestHelperAppTests {

    @Test
    void contextLoads() {
    }

}