package com.example.zhihuribao.util;



public class HtmlUtil {

    public static String formatHtml(String html) {

        html = html.replace("<div class=\"img-place-holder\">", "");

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"zhihu.css\" ></head>");
        stringBuffer.append("<body>");
        stringBuffer.append(html);
        stringBuffer.append("</body></html>");

        return stringBuffer.toString();
    }
}
