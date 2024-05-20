package runner;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;


@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME,value = "src/test/resources")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,value = "steps")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "pretty, html:target/cucumberRunner.html")
//@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME,value = "@three")

public class CucumberRunner {
}
