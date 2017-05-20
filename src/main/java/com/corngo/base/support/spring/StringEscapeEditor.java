package com.corngo.base.support.spring;

import jodd.util.StringUtil;

import java.beans.PropertyEditorSupport;

/**
 * 防止XSS, SQL注入
 * 
 * @author kfc
 */
public class StringEscapeEditor extends PropertyEditorSupport {
	
	private boolean control = false;

	public StringEscapeEditor() {
		super();
	}
	
	public StringEscapeEditor(boolean control) {
		super();
		this.control = control;
	}

	@Override
	public void setAsText(String text) {
		if (StringUtil.isEmpty(text)) {
			setValue(null);
		} else {
			if(control){
				text = htmlEncode(text);
			}
			setValue(text);
		}
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : null;
	}

    private String htmlEncode(String source) {
        if (source == null) {
            return "";
        }
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case '>':
                    buffer.append('＞');//全角大于号
                    break;
                case '<':
                    buffer.append('＜');//全角小于号
                    break;
                case '\'':
                    buffer.append('‘');//全角单引号
                    break;
                case '\"':
                    buffer.append('“');//全角双引号
                    break;
                case '&':
                    buffer.append('＆');//全角
                    break;
                case '\\':
                    buffer.append('＼');//全角斜线
                    break;
                case '#':
                    buffer.append('＃');//全角井号
                    break;
                case '(':
                    buffer.append('（');//全角井号
                    break;
                case ')':
                    buffer.append('）');//全角井号
                    break;
                case 10:
                case 13:
                    break;
                default:
                    buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }

}
