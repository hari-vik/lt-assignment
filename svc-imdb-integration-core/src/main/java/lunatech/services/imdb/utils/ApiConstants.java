package lunatech.services.imdb.utils;
/**
 * Class to manage common application constants
 * 
 * @author hari
 *
 */
public class ApiConstants {
	private ApiConstants() {
	}
	
	public static final String BASE_PATH = "${service.config.basepath}";
	public static final String IMDB_V1_PATH = BASE_PATH + "/v1";
	public static final String COMMA = ",";
	public static final String LOG_ERR_PLACEHOLDER="RequestId {} failed with message {}";
}
