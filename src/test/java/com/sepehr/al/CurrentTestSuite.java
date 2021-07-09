package com.sepehr.al;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({AlgorithmsTest.class})
@IncludeTags(value = {"currentTest"})
public class CurrentTestSuite {

}
