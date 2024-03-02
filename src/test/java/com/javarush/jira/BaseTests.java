package com.javarush.jira;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
//@ActiveProfiles("pg")
@ActiveProfiles("h2")
abstract class BaseTests {
}
