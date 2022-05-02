import com.zjh.utils.MD5Util;
import org.junit.Test;

public class testMD5 {
    @Test
    public void testMD5(){
        String str = MD5Util.getMD5("000000");
        System.out.println(str);
    }
}
