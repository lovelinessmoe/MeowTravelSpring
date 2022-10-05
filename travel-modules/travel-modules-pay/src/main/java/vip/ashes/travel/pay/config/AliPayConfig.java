package vip.ashes.travel.pay.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import org.springframework.context.annotation.Configuration;

/**
 * @author loveliness
 */
@Configuration
public class AliPayConfig {
    // 1. 设置参数（全局只需设置一次）
    static {
        Factory.setOptions(getOptions());
    }

    private static Config getOptions() {
        Config config = new Config();

        config.protocol = "https";

        // 沙箱环境修改为 openapi.alipaydev.com
        config.gatewayHost = "openapi.alipaydev.com";

        config.signType = "RSA2";

        config.appId = "2021000118645987";

        // my私钥
        config.merchantPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCDYw0sjpaRBIxLbEOkuS16TDqrQExvf3zS2s3VFR26i6DaoXqKNue0JW+9ruJXZkQ91WZZwOtzp5bXKXvKCPJvE6nj2wu/GPmWgLqISGdfsP0OfSht7tsRt6IgdUTqGSQMIXb7pzwlsXIhPE4eDJ2KUThM255aEGMSRXloCsJKNObtE1UHPxvPAuqy66/My9vBXML9R3wOys9ENH1qRKaqitBqX2YzYmAbAPNlmdKNdL/FQsjxq1TwUIe4LWRiS2AB/GBcuqn4WMVEsTdb/Y5+7RpzcjwYBD0Nv4eLMsq5T7npH/8rO4dTIRIflPfW4BLpHxgaw+FVgEZkjdcRzRFZAgMBAAECggEAc6Q0DTq3VY6uORhidVKzr4f2v5JJdH2+jQwezOTYFMAe5S/p3kszJ0EoF8kc2RZfDBmPC4Kc+l/QiTKvDQVQ9OlWyAGdViaST6CgDaHEstnh8eXBNvlcAj0hyvXMoUtdifvOcPMiIn8LWCtvCk3KP9r69dL7joQL3yIdGLEXjx22fpSXAx5QS5QhK2uXQC/A4bnBbbO43zq8h3Wp/BWt3akhQUL1d5nNzGloD7oOZ6nFVksxUoE/6UDPhKsYJI+Ouj7c7tfFLXDWAlgcPLsvUPXSZCSQ+DCd+h/M/eVO+RJcERw1TsfYkPTvXmhEGCEjCOk59xNKC5lWhE6pwyeUAQKBgQDdGfICcQN2AHn93whyvbj4zddkl8nBwd8pEWHSwOp4H3zpd4aT9YWnVLElWzCywneFxCDaLoh36sCPWUJdnozREqyd+x8kLg0QHJ9HgTzMdAa567lP9JrW4Qc/ROEWD+qczD6zPiuwWdbWy7K2jNm6U5pbKBkvsQN6a9hYe4nSgQKBgQCYIAKXRpzqIYrRHBCQ1kDfkBn1WIXA7TvEFV8EmqnwLsGXGzt1Rn0SN9hSUzP4FkH40o/OodJXjLKh44me9vFqx2XGknVcGAPJfIaOsorYJvzaruWvIAb+Vrs98t1gSu37iE5nd/aYNjaZzsry2Eo1H7NQ4m+J4xEUkhm3+MSi2QKBgQCcWoGqulKxOssJ/ZPS2FmbEK12BGT3sOrn9tPPnv7wLYw+6Xhw31pBp4GSIjeA4kZ1gOmegPOeoZLEjGGDJOD95I/PNEukYTB5qVP10+2zw4USKyEj6p0owX9HfaUS3fSSJ1lbleZ9Ujq8MNZTg61gt0hbmDKfaDVGeEdI4yvmgQKBgHIf4ZG3qdSEIW5LDap68eRGaRMEvCNGVoX+OnBrz32etiE0juCmZwIZ3zGztdMyb3/KB5TPFcwUg5ar6vzU6bxfptzX1y+l3IF16ER5cWxDo4Izn6BH+LcqPgJ1htrMm8kOMzOwbdE3KrMszlhtJw6/n3jYxzC8pn22NZEtEefRAoGBAIkmgO/ECaoUdjtrdNxKpwdaPOH7DuQ89+msfkbEvyplrQrNaAjUN5XKhQjuCBhXPkgoshYoe2EWvOzgszZcWLhYzYUhqGeS1TyfCZmz38eb1wpo+ASlIcn4YhZm83SAdU998lBOlvB/gaTM0eDijHlKGz0tma5Bh7kBuGEcpcUF";

        // my公钥
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkugYRSC7txhXVf00zwuAuRsjb6yYp75777Da9m3JqeyX3jxvUoVWH/++Ekup+bOnKw6I6Z1KNlRxDCekm9VkeYlU3lVvqH7hvXgWdSmP9uXpVhzdsLLvHPm0ZfmaAQ9puDnCOXDIx5qGGxqHsRCJkliZx/4sFO1SmaFQFGa+vfImLyBaQT4b/6cPKb8aZvHhCXkevGtKZ1JIPtDPaQgkgw8mhDHnOP5D0zFC7wDqkcZ+6pJvNcbBkfzD0W1+6spffwS9WAjjXIjrCUoQnj0ghwOAKdEtv6HI6BZbbD/cbIKaWenXi3WXo4tKqtIdnGh4O1enMdBTdt3uFmSwhXoehwIDAQAB";

        //可设置异步通知接收服务地址（可选）（该地址需要外网能够访问）
        config.notifyUrl = "https://2420n0969k.zicp.fun/pay/pay/asyncNotify";

        return config;
    }
}
