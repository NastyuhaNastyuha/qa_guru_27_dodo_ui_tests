//package config;
//
//import org.aeonbits.owner.ConfigFactory;
//
//public enum ConfigReader {
//    Instance;
//    private static final WebConfig APPLY_WEB_CONFIG =
//            ConfigFactory.create(
//                    WebConfig.class,
//                    System.getProperties()
//            );
//
//    public WebConfig read() {
//        return APPLY_WEB_CONFIG;
//    }
//}