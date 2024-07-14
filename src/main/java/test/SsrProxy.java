package test;

import cn.hutool.http.HttpUtil;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import lombok.Data;
import lombok.experimental.Accessors;

public class SsrProxy {

    public static void main(String[] args) throws IOException {
        final byte[] resultBytes = HttpUtil.get("https://s.suying666.info/link/JOUCWyLXNk1urzom?sub=1")
                .getBytes(StandardCharsets.UTF_8);
        String result = new String(Base64.getDecoder().decode(resultBytes));
        String[] split = result.split("\\n");
        Arrays.asList(split).forEach(ssrUrl -> {
            String[] dataArr = ssrUrl.split("ssr://");
            if (dataArr.length <= 1) {
                return;
            }
            String ssrRealEncodeString = dataArr[1];
            String subscriptionContent = new String(Base64.getUrlDecoder().decode(ssrRealEncodeString.getBytes()));
            System.out.println("ssrRealEncodeString=== " + subscriptionContent);


        });;

        System.out.println("remarks = " + new String(Base64.getDecoder().decode("VjQtMzYxNXzml6XmnKx8eDEuNQ".getBytes())));
        System.out.println("group = " + new String(Base64.getDecoder().decode("6YCf6bmwNjY2".getBytes())));
        System.out.println("protoparam = " + new String(Base64.getDecoder().decode("MTI4MTYxOndvc2hpemhvdWp1bjQ1NnM".getBytes())));
        System.out.println("obfsparam = " + new String(Base64.getDecoder().decode("Y2IwZmIxMjgxNjEubWljcm9zb2Z0LmNvbQ".getBytes())));

    }

    // 解析SSR订阅链接，获取SSR服务器的地址、端口、密码、加密方式等信息
    private static SsrInfo parseSsrLink(String ssrLink) {
        String subscriptionContent = new String(Base64.getUrlDecoder().decode(ssrLink.getBytes()));
        // TODO: 实现解析逻辑
        String[] segments = subscriptionContent.split(":");
        String server = segments[0].substring(6); // remove the "ssr://" prefix
        int port = Integer.parseInt(segments[1]);
        String protocol = segments[2];
        String method = segments[3];
        String obfs = segments[4];
        String passwordBase64 = segments[5].split("/")[0];
        String password = new String(Base64.getUrlDecoder().decode(passwordBase64.getBytes()));
        return new SsrInfo(server, port, password, method);
    }

    // 代理服务器
    private static class ProxyServer {

        private final SsrInfo ssrInfo;

        public ProxyServer(SsrInfo ssrInfo) {
            this.ssrInfo = ssrInfo;
        }

        public void start(int port) throws IOException {
            // 监听本地端口
            java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.SOCKS, new InetSocketAddress(ssrInfo.getAddress(), ssrInfo.getPort()));
            java.net.URL url = new java.net.URL("https://www.baidu.com/");
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection(proxy);
            connection.connect();
            System.out.println(connection.getResponseCode());
            // TODO: 实现代理服务器逻辑
        }

        private void handleRequest(Socket clientSocket) throws IOException {
            // TODO: 实现HTTP请求处理逻辑
        }

    }

    // SSR服务器信息
    @Data
    @Accessors(chain = true)
    private static class SsrInfo {

        private final String address;
        private final int port;
        private final String password;
        private final String encryptionMethod;

        public SsrInfo(String address, int port, String password, String encryptionMethod) {
            this.address = address;
            this.port = port;
            this.password = password;
            this.encryptionMethod = encryptionMethod;
        }

    }

}
