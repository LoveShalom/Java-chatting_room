package Serverentity.common.example;

import java.io.Serializable;

/**
 * 用于存放服务器的相关信息
 */
public class ServerInformation implements Serializable {
    private static final long serialVersionUID=1L;
    private String host;
    private String ip;
    private Integer port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
