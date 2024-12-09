package cucumberTests;

public class EcommerceFreshMain {
    public static void main(String[] args) throws Throwable {
        System.out.println("UI Testing for Ecommerce Fresh Page:");
        io.cucumber.core.cli.Main.main(
                "target/test-classes/features",
                "-g", "stepImplementations",
                "-p","pretty",
                "-p","json:cucumber-reports/Cucumber.json",
                "-p", "html:cucumber-reports/Cucumber.html"
        );
    }

}
