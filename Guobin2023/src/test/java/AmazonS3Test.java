

import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.json.B2JsonException;
import com.hl.travel.constant.S3Constant;
import com.hl.travel.util.B2Utils;
import org.junit.Test;

import java.io.IOException;


public class AmazonS3Test extends BaseTest {


    @Test
    public void uploadFile() throws IOException, B2JsonException, B2Exception {

        B2Utils b2Utils = new B2Utils();
        b2Utils.uploadFile(S3Constant.UPLOAD_FILE, S3Constant.BUCKET_NAME,"C:\\Users\\HL\\Desktop\\1.jpg","test.jpg");

    }

    @Test
    public void deleteFile() throws IOException, B2JsonException, B2Exception {

        B2Utils b2Utils = new B2Utils();
        b2Utils.deleteFile(S3Constant.DELETE_FILE,S3Constant.BUCKET_NAME,"test.jpg");

    }
}
