package com.works;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {AppTest.class, UserMockTest.class})
//@SelectPackages(value = {"com.works"})
//@org.junit.platform.suite.api.Suite
public class AppSuiteTest {
}
