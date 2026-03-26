import com.zhurong.platform.base.util.PathResolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathResolverTest {

    // ========================
    // 路径类型判断
    // ========================

    @Test
    void testIsAbsolute_Windows() {
        assertTrue(PathResolver.isAbsolute("C:\\data\\file.txt"));
    }

    @Test
    void testIsAbsolute_Linux() {
        assertTrue(PathResolver.isAbsolute("/app/data/file.txt"));
    }

    @Test
    void testIsAbsolute_UNC() {
        assertTrue(PathResolver.isAbsolute("\\\\172.16.220.24\\share\\file.txt"));
    }

    @Test
    void testIsRelative() {
        assertTrue(PathResolver.isRelative("Nestings\\1\\1119Box1.wmf"));
    }

    // ========================
    // UNC 场景（你当前重点）
    // ========================

    @Test
    void testResolve_UNC_Base_Relative() {
        String base = "\\\\172.16.220.24\\Lantek\\Database\\CRDB";
        String relative = "Nestings\\1\\1119Box1.wmf";

        String result = PathResolver.resolve(base, relative);
        System.out.println(result);
        assertEquals(
                "\\\\172.16.220.24\\Lantek\\Database\\CRDB\\Nestings\\1\\1119Box1.wmf",
                result
        );
    }

    @Test
    void testResolve_UNC_WithAbsoluteInput() {
        String base = "\\\\172.16.220.24\\Lantek";
        String absolute = "\\\\172.16.220.24\\Other\\file.wmf";

        String result = PathResolver.resolve(base, absolute);

        assertEquals(
                "\\\\172.16.220.24\\Other\\file.wmf",
                result
        );
    }

    // ========================
    // Linux 场景
    // ========================

    @Test
    void testResolve_Linux() {
        String base = "/app/data";
        String relative = "file.txt";

        String result = PathResolver.resolve(base, relative);

        assertTrue(result.endsWith("/app/data/file.txt"));
    }

    @Test
    void testResolve_Linux_AbsoluteInput() {
        String base = "/app/data";
        String absolute = "/tmp/test.txt";

        String result = PathResolver.resolve(base, absolute);

        assertEquals("/tmp/test.txt", result);
    }

    // ========================
    // Windows 本地路径
    // ========================

    @Test
    void testResolve_WindowsLocal() {
        String base = "C:\\data";
        String relative = "file.txt";

        String result = PathResolver.resolve(base, relative);

        assertEquals("C:\\data\\file.txt", result);
    }

    // ========================
    // 边界情况
    // ========================

    @Test
    void testResolve_NullRelative() {
        String base = "/app/data";

        String result = PathResolver.resolve(base, null);

        assertEquals("/app/data", result);
    }

    @Test
    void testNormalize() {
        String input = "\\\\server\\share\\file.txt";

        String result = PathResolver.normalize(input);

        assertEquals("//server/share/file.txt", result);
    }
}