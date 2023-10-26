package org.pzk.web.excpetion;

/**
 * @description:
 * @Author: Xhy
 * @gitee: https://gitee.com/XhyQAQ
 * @copyright: B站: https://space.bilibili.com/152686439
 * @CreateTime: 2023-10-15 18:08
 */
public class HttpRequestMethodNotSupport extends Exception{
    public HttpRequestMethodNotSupport(String message) {
        super(message);
    }
}
