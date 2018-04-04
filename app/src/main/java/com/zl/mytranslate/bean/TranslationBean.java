package com.zl.mytranslate.bean;

/**
 * Zhaolei
 * 时间:2018/4/4
 */

public class TranslationBean {

    public int status;

    private content content;

    public TranslationBean.content getContent() {
        return content;
    }

    public void setContent(TranslationBean.content content) {
        this.content = content;
    }

    public static class content {
        public String from;
        public String to;
        public String vendor;
        public String out;
        public int errNo;
    }

}
