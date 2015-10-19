package random.model;

/**
 * Created by DaoSui on 2015/10/17.
 */
public class Demo {

    private String demoName;
    private String demoUrl;
    private String demoVersion;

    public Demo(String demoName, String demoUrl, String demoVersion) {
        this.demoName = demoName;
        this.demoUrl = demoUrl;
        this.demoVersion = demoVersion;
    }

    public String getDemoName() {
        return demoName;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public String getDemoVersion() {
        return demoVersion;
    }

    public void setDemoName(String demoName) {
        this.demoName = demoName;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }

    public void setDemoVersion(String demoVersion) {
        this.demoVersion = demoVersion;
    }
}
